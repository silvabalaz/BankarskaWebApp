package bankaccount.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bankaccount.model.Account;
import bankaccount.repository.AccountRepository;

@Service
public class AccountService {

    private static Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository repository;
   
    public Account findByIban(long iban) {
    	
    	return repository.findByIban(iban);
    	
    }
    
	public double findBalanceByIban(long iban) {
    	
    	Account account = repository.findByIban(iban);
        double balance = account.getBalance();
        return balance;
	}

}
