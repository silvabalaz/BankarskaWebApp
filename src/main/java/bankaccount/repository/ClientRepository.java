package bankaccount.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bankaccount.model.Account;
import bankaccount.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer>{
	
    public Client save(Client client);
    public Client findByUsername(String username);
    public Client findIbanByAccount(Account account);
    //long findIbanByUsername(String username);
   // double findBalanceByIban(long iban);
	

}
