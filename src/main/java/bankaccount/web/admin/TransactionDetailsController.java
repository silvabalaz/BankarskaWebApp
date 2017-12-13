package bankaccount.web.admin;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bankaccount.service.AccountService;
import bankaccount.service.ClientService;
import bankaccount.service.TransactionService;
import bankaccount.web.TransactionController;
import bankaccount.web.TransactionDto;
import bankaccount.model.Account;
import bankaccount.model.Client;
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
    private ClientService clientService; 
    
    @Autowired
    private AccountService accountService; 
	
    @RequestMapping("/all")
    public String all(Model model) {
    	
    	 
        List<Transaction> transactionsAllByAdminZadan = new ArrayList<Transaction>();
        List<Transaction> transactionsAllByAdminIzvrsen = new ArrayList<Transaction>(); 
        List<Transaction> transactionsAllByAdminOdbijen = new ArrayList<Transaction>(); 
        
        long adminIban = (long)100000000;
        logger.info("adminIban" + adminIban);
        Client admin = clientService.findByUsername("banka");
        Account adminAccount = admin.getAccount();
        adminAccount.setIban(adminIban);
        
        transactionsAllByAdminZadan = transactionService.findAllByStatus(adminIban,"zadan");
        transactionsAllByAdminIzvrsen = transactionService.findAllByStatus(adminIban,"izvrsen");
        transactionsAllByAdminOdbijen = transactionService.findAllByStatus(adminIban,"odbijen");
        /*Drop-Down list*/
        model.addAttribute("transactionsZadan",  transactionsAllByAdminZadan);
        model.addAttribute("transactionsIzvrsen",transactionsAllByAdminIzvrsen);
        model.addAttribute("transactionsOdbijen", transactionsAllByAdminOdbijen);
        logger.info("transactionsAllByAdminZadan" + transactionsAllByAdminZadan);
        logger.info("transactionService.findAllByStatus(status)" + transactionsAllByAdminZadan);
   
        return TRANS_ALL;
    }
    
    @RequestMapping("/all/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
    	
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
    
    @RequestMapping(value = "/all/{id}/izvrsi")
    public String izvrsi(@PathVariable("id") Integer id, Model model) {
    	
    	boolean success = false;
    	
    	if (transactionService.findById(id) != null) {
    		
	    	success = true;
	    	
	    	Transaction trans = transactionService.findById(id);
	    	
	    	trans.setStatus("izvrsen");
	    	//uvecava se racun primatelja
	    	//umanjuje se racun platitelja
	    	
	    	Account source = trans.getSourceAccount();
	    	double newBalace = source.getBalance() - trans.getBalance();
	    	source.setBalance(newBalace);
	    	Long destination = trans.getDestinationIban();
	    	Account	destAccount = clientService.findByIban(destination); 
	    	double newDest = destAccount.getBalance() + trans.getBalance();
	    	destAccount.setBalance(newDest);
	    	
			accountService.save(source);
	    	accountService.save(destAccount);
	    	transactionService.save(trans);
	    	
    		return "redirect:/transaction/all";
    	}
    	else 
    		model.addAttribute("isSucess", success);
    
 
        return TRANS_DETAILS;
    }
    
    @RequestMapping(value = "/all/{id}/odbij")
    public String odbij(@PathVariable("id") Integer id, Model model) {
    	
    	boolean success = false;
    	
    	if (transactionService.findById(id) != null) {
    		
	    	success = true;
	    	
	    	Transaction trans = transactionService.findById(id);
	    	
	    	trans.setStatus("odbijen");
	    	
	    	transactionService.save(trans);
	    	
    		return "redirect:/transaction/all";
    	}
    	else 
    		model.addAttribute("isSucess", success);
    
 
        return TRANS_DETAILS;
    }

}
