package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.AutomaticPayment;

public interface IAutomaticPaymentRepo extends CrudRepository<AutomaticPayment, Long>{

}
