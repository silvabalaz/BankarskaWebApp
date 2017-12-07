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
import bankaccount.model.Client;
import bankaccount.service.ClientService;



@Controller
@RequestMapping("/clientcreate")
public class NewClientController {
	

    private static final String CREATE_VIEW = "client_create";
 
    private static Logger logger = LoggerFactory.getLogger(NewClientController.class);
    
    @Autowired
    public ClientService service; 
   
    @RequestMapping(method = RequestMethod.GET)
    public String clientCreate( Model model) {
    	
        model.addAttribute("clientInfo", new ClientDto());
      
      
        return CREATE_VIEW;
    }
   
    @RequestMapping(method = RequestMethod.POST)
    public String create(@ModelAttribute("clientInfo")ClientDto clientInfo, Model model,RedirectAttributes redirectAttributes) {
    	
    	logger.info("Podaci o trenutnom klijentu:", clientInfo.getUsername(), clientInfo.getPassword());
    	
    	if(service.findByUsername(clientInfo.getUsername()) != null) {
    		
       	 model.addAttribute("vecPostoji",true);
	           
       	return CREATE_VIEW;
	        
       }
    		
     
        Client newClient = new Client(clientInfo.getUsername(), clientInfo.getPassword());    
        
		service.save(newClient);
		
        model.addAttribute("noviKlijentIme", newClient.getUsername());
        
        return clientCreate(model);
    }

}
