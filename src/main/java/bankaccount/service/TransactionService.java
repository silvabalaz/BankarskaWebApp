package bankaccount.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import bankaccount.model.Account;
import bankaccount.model.Transaction;
import bankaccount.repository.TransactionRepository;

public class TransactionService {

    private static Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    private TransactionRepository repository;
   
    public boolean save(Transaction newTrans) {

        boolean saved = false;
        
        Transaction newTransaction = repository.save(newTrans);

        logger.info("Saved prije: " + saved + "User:" + newTransaction.getSourceIban() + " " + newTransaction.getDestinationIban() );
        
        if(newTransaction != null) {
            return saved = true;

        }
        logger.info("PRIJE RETURNA U SAVEU" + newTransaction);
        
        return saved;
    		
    }
    
    public List<Transaction> findAll(){
    	
    	return repository.findAll();
    	 	
    }
    /*
    public List<Transaction> findAllByIban(int iban){
    	
    	 List<Transaction> all = repository.findAll();
 
    	 List<Transaction> allMine = new ArrayList();
    	 
    	 for(Transaction t: all) {
    		 if(t.getSourceIban() == iban)
    			 allMine.add(t);
    	 }
    	 return allMine;
    }
    
    List<Transaction> findAllByStatus(int iban, String status){
    	//sortirati naloge po vremenu
    	//pronać sve moje naloge
    	//filter po zadanom statusu
    	List<Transaction> myTransactions = repository.findAllByIban(iban);
    	
    	Collections.sort(myTransactions, new Comparator<Transaction>() {
    		  public int compare(Transaction o1, Transaction o2) {
    		      if (o1.getCurrentTime() == null || o2.getCurrentTime() == null)
    		        return 0;
    		      return o1.getCurrentTime().compareTo(o2.getCurrentTime());
    		  }
    		});
    	
    	List<Transaction> statusTransactions = new ArrayList<Transaction>(); 
    	for(Transaction t : myTransactions ) {
    		if(t.getStatus().equals(status))
    			statusTransactions.add(t);
    		
    	}
    	
    	return statusTransactions;
    	
    }
*/
}
