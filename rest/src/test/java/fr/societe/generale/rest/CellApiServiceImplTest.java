package fr.societe.generale.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.societe.generale.model.def.OperationTypeEnum;
import fr.societe.generale.service.CompteService;
import fr.societe.generale.service.KataUserService;
import fr.societe.generale.service.OperationService;
import fr.societe.generale.util.conf.AppConfig;
import fr.societe.generale.util.exception.ApplicationException;
import junit.framework.TestCase;

/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@ActiveProfiles("test")
public class CellApiServiceImplTest  {
	
	final  String  USER_NAME_TEST ="mbarki";
	final Long  ID_USER_TEST =(long) 1;
	final String ID_COMPTE_TEST = "bic1";
	final String ID_COMPTE_TEST_TO = "bic2";
	final String ID_COMPTE_TEST_FROM = "bic3";
	
	@Autowired
	KataUserService kataUserService; 
	@Autowired
	CompteService kataCompteService; 
	@Autowired
	OperationService kataOperationService; 
	@Test
	public void initAndGetAllUser() throws ApplicationException{
		kataUserService.initForTest();
		TestCase.assertFalse(kataUserService.findAllUsers().isEmpty());

	}
	@Test
	public void getUserById() throws ApplicationException{
		kataUserService.initForTest();
		TestCase.assertTrue(USER_NAME_TEST.equals(kataUserService.getById(String.valueOf(ID_USER_TEST)).getName()));

	}
	@Test
	public void authenticate() throws ApplicationException{
		kataUserService.initForTest();
		TestCase.assertTrue(USER_NAME_TEST.equals(kataUserService.authenticate(USER_NAME_TEST,USER_NAME_TEST).getName()));

	}

	@Test
	public void getCompteByIdCompte() throws ApplicationException{
		kataUserService.initForTest();
		TestCase.assertNotNull(kataCompteService.getById(ID_COMPTE_TEST));

	}
	
	@Test
	public void getListCompteBYIdUser() throws ApplicationException{
		kataUserService.initForTest();
		TestCase.assertTrue(1==kataCompteService.findCompteByUser(ID_USER_TEST).size());

	}
	
	@Test
	public void testNormalOperationDeposit() throws ApplicationException{
		kataUserService.initForTest();
		kataCompteService.addOperation(ID_COMPTE_TEST_TO,OperationTypeEnum.DEPOSIT,(float) 20);
		TestCase.assertTrue(70==kataCompteService.getById(ID_COMPTE_TEST_TO).getBalance());
	}

	@Test
	public void testNormalOperationDrawal() throws ApplicationException{
		kataUserService.initForTest();
		TestCase.assertTrue(kataCompteService.addOperation(ID_COMPTE_TEST_TO,OperationTypeEnum.WITHDRAWAL,(float) 20));
		TestCase.assertTrue(30==kataCompteService.getById(ID_COMPTE_TEST_TO).getBalance());
	
	}
	@Test
	public void testNormalOperations() throws ApplicationException{
		kataUserService.initForTest();
		TestCase.assertTrue(kataCompteService.addOperation(ID_COMPTE_TEST_TO,OperationTypeEnum.WITHDRAWAL,(float) 30));
		TestCase.assertTrue(kataCompteService.addOperation(ID_COMPTE_TEST_TO,OperationTypeEnum.DEPOSIT,(float) 10));
		TestCase.assertTrue(30==kataCompteService.getById(ID_COMPTE_TEST_TO).getBalance());
		TestCase.assertTrue(kataOperationService.findOperationsBYCompte(ID_COMPTE_TEST_TO).size()>1);
	
	}
	@Test
	public void testErrorOperations() throws ApplicationException{
		kataUserService.initForTest();
		TestCase.assertFalse(kataCompteService.addOperation(ID_COMPTE_TEST_TO,OperationTypeEnum.WITHDRAWAL,(float) 60));
		
	}


}
