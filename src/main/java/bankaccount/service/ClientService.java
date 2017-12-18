package bankaccount.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bankaccount.model.Account;
import bankaccount.model.Client;
import bankaccount.model.security.Role;
import bankaccount.repository.AccountRepository;
import bankaccount.repository.ClientRepository;

@Service
public class ClientService {
	

    private static Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private ClientRepository repository;

    @Autowired
    private AccountRepository repositoryA;

    public boolean save(Client client){

        boolean saved = false;
        Client newClient = repository.save(client);

        if(newClient != null) {
            return saved = true;

        }
       
        return saved;
    }

    public boolean isValid(String username, String password) {

        boolean isValid = false;

        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {

            final String hashedPassword = Util.getPasswordHash(password);
        
            Client client = repository.findByUsername(username);
            
            logger.info("account by username" + client.getUsername());
            
            if (hashedPassword.equals(client.getPassword())) 
                isValid = true;
               
       }
        
        return isValid;
    }

    public Client findByUsername(String username) {
    	
    	return repository.findByUsername(username);
    	
    }
    
    public Account findByIban(long iban) {
    	
    	return repositoryA.findByIban(iban);
    	
    }
    

    public long findIbanByUsername(String username) {
    	
    	Client client = repository.findByUsername(username);
    	
    	long iban = (long)0;
    	if(client.getRole() != Role.ROLE_ADMIN)
    	  iban = client.getAccount().getIban();
    	
    	return iban;
    }
   

    public double findBalanceByIban(long iban) {
    	
    	Account account = repositoryA.findByIban(iban);
    	double balance = account.getBalance();
    	
    	return balance;
    }
}
