package bankaccount.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import bankaccount.service.ClientService;

@Controller
public class HomeController {
	
	 private static final String HOME_VIEW = "index";

    @Autowired
    private ClientService userService;

    @GetMapping("/")
    public String index(Authentication authentication) {
    	
        return HOME_VIEW;
    }

}