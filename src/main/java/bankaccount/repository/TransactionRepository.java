package bankaccount.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import bankaccount.model.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

	Transaction save(Transaction newTransaction);
    List<Transaction> findAll();
    //List<Transaction> findAllBysourceIban(int iban);
    List<Transaction> findAllByStatus(int iban, String status);
}
