package bankaccount.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bankaccount.model.Account;
import bankaccount.repository.AccountRepository;

import java.util.List;

@Service
public class AccountService {

    private static Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository repository;

    public boolean save(Account account){

        boolean saved = false;
        Account newAccount = repository.save(account);

        logger.info("Saved prije: " + saved + "User:" + newAccount.getUsername() + " " + newAccount.getPassword() );
        if(newAccount != null) {
            return saved = true;

        }
        logger.info("PRIJE RETURNA U SAVEU" + newAccount);
        return saved;
    }

    public boolean isValid(String username, String password) {

        boolean isValid = false;

        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {

            final String hashedPassword = PasswordService.getPasswordHash(password);
        	//final String hashedPassword = password;
            Account account = repository.findByUsername(username);
            logger.info("account by username" + account.getUsername());
            if (hashedPassword.equals(account.getPassword())) 
                isValid = true;
               
       }
        
        return isValid;
    }

    public Account findByUsername(String username) {
    	
    	return repository.findByUsername(username);
    	
    }
    
    public int findIbanByUsername(String username){
    	
    	Account account = repository.findByUsername(username);
        int currentIban = account.getIban();
      
    	return currentIban;	    	
    }
}
