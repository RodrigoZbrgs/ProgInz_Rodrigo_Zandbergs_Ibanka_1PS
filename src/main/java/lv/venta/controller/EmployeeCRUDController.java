package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lv.venta.model.Employee;
import lv.venta.service.IEmployeeCrudService;


@Controller
@RequestMapping("/employee/crud")

public class EmployeeCRUDController {
	
	@Autowired
	private IEmployeeCrudService employeeService;
	
	@GetMapping("/all") 
	public String getControllerSelectAllEmployees(Model model) {
		try {
			ArrayList<Employee> employeesFromDB = employeeService.selectAllEmployees();
			model.addAttribute("package", employeesFromDB);
			return "show-all-employees";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/all/{id}")
	public String getControllerSelectEmployeeById(@PathVariable(name = "id") int id, Model model) {
		try {
			Employee employeesFromDB = employeeService.selectEmployeeById(id);
			model.addAttribute("package", employeesFromDB);
			return "show-one-employee";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}

	}
	
	@GetMapping("/remove/{id}")
	public String getControllerEmployeeForRemoval(@PathVariable(name = "id") int id, Model model) {
		try {
			employeeService.deleteEmployeeById(id);
			ArrayList<Employee> EmployeesFromDB = employeeService.selectAllEmployees();
			model.addAttribute("package", EmployeesFromDB);
			return "show-all-employees";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	
	@GetMapping("/add")
	public String getControllerForEmployeeAdding(Model model) {
		try {

			model.addAttribute("employee", new Employee());
			return "add-one-employee";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@PostMapping("/add")
	public String postControllerForEmployeeAdding(@Valid Employee employee, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-one-employee";
		} else {
			try {

				employeeService.insertNewEmployee(employee);
				return "redirect:/employee/crud/all";

			} catch (Exception e) {
				model.addAttribute("package", e.getMessage());
				return "error-page";
			}
		}
	}
	
	@GetMapping("/update/{id}")
	public String retrieveUpdateEmployeeById(@PathVariable(name = "id") int id, Model model) {
		try {
			Employee employeeFromDB = employeeService.selectEmployeeById(id);
			model.addAttribute("employee", employeeFromDB);
			return "update-one-employee";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@PostMapping("/update/{id}")
	public String postControllerForUpdateById(@PathVariable(name = "id") int id, Employee employee, Model model) {
		try {
			employeeService.updateByEid(id, employee.getApprovalLimit(), employee.getName(), employee.getPersonCode(),
					employee.getSurname(), employee.getStatus());
			return "redirect:/employee/crud/all/" + id;
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
}
