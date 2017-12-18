package bankaccount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bankaccount.model.Account;
import bankaccount.model.Transaction;
import bankaccount.repository.AccountRepository;

@Service
public class AdminService {
	
    @Autowired
    private TransactionService transactionService; 
    
    @Autowired
    private ClientService clientService; 
    
    @Autowired
    private AccountService accountService; 
    
 
    public boolean executeAdmin(Transaction trans){
    	
    	boolean execute = false;
    	
    	
    	Account source = trans.getSourceAccount(); 
    	double newBalace = source.getBalance() - trans.getBalance();
    	source.setBalance(newBalace);
    	Long destination = trans.getDestinationIban();
    	Account	destAccount = clientService.findByIban(destination); 
    	double newDest = destAccount.getBalance() + trans.getBalance();
    	destAccount.setBalance(newDest);
    	trans.setVerified(true);
    	transactionService.save(trans); 
    	
    	return execute ;
    }
    

    public boolean checkBalance(Transaction trans){
    	
    	boolean odbijen = false;
    	
    	Account source = trans.getSourceAccount(); 
    	double sourceBalance = source.getBalance();
    	double transBalance = trans.getBalance();
        
    	if(transBalance > sourceBalance)
    		return odbijen = true;
    	
    	return odbijen;
    
    }

}
