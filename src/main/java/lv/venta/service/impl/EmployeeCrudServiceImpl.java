package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Employee;
import lv.venta.model.LargeTransaction;
import lv.venta.model.enums.EmployeeStatus;
import lv.venta.repo.IEmployeeRepo;
import lv.venta.repo.ILargeTransactionRepo;
import lv.venta.service.IEmployeeCrudService;

@Service
public class EmployeeCrudServiceImpl implements IEmployeeCrudService {


	@Autowired
	private IEmployeeRepo employeeRepo;

	@Autowired
	private ILargeTransactionRepo largeTrRepo;
	
	@Override
	public ArrayList<Employee> selectAllEmployees() throws Exception {
		if (employeeRepo.count() == 0) {
			throw new Exception("Darbinieku tabula ir tukša");
		}
		return (ArrayList<Employee>) employeeRepo.findAll();
	}
	
	@Override
	public Employee selectEmployeeById(long id) throws Exception {
		if (employeeRepo.count() == 0) {
			throw new Exception("Studentu tabula ir tukša un nevar atgriezt id");
		}

		if (id < 1) {
			throw new Exception("Id nevar būt negatīvs vai 0");
		}

		if (!employeeRepo.existsById(id)) {
			throw new Exception("Students ar id" + id + "neeksiste");
		}

		return employeeRepo.findById(id).get();
	}
	
	@Override
	public void deleteEmployeeById(long id) throws Exception {
	    Employee employeeForDeletion = selectEmployeeById(id);

	    ArrayList<LargeTransaction> allTransactionsForEmployee = largeTrRepo.findByEmployeeEid(id);

	    for (LargeTransaction tempT : allTransactionsForEmployee) {
	        tempT.setEmployee(null); 
	        largeTrRepo.save(tempT);
	    }

	    employeeRepo.delete(employeeForDeletion);
	}
	@Override	
	public Employee insertNewEmployee(Employee newEmployee) throws Exception {
		if(newEmployee == null) {
			throw new Exception("Produkta dati nav piejami, jo nav reference");
		}
		
		if (employeeRepo.existsByPersonCode(newEmployee.getPersonCode())) {
		    throw new Exception("Produkts ar tādu nosaukumu jau eksistē");
		}
		
		if(newEmployee.getName() == null || newEmployee.getSurname() == null
				|| newEmployee.getApprovalLimit() < 0|| newEmployee.getPersonCode() == null
				|| newEmployee.getStatus() == null) {
			throw new Exception("Nav korekti ievades dati");
		}

		return employeeRepo.save(newEmployee);
	}
	
	
	@Override
	public void updateByEid(long id, int approvalLimit, String name, String personCode, String surname, EmployeeStatus status) throws Exception {
	    Employee employeeToUpdate = selectEmployeeById(id);

		if(approvalLimit < 0 || name == null || personCode == null
				|| surname == null || status == null) {
			throw new Exception("Nav korekti ievades dati");
		}
	    
	    if (approvalLimit != 0) {
	    	employeeToUpdate.setApprovalLimit(approvalLimit);
	    }
	    if (name != null) {
	    	employeeToUpdate.setName(name);
	    }
	    if (personCode != null) {
	    	employeeToUpdate.setPersonCode(personCode);
	    }
	    if (surname != null) {
	    	employeeToUpdate.setSurname(surname);
	    }
	    if (status != null) {
	    	employeeToUpdate.setStatus(status);
	    }

	    employeeRepo.save(employeeToUpdate);
	}

}
