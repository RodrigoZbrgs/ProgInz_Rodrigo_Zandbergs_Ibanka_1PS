package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Employee;
import lv.venta.model.enums.City;

public interface IEmployeeRepo extends CrudRepository<Employee, Long>{

	public abstract boolean existsByPersonCode(String personCode);



}
