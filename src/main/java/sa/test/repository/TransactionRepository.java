package sa.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sa.test.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
