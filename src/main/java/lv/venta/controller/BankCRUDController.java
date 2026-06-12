package lv.venta.controller;

import java.math.BigDecimal;
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
import lv.venta.model.BankAccount;
import lv.venta.model.Client;
import lv.venta.model.Transaction;
import lv.venta.model.enums.City;
import lv.venta.repo.ITransactionRepo;
import lv.venta.service.IBankAppService;


@Controller
@RequestMapping("/bank/crud")
public class BankCRUDController {
	
	@Autowired
	private ITransactionRepo tranRepo;

	@Autowired
	private IBankAppService bankService;
	
	
	@GetMapping("/accounts/all") 
	public String getControllerSelectAllBankAccounts(Model model) {
		try {
			ArrayList<BankAccount> bankAccountsFromDB = bankService.selectAllBankAccounts();
			model.addAttribute("package", bankAccountsFromDB);
			return "show-all-bank-accounts";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/clients/all") 
	public String getControllerSelectAllClients(Model model) {
		try {
			ArrayList<Client> clientsFromDB = bankService.selectAllClients();
			model.addAttribute("package", clientsFromDB);
			return "show-all-clients";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/transactions/all") 
	public String getControllerSelectAllTransactions(Model model) {
		try {
			ArrayList<Transaction> transactionsFromDB = bankService.selectAllTransactions();
			model.addAttribute("package", transactionsFromDB);
			return "show-all-transactions";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/transactions/amount/{amount}")
	public String getControllerSelectAllTransactionsTypesWhereAmountLargerThan(@PathVariable(name = "amount") BigDecimal amount, Model model) {
		try {
	        ArrayList<Transaction> transactionsFromDB = bankService.selectAllTransactionsTypesWhereAmountLargerThan(amount);
	        model.addAttribute("package", transactionsFromDB);
	        return "show-all-transactions";
	    } catch (Exception e) {
	        model.addAttribute("package", e.getMessage());
	        return "error-page";
	    }
	}
	
	@GetMapping("/clients/city/{city}")
	public String getControllerSelectAllClientsByCity(@PathVariable(name = "city") City city, Model model) {
		try {
			ArrayList<Client> clientsFromCityDB = bankService.selectAllClientsFromCity(city);
			model.addAttribute("package", clientsFromCityDB);
			return "show-all-clients";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/avgamount")
	public String getControllerAveragePriceCalculator(Model model) {
		try {
			float avgPrice = tranRepo.calculateAVGPAmountFromDB();
			model.addAttribute("package", avgPrice);
			model.addAttribute("info", "Šodienas vidējā darījuma summa: " + avgPrice);
			return "calculate-transaction";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/transactions/add")
	public String getControllerForTransactionAdding(Model model) {
		try {

			model.addAttribute("transaction", new Transaction());
			return "add-one-transaction";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@PostMapping("/transactions/add")
	public String postControllerForTransactionAdding(@Valid Transaction transaction, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-one-transaction";
		} else {
			try {
				bankService.insertNewTransactionForClient(transaction);
				return "redirect:/bank/crud/transactions/all";

			} catch (Exception e) {
				model.addAttribute("package", e.getMessage());
				return "error-page";
			}
		}
	}
}
