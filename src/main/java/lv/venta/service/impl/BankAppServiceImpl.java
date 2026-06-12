package lv.venta.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.BankAccount;
import lv.venta.model.Client;
import lv.venta.model.Transaction;
import lv.venta.model.enums.City;
import lv.venta.repo.IBankAccountRepo;
import lv.venta.repo.IClientRepo;
import lv.venta.repo.ITransactionRepo;
import lv.venta.service.IBankAppService;

@Service
public class BankAppServiceImpl implements IBankAppService {
	
	@Autowired
	private ITransactionRepo tranRepo;
	
	@Autowired
	private IBankAccountRepo bankRepo;
	
	@Autowired
	private IClientRepo clientRepo;
	
	//selectAllBankAccounts
	@Override
	public ArrayList<BankAccount> selectAllBankAccounts() throws Exception {
		if(bankRepo.count() == 0) {
			throw new Exception("Banku tabula ir tukša");
		}
		return(ArrayList<BankAccount>) bankRepo.findAll();
	}
	
	//selectAllClients
	@Override
	public ArrayList<Client> selectAllClients() throws Exception {
		if(clientRepo.count() == 0) {
			throw new Exception("Klientu tabula ir tukša");
		}
		return(ArrayList<Client>) clientRepo.findAll();
	}
	
	//selectAllTransactions
	@Override
	public ArrayList<Transaction> selectAllTransactions() throws Exception {
		if (tranRepo.count() == 0) {
			throw new Exception("Darbinieku tabula ir tukša");
		}
		return (ArrayList<Transaction>) tranRepo.findAll();
	}
	
	//selectAllTransactionsTypesWhereAmountLargerThan
	@Override
	public ArrayList<Transaction> selectAllTransactionsTypesWhereAmountLargerThan(BigDecimal amount) throws Exception {
		if (amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new Exception("Ievades dati nav korekti");
		}

		if (tranRepo.count() == 0) {
			throw new Exception("DB nav produktu, tāpēc neko nevar filtrēt");
		}

		ArrayList<Transaction> filteredTransactions = tranRepo.findByAmountGreaterThan(amount);
		if (filteredTransactions.isEmpty()) {
			throw new Exception("Nav neviena produkts kura cena ir mazaka par " + amount + "eur");
		}
		return filteredTransactions;
	}

	//selectAllClientsFromCity
	@Override
	public ArrayList<Client> selectAllClientsFromCity(City city) throws Exception {
		if (clientRepo.count() == 0) {
			throw new Exception("Studentu tabula ir tukša un nevar atgriezt id");
		}

		if (city == null) {
			throw new Exception("Id nevar būt negatīvs vai 0");
		}

		return (ArrayList<Client>) clientRepo.findByAddressCity(city);
	}
	
	@Override
	public float calculateAverageAmountToday() throws Exception {
		if (tranRepo.count() == 0) {
			throw new Exception("DB ir tukšs, nevar aprēķināt");
		}
		
		float avgAmount = tranRepo.calculateAVGPAmountFromDB();
		
		return avgAmount;
	}

	//insertNewTransactionForClient
	@Override	
	public Transaction insertNewTransactionForClient(Transaction newTransaction) throws Exception {
		if(newTransaction == null) {
			throw new Exception("Darījuma dati nav pieejami, jo nav reference");
		}
		
		if(newTransaction.getAmount() == null || newTransaction.getDateTime() == null
				|| newTransaction.getDescription() == null|| newTransaction.getSenderAccount() == null
				|| newTransaction.getReceiverAccount() == null) {
			throw new Exception("Nav korekti ievades dati");
		}

		return tranRepo.save(newTransaction);
	}


	
}
