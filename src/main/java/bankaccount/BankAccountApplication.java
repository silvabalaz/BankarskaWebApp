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
import bankaccount.web.NewClientController;

@SpringBootApplication
public class BankAccountApplication {
	
	  
    @Autowired
    private AccountService accountService; 
	
	private static Logger log = LoggerFactory.getLogger(NewClientController.class);
	 
	public static void main(String[] args) {
		SpringApplication.run(BankAccountApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ClientRepository clientRepository) {
		return (args) -> { 
			//save a couple of customers
			
			Role roleUser = Role.USER;
			Role roleAdmin = Role.ADMIN;
			
			Client client1 = new Client("Jana", "1234",roleUser);
			Client client2 = new Client("Ivica", "1234",roleUser);
			
			Client admin = new Client("banka", "banka",roleAdmin);
	
			clientRepository.save(client1);
			clientRepository.save(client2);
			clientRepository.save(admin);
			
			Account account1 = client1.getAccount();
			Account account2 = client2.getAccount();
			Account accountA = admin.getAccount();
			
			account1.setIban((long)111111111);
			account2.setIban((long)111111110);
			accountA.setIban((long)100000000);
			accountA.setBalance((double)0);
			
			accountService.save(account1);
			accountService.save(account2);
			accountService.save(accountA);
			
			for (Client c : clientRepository.findAll()) {
				log.info("svi racuni _______________  " + c.toString() + c.getUsername());
			} 
		
	
		};
	}

	}


