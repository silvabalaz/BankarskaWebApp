package bankaccount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import bankaccount.model.Account;
import bankaccount.model.Client;
import bankaccount.model.Transaction;
import bankaccount.model.security.Role;
import bankaccount.repository.AccountRepository;
import bankaccount.repository.ClientRepository;
import bankaccount.repository.TransactionRepository;
import bankaccount.service.AccountService;
import bankaccount.service.TransactionService;
import bankaccount.web.NewClientController;

@SpringBootApplication
public class BankAccountApplication {
	
    @Autowired
    private AccountService accountService; 
    
    @Autowired
    private TransactionService transactionService; 
	
	private static Logger log = LoggerFactory.getLogger(NewClientController.class);
	 
	public static void main(String[] args) {
		SpringApplication.run(BankAccountApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ClientRepository clientRepository) {
		return (args) -> { 
			//save a couple of customers
			
			Role roleUser = Role.ROLE_USER;
			Role roleAdmin = Role.ROLE_ADMIN;
			
			Client client1 = new Client("Jana", "1234",roleUser);
			Client client2 = new Client("Ivica", "1234",roleUser);
			
			Client admin = new Client("banka","banka",roleAdmin);
	
			clientRepository.save(client1);
			clientRepository.save(client2);
			clientRepository.save(admin);
			
			Account account1 = client1.getAccount();
			Account account2 = client2.getAccount();
			
			account1.setIban((long)1111111111);
			account2.setIban((long)1111111110);
			
			Transaction t1 = new Transaction(account1,(long)1111111110, "zadan", (double)500.0);
			Transaction t2 = new Transaction(account2,(long)1111111111, "zadan", (double)700.0);
			Transaction t3 = new Transaction(account2,(long)1111111111, "zadan", (double)1000.0);
			
			accountService.save(account1);
			accountService.save(account2);
			
			transactionService.save(t1);
			transactionService.save(t2);
			transactionService.save(t3);
			
			for (Client c : clientRepository.findAll()) {
				log.info("svi racuni _______________  " + c.toString() + c.getUsername());
			} 
		
	
		};
	}

	}


