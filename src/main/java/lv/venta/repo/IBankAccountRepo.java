package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.BankAccount;

public interface IBankAccountRepo extends CrudRepository<BankAccount, Long> {

}
