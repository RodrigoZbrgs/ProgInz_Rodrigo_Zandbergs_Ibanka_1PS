package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Transaction;

public interface ITransactionRepo extends CrudRepository<Transaction, Long> {

}
