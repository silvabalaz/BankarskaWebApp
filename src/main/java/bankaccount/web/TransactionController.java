package bankaccount.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bankaccount.model.Account;
import bankaccount.model.Transaction;
import bankaccount.repository.TransactionRepository;
import bankaccount.service.AccountService;
import bankaccount.service.ClientService;
import bankaccount.service.Util;
import bankaccount.service.TransactionService;

@Controller
@RequestMapping("/transactioncreate")
public class TransactionController {
	
    private static final String TRANS_VIEW = "transaction_create";
    private static final String TRANS_LIST = "transaction_list";
 
    private static Logger logger = LoggerFactory.getLogger(TransactionController.class);
    
    @Autowired
    private ClientService clientService; 
    @Autowired
    private TransactionService transactionService; 
	
	 @RequestMapping(method = RequestMethod.GET)
	    public String Form(@ModelAttribute("clientInfo")ClientDto clientInfo, Model model) {
		 
	    	TransactionDto trans = new TransactionDto();
	    	
	    	if(clientService.findIbanByUsername(clientInfo.getUsername()) !=(long)0  ){
	    		
	    		long currentIban = clientService.findIbanByUsername(clientInfo.getUsername());
	    		trans.setSourceIban(currentIban);
    		    model.addAttribute("transactionInfo",trans);
    		    return TRANS_VIEW;
	    	}
	    	else {
	    		model.addAttribute("userAdmin","User je admin");
        
    		    return "redirect:/admin/all";
	    	}
	    }
	 
	
    @RequestMapping(method = RequestMethod.POST)
    public String transaction(@ModelAttribute("transactionInfo")TransactionDto transactionInfo, Model model, RedirectAttributes redirectAttributes) {
    	
    	if (clientService.findBalanceByIban(transactionInfo.getSourceIban()) < transactionInfo.getAmount()) 
    		return "error";
    	
    	long currentSourceIban = transactionInfo.getSourceIban();
    	
    	Account findAccount = clientService.findByIban(currentSourceIban);
    	
    	Transaction newTransaction = new Transaction(findAccount,transactionInfo.getDestinationIban(),"zadan",transactionInfo.getAmount());
    	
        if(transactionService.save(newTransaction) != false){

           //service.saveChanges(transactionInfo.getSourceIban(),transactionInfo.getDestinationIban(),transactionInfo.getAmount() );
           
           redirectAttributes.addFlashAttribute("transactionInfo", transactionInfo);
			
           return "redirect:/transactioncreate/list";
        }
        
       return "error";
    } 
    	
	 @RequestMapping(value = "list", method = RequestMethod.GET)
	    public String listTransactions(@ModelAttribute("transactionInfo")TransactionDto transactionInfo, Model model) {
		     	
	    	long currentIban = transactionInfo.getSourceIban();
	    	
	        model.addAttribute("transactionInfo", currentIban);
	    
	        List<Transaction> transactionsMyIbanZadan = new ArrayList<Transaction>();
	        List<Transaction> transactionsMyIbanIzvrsen = new ArrayList<Transaction>(); 
	        List<Transaction> transactionsMyIbanOdbijen = new ArrayList<Transaction>(); 
	        
	        transactionsMyIbanZadan = transactionService.findAllByStatus(currentIban,"zadan");
	        transactionsMyIbanIzvrsen = transactionService.findAllByStatus(currentIban,"izvrsen");
	        transactionsMyIbanOdbijen = transactionService.findAllByStatus(currentIban,"odbijen");
	        /*Drop-Down list*/
	        model.addAttribute("transactionsZadan",  transactionsMyIbanZadan);
	        model.addAttribute("transactionsIzvrsen",transactionsMyIbanIzvrsen);
	        model.addAttribute("transactionsOdbijen", transactionsMyIbanOdbijen);
	        
	        
	        logger.info("transactionsMyIbanStatus", transactionsMyIbanZadan);
	        logger.info("serviceT.findAllByStatus(currentIban,status)", transactionsMyIbanZadan);
	  
	        
	        return TRANS_LIST;
	    }
	 
	 
}
