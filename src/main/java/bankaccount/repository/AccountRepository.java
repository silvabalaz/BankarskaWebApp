package bankaccount.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import bankaccount.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
	
	Account save(Account account);
    Account findByIban(long iban);
    double findBalanceByIban(long iban); 
}

