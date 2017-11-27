package bankaccount.web;


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

import bankaccount.model.Account;
import bankaccount.service.AccountService;
import bankaccount.service.PasswordService;

@Controller
@RequestMapping("/login")
public class LoginController {
	

    private static final String LOGIN_VIEW = "account_login";
    private static final String TRANS_VIEW = "transaction_create";
 
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private AccountService service; 
	
	
	
	 @RequestMapping(method = RequestMethod.GET)
	    public String loginForm( Model model) {
	    	
	        model.addAttribute("accountInfo", new AccountDto());
	      
	      
	        return LOGIN_VIEW;
	    }
	   
    @RequestMapping(method = RequestMethod.POST)
    public String login(@ModelAttribute("accountInfo")AccountDto accountInfo, Model model,RedirectAttributes redirectAttributes) {


        logger.info("Account create -> accountInfo: '" + accountInfo.getUsername() + "' password: '" + accountInfo.getPassword() + "'");
        logger.info("hasshed password: " + PasswordService.getPasswordHash(accountInfo.getPassword()));


        logger.info("service.isValid::  " + service.isValid(accountInfo.getUsername(), accountInfo.getPassword()));

        if(service.isValid(accountInfo.getUsername(), accountInfo.getPassword())) {

            redirectAttributes.addFlashAttribute("accountInfo", accountInfo);
            
            return "redirect:/transactioncreate";
        }

        model.addAttribute("isWrongPassword", true);


        return loginForm(model);
    }
    
	   

}
