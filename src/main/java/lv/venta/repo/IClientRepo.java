package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Client;


public interface IClientRepo extends CrudRepository<Client, Long> {

}
