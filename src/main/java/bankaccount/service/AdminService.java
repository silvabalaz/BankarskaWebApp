package bankaccount.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bankaccount.model.Account;
import bankaccount.model.Transaction;
import bankaccount.service.AccountService;
import bankaccount.service.TransactionService;

@Service
public class AdminService {
	
    private static Logger logger = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private TransactionService transactionService;
    
    
    List<Transaction> findAll(){
    	
    	
    	return transactionService.findAll();
    	
    }
    
    

}
