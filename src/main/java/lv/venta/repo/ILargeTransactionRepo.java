package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.LargeTransaction;

public interface ILargeTransactionRepo extends CrudRepository<LargeTransaction, Long> {

}
