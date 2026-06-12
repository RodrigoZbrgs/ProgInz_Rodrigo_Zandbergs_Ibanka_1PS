package lv.venta;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.model.Address;
import lv.venta.model.AutomaticPayment;
import lv.venta.model.BankAccount;
import lv.venta.model.Card;
import lv.venta.model.Client;
import lv.venta.model.Employee;
import lv.venta.model.LargeTransaction;
import lv.venta.model.Transaction;
import lv.venta.model.enums.AutomaticPaymentScheduleType;
import lv.venta.model.enums.City;
import lv.venta.model.enums.EmployeeStatus;
import lv.venta.repo.IAddressRepo;
import lv.venta.repo.IAutomaticPaymentRepo;
import lv.venta.repo.IBankAccountRepo;
import lv.venta.repo.ICardRepo;
import lv.venta.repo.IClientRepo;
import lv.venta.repo.IEmployeeRepo;
import lv.venta.repo.ILargeTransactionRepo;
import lv.venta.repo.ITransactionRepo;

@SpringBootApplication
public class ProgInzRodrigoZandbergsIbankaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgInzRodrigoZandbergsIbankaApplication.class, args);
	}

	@Bean
	public CommandLineRunner saveDatainDB(IAddressRepo adrRepo, IAutomaticPaymentRepo autoPayRepo,
			IBankAccountRepo bankRepo, ICardRepo cardRepo, IClientRepo clientRepo, IEmployeeRepo employeeRepo,
			ILargeTransactionRepo largeTrRepo, ITransactionRepo tranRepo) {

		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {

				if (adrRepo.count() == 0) {
					// Address
					Address a1 = new Address(12, "Lielais prospekts", City.Ventspils);
					Address a2 = new Address(101, "Inženieru iela", City.Rēzekne);
					adrRepo.saveAll(Arrays.asList(a1, a2));

					// Clients
					Client cl1 = new Client("O_B_121289-98765", "baiba.ozola@gmail.com", "Baiba", "121289-98765",
							"Ozola");
					Client cl2 = new Client("K_K_120289-98865", "kaspars.kalnins@gmail.com", "Kaspars", "120289-98865",
							"Kalniņš");
					Client cl3 = new Client("P_J_090289-98865", "juris.priede@gmail.com", "Juris", "090289-98865",
							"Priede");

					cl1.setAddress(a1);
					cl2.setAddress(a2);
					cl3.setAddress(a2);
					clientRepo.saveAll(Arrays.asList(cl1, cl2, cl3));

					// Employees
					Employee e1 = new Employee(1000, "Jānis", "121212-12345", "Bērziņš", EmployeeStatus.active);
					Employee e2 = new Employee(10000, "Līga", "121213-12344", "Jaukā", EmployeeStatus.on_leave);
					employeeRepo.saveAll(Arrays.asList(e1, e2));

					// Bank accounts
					BankAccount b1 = new BankAccount(true, 500, "LV00UNLA0000004000000");
					BankAccount b2 = new BankAccount(true, 1300,"LV00UNLA0000004000001");
					BankAccount b3 = new BankAccount(true, 2300,"LV00UNLA0000004000002");
					BankAccount b4 = new BankAccount(true, 4300,"LV00UNLA0000004000003");

					b1.setClient(cl1);
					b2.setClient(cl1);
					b3.setClient(cl2);
					b4.setClient(cl3);

					bankRepo.saveAll(Arrays.asList(b1, b2, b3, b4));

					// Cards
					Card c1 = new Card("101", LocalDate.of(2026, 6, 27), "1234098761233487");
					Card c2 = new Card("678", LocalDate.of(2026, 9, 27), "1234098761233487");
					Card c3 = new Card("987", LocalDate.of(2027, 1, 27), "1234098761233487");

					c1.setBank(b1);
					c2.setBank(b3);
					c3.setBank(b4);

					cardRepo.saveAll(Arrays.asList(c1, c2, c3));

					// Transaction
					Transaction t1 = new Transaction(BigDecimal.valueOf(5),
							LocalDateTime.of(2026, 4, 27, 18, 32, 48, 057442), "Par pusdienām");
					Transaction t2 = new Transaction(BigDecimal.valueOf(8.99),
							LocalDateTime.of(2026, 4, 27, 18, 32, 48, 057442), "Par kurpēm");
					Transaction t3 = new Transaction(BigDecimal.valueOf(9.99),
							LocalDateTime.of(2026, 4, 27, 18, 32, 48, 057442), "Par cienastu");
					
					t1.setSenderAccount(b1);
					t1.setReceiverAccount(b4);
					
					t2.setSenderAccount(b2);
					t2.setReceiverAccount(b4);
					
					t3.setSenderAccount(b3);
					t3.setReceiverAccount(b2);
					
					tranRepo.saveAll(Arrays.asList(t1, t2, t3));

					// Automatic payment
					AutomaticPayment ap1 = new AutomaticPayment(true, AutomaticPaymentScheduleType.Monthly,
							BigDecimal.valueOf(15), LocalDate.of(2026, 5, 1),
							LocalDateTime.of(2026, 4, 27, 18, 32, 48, 128015), "Par elektrību", "Elektrības maksājums");
					AutomaticPayment ap2 = new AutomaticPayment(true, AutomaticPaymentScheduleType.Monthly,
							BigDecimal.valueOf(99), LocalDate.of(2026, 5, 7),
							LocalDateTime.of(2026, 4, 27, 18, 32, 48, 128015), "Par kredītu", "Kredīta maksājums");
					
					ap1.setSenderAccount(b4);
					ap1.setReceiverAccount(b1);
					
					ap2.setSenderAccount(b3);
					ap2.setReceiverAccount(b1);
					
					autoPayRepo.saveAll(Arrays.asList(ap1, ap2));

					// Large transactions
					LargeTransaction lt1 = new LargeTransaction(BigDecimal.valueOf(2000), true,
							LocalDateTime.of(2026, 4, 27, 18, 32, 48, 153104), "Par jauno mašīnu");
					LargeTransaction lt2 = new LargeTransaction(BigDecimal.valueOf(9000), true,
							LocalDateTime.of(2026, 4, 27, 18, 32, 48, 153104), "Par jauno dzīvokli");
					
					lt1.setSenderAccount(b3);
					lt1.setReceiverAccount(b4);
					
					lt2.setSenderAccount(b1);
					lt2.setReceiverAccount(b3);
					
					lt1.setEmployee(e2);
					lt2.setEmployee(e2);
					
					largeTrRepo.saveAll(Arrays.asList(lt1, lt2));
				}
			}
		};
	}
}
