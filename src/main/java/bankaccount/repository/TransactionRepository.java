package bankaccount.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import bankaccount.model.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

	Transaction save(Transaction newTransaction);
	Transaction findById(int id);
    List<Transaction> findAll();
    List<Transaction> findAllByStatus(int iban, String status);
}
