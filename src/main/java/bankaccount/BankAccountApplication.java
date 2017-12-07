package bankaccount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import bankaccount.model.Account;
import bankaccount.model.Transaction;
import bankaccount.repository.AccountRepository;
import bankaccount.repository.TransactionRepository;
import bankaccount.web.NewClientController;

@SpringBootApplication
public class BankAccountApplication {
	
	private static Logger log = LoggerFactory.getLogger(NewClientController.class);
	 
	public static void main(String[] args) {
		SpringApplication.run(BankAccountApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(AccountRepository repository) {
		return (args) -> { 
			// save a couple of customers
			//Account prvi = new Account();	
			/*
			Account a1 = new Account("Jack", "Bauer", 12345);
			Account a3 = new Account("Jacsrfsk", "Bauggger", 127673);
			Account a5 = new Account("Jacjgjgk", "Baugggjer", 12453);
		    //repository.save(prvi);
			repository.save(a1);
			repository.save(a3);
			repository.save(a5);
		
			log.info("a1,a2,a3,a4,a5" + a1+ a3 + a5);
			for (Account account : repository.findAll()) {
				log.info("svi racuni _______________  " + account.toString());
			} 
		*/
	
		};
	}

	}


