package bankaccount.web.admin;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import bankaccount.service.AccountService;
import bankaccount.service.ClientService;
import bankaccount.service.TransactionService;
import bankaccount.web.TransactionController;
import bankaccount.model.Account;
import bankaccount.model.Transaction;

@Controller
@RequestMapping("/transaction")
@PreAuthorize("hasRole('ADMIN')")
public class TransactionDetailsController {
	
    private static final String TRANS_DETAILS = "transaction_details";
    private static final String TRANS_ALL = "transaction_all";
 
    private static Logger logger = LoggerFactory.getLogger(TransactionDetailsController.class);
    
    @Autowired
    private TransactionService transactionService; 
    
    @Autowired
    private AccountService accountService; 
	
    @RequestMapping("/all")
    public String all(Model model) {
    	
        List<Transaction> transactionsAll = new ArrayList<Transaction>();
        transactionsAll = transactionService.findAll();
        logger.info("transactionsAll" + transactionsAll);
        
        List<Account> accountsAll = new ArrayList<Account>();
        accountsAll = accountService.findAll();
        logger.info("accountAll" + accountsAll);
        
        
        model.addAttribute("transactionsAll", transactionsAll);
   
        return TRANS_ALL;
    }
    
    @RequestMapping("/all/{id}")
    public String details(@PathVariable Integer id, Model model) {
    	
    	Transaction trans = transactionService.findById(id);
    	
    	model.addAttribute("id",trans.getId());
    	model.addAttribute("sourceIban",trans.getSourceAccount().getIban());
	    model.addAttribute("destinationIban", trans.getDestinationIban());
        model.addAttribute("balance", trans.getBalance());
        model.addAttribute("status", trans.getStatus());
        model.addAttribute("time", trans.getCurrentTime());
        model.addAttribute("verified", trans.getVerified());
 
        return TRANS_DETAILS;
    }

}
