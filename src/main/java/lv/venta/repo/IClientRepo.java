package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Client;
import lv.venta.model.enums.City;


public interface IClientRepo extends CrudRepository<Client, Long> {

	public abstract ArrayList<Client> findByAddressCity(City city);

}
