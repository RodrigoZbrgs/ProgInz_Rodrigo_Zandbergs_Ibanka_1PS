package lv.venta.service;
import java.util.ArrayList;

import lv.venta.model.Employee;
import lv.venta.model.enums.EmployeeStatus;


public interface IEmployeeCrudService extends ICrudBaseService<Employee>{

	public abstract void updateByEid(long id, int approvalLimit, String name, String personCode, String surname, EmployeeStatus status)
			throws Exception;

	public abstract Employee insertNewEmployee(Employee newEmployee) throws Exception;

	public abstract void deleteEmployeeById(long id) throws Exception;

	public abstract Employee selectEmployeeById(long id) throws Exception;

	public abstract ArrayList<Employee> selectAllEmployees() throws Exception;
	
}
