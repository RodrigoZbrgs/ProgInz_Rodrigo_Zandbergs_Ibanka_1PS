package lv.venta.repo;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Transaction;

public interface ITransactionRepo extends CrudRepository<Transaction, Long> {

	@Query(nativeQuery = true, value = "SELECT AVG(amount) FROM (" +
		    "SELECT amount FROM transaction_table " +
		    "UNION ALL " +
		    "SELECT amount FROM automatic_payment " +
		    "UNION ALL " +
		    "SELECT amount FROM large_transaction_table" +
		    ") AS all_transactions")

	public abstract float calculateAVGPAmountFromDB();

	public abstract ArrayList<Transaction> findByAmountGreaterThan(BigDecimal amount);

}
