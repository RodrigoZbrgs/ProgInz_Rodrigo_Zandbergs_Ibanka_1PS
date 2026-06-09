package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.LargeTransaction;

public interface ILargeTransactionRepo extends CrudRepository<LargeTransaction, Long> {

	ArrayList<LargeTransaction> findByEmployeeEid(long id);

}
