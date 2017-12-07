package bankaccount.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bankaccount.model.Account;
import bankaccount.model.Client;
import bankaccount.repository.AccountRepository;

import java.util.List;

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
/*
	public void saveChanges(int sourceIban, int destinationIban, double amount) {
		
		Account source = repository.findByIban(sourceIban);
		Account destination = repository.findByIban(destinationIban);
		logger.info("source, destination" + source + " " + destination);
		double newSourceBalance = source.getBalance() - amount;
		double newDestinationBalance = source.getBalance() + amount;
		
		source.setBalance(newSourceBalance);
		destination.setBalance(newDestinationBalance);
		logger.info("source, destination" + source + " " + destination);
	}*/
}
