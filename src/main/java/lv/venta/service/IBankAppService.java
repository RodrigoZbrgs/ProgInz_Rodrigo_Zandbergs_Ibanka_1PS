package lv.venta.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import lv.venta.model.BankAccount;
import lv.venta.model.Client;
import lv.venta.model.Transaction;
import lv.venta.model.enums.City;

public interface IBankAppService {

	public abstract Transaction insertNewTransactionForClient(Transaction newTransaction) throws Exception;

	public abstract ArrayList<Transaction> selectAllTransactions() throws Exception;

	public abstract ArrayList<Client> selectAllClients() throws Exception;

	public abstract ArrayList<Client> selectAllClientsFromCity(City city) throws Exception;

	public abstract ArrayList<Transaction> selectAllTransactionsTypesWhereAmountLargerThan(BigDecimal amount) throws Exception;

	public abstract float calculateAverageAmountToday() throws Exception;

	public abstract ArrayList<BankAccount> selectAllBankAccounts() throws Exception;


	

}
