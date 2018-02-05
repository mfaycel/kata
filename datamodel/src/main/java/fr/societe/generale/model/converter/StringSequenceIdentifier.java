package fr.societe.generale.model.converter;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.FlushMode;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.type.Type;


/**
 * 
 * https://stackoverflow.com/questions/37747218/how-to-implement-a-custom-string-sequence-identifier-generator-with-hibernate/
 * https://vladmihalcea.com/2016/06/13/how-to-implement-a-custom-string-based-sequence-identifier-generator-with-hibernate/
 *
 */
public class StringSequenceIdentifier implements IdentifierGenerator, Configurable {


	private String sequenceCallSyntax;

	@Override
	public void configure(Type type, Properties params, Dialect dialect) throws MappingException {

		final String sequencePerEntitySuffix = ConfigurationHelper.getString(SequenceStyleGenerator.CONFIG_SEQUENCE_PER_ENTITY_SUFFIX, 
				params,
				SequenceStyleGenerator.DEF_SEQUENCE_SUFFIX);

		boolean preferSequencePerEntity = ConfigurationHelper.getBoolean(SequenceStyleGenerator.CONFIG_PREFER_SEQUENCE_PER_ENTITY, params, false);

		final String defaultSequenceName;
		if (preferSequencePerEntity){
			defaultSequenceName=params.getProperty(JPA_ENTITY_NAME) + sequencePerEntitySuffix;
		} else {
			defaultSequenceName=SequenceStyleGenerator.DEF_SEQUENCE_NAME;
		}
		
		sequenceCallSyntax = dialect.getSequenceNextValString(ConfigurationHelper.getString(SequenceStyleGenerator.SEQUENCE_PARAM, params, defaultSequenceName));
		
	}
	

	@Override
	public Serializable generate(SessionImplementor session, Object obj) {
		long seqValue = ((Number) Session.class.cast(session).createSQLQuery(sequenceCallSyntax).setFlushMode(FlushMode.COMMIT).uniqueResult()).longValue();
		return Long.toString(seqValue);
	}


}