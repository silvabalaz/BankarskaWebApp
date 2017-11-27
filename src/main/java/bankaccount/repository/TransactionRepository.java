package bankaccount.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import bankaccount.model.Account;
import bankaccount.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    Transaction save(Transaction newTransaction);
    List<Transaction> findAll();
   // List<Transaction> findAllByIban(int iban);
    //List<Transaction> findAllByStatus(int iban, String status);
}
