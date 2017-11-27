package bankaccount.web;
import java.math.BigDecimal;

import org.hibernate.mapping.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bankaccount.model.Account;
import bankaccount.repository.AccountRepository;
import bankaccount.service.AccountService;
import bankaccount.service.PasswordService;


@Controller
@RequestMapping("/accountcreate")
public class NewAccountController {
	

    private static final String CREATE_VIEW = "account_create";
 
    private static Logger logger = LoggerFactory.getLogger(NewAccountController.class);
    
    @Autowired
    public AccountService service; 
    public AccountRepository repository;
  /*  
    @RequestMapping("/")
    public String index() {
    	logger.info("accountCreate");
    	logger.debug("index controller");
        return "index";
    }

    @RequestMapping("/accountcreate")
    public String accountCreate(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
    	
        //model.addAttribute("accountInfo", new AccountDto());
        model.addAttribute("name",name);
      
        return CREATE_VIEW;
    }*/
    
    @RequestMapping(method = RequestMethod.GET)
    public String accountCreate( Model model) {
    	
        model.addAttribute("accountInfo", new AccountDto());
      
      
        return CREATE_VIEW;
    }
   
    @RequestMapping(method = RequestMethod.POST)
    public String create(@ModelAttribute("accountInfo")AccountDto accountInfo, Model model,RedirectAttributes redirectAttributes) {
 	
        logger.info("Account create -> accountInfo: '" + accountInfo.getUsername() + "' password: '" + accountInfo.getPassword() + "'");
        Account newAccount = new Account(accountInfo.getUsername(),accountInfo.getPassword(), 123456);       
        logger.info("newAccout STVOREEEEN" + "  " + newAccount.getId()+ "   " + newAccount.getPassword()+ "  " + newAccount.getUsername()+"  "+ newAccount.getIban() + "  " + newAccount.getBalance());
	
		logger.info("rezultat sejvanja u repozitorij new Account" + service.save(newAccount));
		
        logger.info("acc info getusername" + accountInfo.getUsername());
        		
        logger.info(" find.." + service.findByUsername(accountInfo.getUsername()) );
       /*
        if(repository.findByUsername(accountInfo.getUsername())) {
        	 logger.info("to ime već postoji");
	         
	        
        	return CREATE_VIEW;
	        
        }*/
        logger.info("iza ifa");
        //logger.info("service.isValid::  " + service.findByUsername(accountInfo.getUsername());
      /*
        if(service.isValid(accountInfo.getUsername(), accountInfo.getPassword())) {
        	logger.info("usao u isValid");
            redirectAttributes.addFlashAttribute("accountInfo", accountInfo);

            return "redirect:index";
        }
*/
        model.addAttribute("isWrongPassword", true);


        return accountCreate(model);
    }

}
