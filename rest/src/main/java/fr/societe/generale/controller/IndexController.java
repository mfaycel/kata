package fr.societe.generale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 
 * @author mbarki 5 févr. 2018 
 *
 */
@Controller
@RequestMapping("/")
public class IndexController {

	  @RequestMapping(method = RequestMethod.GET)
	    public String getIndexPage() {
	        return "login";
	    }
	  
	  @RequestMapping( value="/kataCompte/kataUser/", method = RequestMethod.GET)
	    public String getComptePage() {
	        return "compte";
	    }
}