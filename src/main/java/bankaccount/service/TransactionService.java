package bankaccount.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bankaccount.model.Transaction;
import bankaccount.repository.TransactionRepository;

@Service
public class TransactionService {

    private static Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    private TransactionRepository repository;
   
    public boolean saveTransaction(Transaction newTrans) {

        boolean saved = false;
        
        Transaction newTransaction = repository.save(newTrans);
        
        if(newTransaction != null) {
            return saved = true;

        }
         
        return saved;
    		 
    }
    
    public List<Transaction> findAll(){
    	
    	return repository.findAll();
    	 	
    }
    /*
    public List<Transaction> findAllBysourceIban(int iban){
    	
    	 List<Transaction> all = repository.findAll();
 
    	 List<Transaction> allMine = new ArrayList();
    	 
    	 for(Transaction t: all) {
    		 if(t.getSourceIban() == iban)
    			 allMine.add(t);
    	 }
    	 return allMine;
    }*/
    
    public List<Transaction> findAllByStatus(int iban, String status){
    	//sortirati naloge po vremenu
    	//pronać sve moje naloge
    	//filter po zadanom statusu
      	 List<Transaction> all = repository.findAll();
      	 logger.info("all: " + all);
    	 List<Transaction> allMine = new ArrayList();
    	
    	 for(Transaction t: all) {
    		 if(t.getSourceIban() == iban)
    			 allMine.add(t);
    	 }
    	 logger.info("allMine" + allMine);
    	Collections.sort(allMine, new Comparator<Transaction>() {
    		  public int compare(Transaction o1, Transaction o2) {
    		      if (o1.getCurrentTime() == null || o2.getCurrentTime() == null)
    		        return 0;
    		      return o1.getCurrentTime().compareTo(o2.getCurrentTime());
    		  }
    		});
    	logger.info("allMine, usporedjeno po vremenu" + allMine);;
    	List<Transaction> statusTransactions = new ArrayList<Transaction>(); 
    	for(Transaction t : allMine ) {
    		if(t.getStatus().equals(status))
    			statusTransactions.add(t);
    		
    	}
    	logger.info("statusTransactions" + statusTransactions);
    	return statusTransactions;
    	
    }

}
