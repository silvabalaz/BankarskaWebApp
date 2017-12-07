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
import bankaccount.repository.AccountRepository;
import bankaccount.repository.ClientRepository;
import bankaccount.repository.TransactionRepository;
import bankaccount.web.NewClientController;

@SpringBootApplication
public class BankAccountApplication {
	
	private static Logger log = LoggerFactory.getLogger(NewClientController.class);
	 
	public static void main(String[] args) {
		SpringApplication.run(BankAccountApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ClientRepository repository) {
		return (args) -> { 
			//save a couple of customers
			
			
			Client client = new Client("Jana", "1234");
	
			repository.save(client);
	

			log.info("client" + client);
			for (Client c : repository.findAll()) {
				log.info("svi racuni _______________  " + c.toString());
			} 
		
	
		};
	}

	}


