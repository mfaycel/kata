package fr.societe.generale.util.conf.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.ehcache.EhCacheMessageLogger;
import org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory;
import org.hibernate.cache.ehcache.internal.util.HibernateEhcacheUtils;
import org.hibernate.cfg.Settings;
import org.jboss.logging.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fr.societe.generale.util.enumeration.TechnicalException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.Configuration;
/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
public class CustomSingletonEhCacheRegionFactory extends SingletonEhCacheRegionFactory {

	protected static final Log log = LogFactory.getLog(CustomSingletonEhCacheRegionFactory.class);

	private static final long serialVersionUID = 1L;

	private static final EhCacheMessageLogger LOG = Logger.getMessageLogger(
			EhCacheMessageLogger.class,
			SingletonEhCacheRegionFactory.class.getName()
	);

	private static final AtomicInteger REFERENCE_COUNT = new AtomicInteger();

	public CustomSingletonEhCacheRegionFactory() {
	}

	public CustomSingletonEhCacheRegionFactory(Properties prop) {
	}

	public void start(Settings settings, Properties properties) throws CacheException {
		this.settings = settings;
		try {
			String mainConfigXml = readConfigFileFromName("/ehcache.xml");
			String regionsConfigXml = readConfigFileFromName("/ehcache-regions.xml");

			String configXml = updateConfigFile(mainConfigXml, regionsConfigXml);

			URL url = writeConfigFileToUrl(configXml);

			final Configuration configuration = HibernateEhcacheUtils.loadAndCorrectConfiguration( url );
			manager = CacheManager.create( configuration );
			REFERENCE_COUNT.incrementAndGet();

			mbeanRegistrationHelper.registerMBean( manager, properties );
		}
		catch (net.sf.ehcache.CacheException e) {
			throw new CacheException( e );
		}
	}

	private static String updateConfigFile(String mainConfigXmlStr, String regionsConfigXmlStr) {

		Document mainConfigXml = parseXmlString(mainConfigXmlStr);
		Document subConfigXml = parseXmlString(regionsConfigXmlStr);

		insertSubXmlIntoMainXml(mainConfigXml, subConfigXml);

		String configXmlStr = xmlToString(mainConfigXml);

		NodeList cacheNameNodes = xPathGetNodes(mainConfigXml, "/ehcache/cache/cacheEventListenerFactory"
				+ "[@class='net.sf.ehcache.distribution.RMICacheReplicatorFactory']/../@name");
		List<String> cachesWithReplication = new ArrayList<>();
		for (int i = 0; i < cacheNameNodes.getLength(); ++i) {
			String cacheName = ((Attr) cacheNameNodes.item(i)).getValue();
			cachesWithReplication.add(cacheName);
		}
		NodeList defaultCacheNodes = xPathGetNodes(mainConfigXml, "/ehcache/defaultCache/cacheEventListenerFactory"
				+ "[@class='net.sf.ehcache.distribution.RMICacheReplicatorFactory']/..");
		if (defaultCacheNodes.getLength() == 1) {
			cachesWithReplication.add("default");
		}


		Map<String, String> replaceMap = new LinkedHashMap<>();

		for (Map.Entry<String, String> entry : replaceMap.entrySet()) {
			log.trace("Replace in ehcache.xml : \"{"+entry.getKey()+"}\" by \""+entry.getValue()+"\"");
			configXmlStr = configXmlStr.replace("{"+entry.getKey()+"}", entry.getValue());
		}

		return configXmlStr;
	}

	private String readConfigFileFromName(String configurationResourceName) {
		URL url;
		try {
			url = new URL( configurationResourceName );
		}
		catch (MalformedURLException e) {
			if ( !configurationResourceName.startsWith( "/" ) ) {
				configurationResourceName = "/" + configurationResourceName;
				LOG.debugf("prepending / to %s. It should be placed in the root of the classpath rather than in a package.",
					configurationResourceName
				);
			}
			url = loadResource( configurationResourceName );
		}

		String content;
		try(InputStream input = url.openStream()) {
			content = IOUtils.toString(input, "utf-8");
		} catch (Exception e) {
		    throw new TechnicalException("Error parsing ehcache config from " + url + ". Initial cause was " + e.getMessage(), e);
		}

		return content;
	}

	private static void insertSubXmlIntoMainXml(Document mainConfigXml, Document subConfigXml) {
		Element mainRootElement = mainConfigXml.getDocumentElement();
		NodeList subChildNodes = subConfigXml.getFirstChild().getChildNodes();
		for(int i=0; i<subChildNodes.getLength(); i++) {
			Node child = subChildNodes.item(i).cloneNode(true);
			mainConfigXml.adoptNode(child);
			mainRootElement.appendChild(child);
		}
	}

	private static Document parseXmlString(String xmlStr) {
		try (InputStream mainConfigInput = new ByteArrayInputStream(xmlStr.getBytes("utf-8"))) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			return db.parse(mainConfigInput);
		} catch (Exception e) {
			throw new TechnicalException("Error parsing ehcache config", e);
		}
	}

	private static String xmlToString(Document doc) {
		try(StringWriter writer = new StringWriter()) {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			return writer.toString();
		} catch (TransformerException|IOException e) {
			throw new TechnicalException("Failed to write xml", e);
		}
	}

	private static URL writeConfigFileToUrl(String content) {
		try(ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			out.write(content.getBytes("utf-8"));
			out.close();
			URL memUrl = new URL(null, "mem:/ehcache.xml", new BytesURLStreamHandler(out.toByteArray()));

			return memUrl;
		} catch (Exception e) {
		    throw new TechnicalException("Error writing ehcache config to memory URL. Initial cause was " + e.getMessage(), e);
		}
	}

	private static NodeList xPathGetNodes(Document xmlDoc, String path) {
		try {
			XPath xPath = XPathFactory.newInstance().newXPath();
			NodeList nodes = (NodeList)xPath.evaluate(path, xmlDoc.getDocumentElement(), XPathConstants.NODESET);
			return nodes;
		} catch (XPathExpressionException e) {
			throw new TechnicalException("Failed to evaluate XPath \""+path+"\"");
		}
	}

	public void stop() {
		try {
			if ( manager != null ) {
				if ( REFERENCE_COUNT.decrementAndGet() == 0 ) {
					manager.shutdown();
				}
				manager = null;
			}
		}
		catch (net.sf.ehcache.CacheException e) {
			throw new CacheException( e );
		}
	}

	private static class BytesURLStreamHandler extends URLStreamHandler {
		byte[] content;

		public BytesURLStreamHandler(byte[] content) {
			this.content = content;
		}

		public URLConnection openConnection(URL url) {
			return new BytesURLConnection(url, content);
		}
	}

	private static class BytesURLConnection extends URLConnection {
		protected byte[] content;

		public BytesURLConnection(URL url, byte[] content) {
			super(url);
			this.content = content;
		}

		public void connect() {
		}

		public InputStream getInputStream() {
			return new ByteArrayInputStream(content);
		}
	}
}