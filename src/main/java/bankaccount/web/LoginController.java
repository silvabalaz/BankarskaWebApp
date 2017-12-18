package bankaccount.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bankaccount.model.security.Role;
import bankaccount.service.ClientService;
import bankaccount.service.Util;

@Controller
@RequestMapping("/login")
public class LoginController {
	

    private static final String LOGIN_VIEW = "client_login";
    
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private ClientService service; 
	
	 @RequestMapping(method = RequestMethod.GET)
	    public String loginForm( Model model) {
	    	
	        model.addAttribute("clientInfo", new ClientDto());
	      
	      
	        return LOGIN_VIEW;
	    }
	   
    @RequestMapping(method = RequestMethod.POST)
    public String login(@ModelAttribute("clientInfo")ClientDto clientInfo, Model model,RedirectAttributes redirectAttributes) {


        logger.info("Klijent ime i lozinka:" + clientInfo.getUsername() + "' password: '" + clientInfo.getPassword() + " ");
        logger.info("hasshed password: " + Util.getPasswordHash(clientInfo.getPassword()));
        logger.info("service.isValid::  " + service.isValid(clientInfo.getUsername(), clientInfo.getPassword()));

        if(service.isValid(clientInfo.getUsername(), clientInfo.getPassword())) {

            redirectAttributes.addFlashAttribute("clientInfo", clientInfo);
            
            if(clientInfo.getRole() == Role.ROLE_ADMIN){
            	
            	return "redirect:/admin/all";
            	
            }
            
            return "redirect:/transactioncreate";
        }

        model.addAttribute("isWrongPassword", true);


        return loginForm(model);
    }
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(Model model)
    {
        return "index";
    }
    
	   

}
