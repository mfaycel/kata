package fr.societe.generale.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import fr.societe.generale.model.KataCompte;
import fr.societe.generale.model.KataOperation;
import fr.societe.generale.model.KataUser;
import fr.societe.generale.model.def.OperationTypeEnum;
import fr.societe.generale.service.CompteService;
import fr.societe.generale.service.KataUserService;
import fr.societe.generale.service.OperationService;
import fr.societe.generale.service.container.CompteContainer;
import fr.societe.generale.util.exception.ApplicationException;
/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
@RestController
public class KataRestController {
	
	private static Logger log = LoggerFactory.getLogger(CompteContainer.class);
	@Autowired
	KataUserService kataUserService; 
	@Autowired
	CompteService kataCompteService; 
	@Autowired
	OperationService kataOperationService; 


	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public ResponseEntity<List<KataUser>> initAndGetAllUser() throws ApplicationException {
		log.info("init start");
		kataUserService.initForTest();
		List<KataUser> users = kataUserService.findAllUsers();
		if(users.isEmpty()){
			log.info("init finished with error");
			return new ResponseEntity<List<KataUser>>(HttpStatus.NO_CONTENT);
		}
		log.info("init success");
		return new ResponseEntity<List<KataUser>>(users, HttpStatus.OK);
	}



	//-------------------Retrieve Single User--------------------------------------------------------

	@RequestMapping(value = "/kataUser/{id}", method = RequestMethod.GET)
	public ResponseEntity<KataUser> getUser(@PathVariable("id") long id) throws ApplicationException {
		log.info("Fetching User with id " + id);
		KataUser user = kataUserService.getById(String.valueOf(id));
		if (user == null) {
			log.info("User with id " + id + " not found");
			return new ResponseEntity<KataUser>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<KataUser>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/kataUser/login/", method = RequestMethod.POST)
	public ResponseEntity<KataUser> logUser(@RequestBody KataUser user,    UriComponentsBuilder ucBuilder) {
		KataUser kataUser = kataUserService.authenticate(user.getName(), user.getPassword());
		return new ResponseEntity<KataUser>(kataUser, HttpStatus.OK);

	} 

	//-------------------Retrieve Single Compte--------------------------------------------------------

	@RequestMapping(value = "/kataCompte/{bic}", method = RequestMethod.GET)
	public ResponseEntity<KataCompte> getCompte(@PathVariable("bic") String bic) throws ApplicationException {
		log.info("Fetching compte with bic " + bic);
		KataCompte kataCompte = kataCompteService.getById(bic);
		if (kataCompte == null) {
			log.info("compte with bic " + bic + " not found");
			return new ResponseEntity<KataCompte>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<KataCompte>(kataCompte, HttpStatus.OK);
	}

	//-------------------get  Compte pour by user--------------------------------------------------------

	@RequestMapping(value = "/kataCompte/kataUser/{id}", method = RequestMethod.GET)
	public ResponseEntity<KataCompte> getCompteByUser(@PathVariable("id")  long id) throws ApplicationException {
		log.info("Fetching compte with id user  " + id);
		List<KataCompte> liComptes = kataCompteService.findCompteByUser(id);
		if (liComptes.isEmpty()) {
			log.info("compte with id user " + id + " not found");
			return new ResponseEntity<KataCompte>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<KataCompte>(liComptes.get(0), HttpStatus.OK);
	}
	//-------------------Retrieve Operations by compte--------------------------------------------------------

	@RequestMapping(value = "/kataOperation/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<KataOperation>> getOPerationsBYCompte(@PathVariable("id") String bic) throws ApplicationException {
		log.info("Fetching compte with bic " + bic);
		List<KataOperation> liKataOperations = kataOperationService.findOperationsBYCompte(bic);
		if (liKataOperations.isEmpty()) {
			log.info("compte with bic " + bic + " not found");
			return new ResponseEntity<List<KataOperation>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<KataOperation>>(liKataOperations, HttpStatus.OK);
	}

	//-------------------Retrieve Operations by compte--------------------------------------------------------

	@Transactional
	@RequestMapping(value = "/kataOperation/", method = RequestMethod.PUT)
	public void transfert(@RequestBody String bicFrom, String bicTo, Float montant) throws ApplicationException {
		log.info("transfert from" + bicFrom  +" to "+bicTo +" montant :"+montant);
		kataCompteService.addOperation(bicFrom,OperationTypeEnum.WITHDRAWAL,montant);
		kataCompteService.addOperation(bicTo,OperationTypeEnum.DEPOSIT,montant);


	}
}