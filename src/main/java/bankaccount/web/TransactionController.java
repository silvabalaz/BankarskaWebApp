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
import bankaccount.service.PasswordService;
import bankaccount.service.TransactionService;

@Controller
@RequestMapping("/transactioncreate")
public class TransactionController {
	
    private static final String TRANS_VIEW = "transaction_create";
    private static final String TRANS_LIST = "transaction_list";
 
    private static Logger logger = LoggerFactory.getLogger(TransactionController.class);
    
    @Autowired
    private AccountService service; 
    @Autowired
    private TransactionService serviceT; 
    @Autowired
    private TransactionRepository repository;
	
	 @RequestMapping(method = RequestMethod.GET)
	    public String Form(@ModelAttribute("accountInfo")AccountDto accountInfo, Model model) {
		 
	    	TransactionDto trans = new TransactionDto();
	    	
	    	int currentIban = service.findIbanByUsername(accountInfo.getUsername());
	    	
	    	trans.setSourceIban(currentIban);
	    	
	        model.addAttribute("transactionInfo",trans);
	        
	        return TRANS_VIEW;
	    }
	 
	
    @RequestMapping(method = RequestMethod.POST)
    public String transaction(@ModelAttribute("transactionInfo")TransactionDto transactionInfo, Model model, RedirectAttributes redirectAttributes) {

    	Transaction newTransaction = new Transaction(transactionInfo.getSourceIban(),transactionInfo.getDestinationIban(),"zadan",transactionInfo.getAmount());
    	
        if(serviceT.saveTransaction(newTransaction) != false){

           redirectAttributes.addFlashAttribute("transactionInfo", transactionInfo);
			
           return "redirect:/transactioncreate/list";
        }
        
       return "error";
    } 
    	
	 @RequestMapping(value = "list", method = RequestMethod.GET)
	    public String listTransactions(@ModelAttribute("transactionInfo")TransactionDto transactionInfo ,Model model) {
		     	
	    	int currentIban = transactionInfo.getSourceIban();
	    	String status = "zadan";
	    		
	        model.addAttribute("transactionInfo", currentIban);
	        model.addAttribute("status", status);
	        
	        //List<Transaction> transactionsMyIban = serviceT.findAllByIban(currentIban);
	        List<Transaction> transactionsMyIbanStatus = new ArrayList();
	        transactionsMyIbanStatus = serviceT.findAllByStatus(currentIban,status);
	        logger.info("transactionsMyIbanStatus", transactionsMyIbanStatus);
	        logger.info("serviceT.findAllByStatus(currentIban,status)", serviceT.findAllByStatus(currentIban,status));
	        
	        return TRANS_LIST;
	    }
	 
	 
}
