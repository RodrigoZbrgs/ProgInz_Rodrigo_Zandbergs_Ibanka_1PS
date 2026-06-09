package lv.venta.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "TransactionTable")
@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@ToString
public class Transaction {
	
	@Column(name = "Tid")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
	@SequenceGenerator(name = "transaction_seq", sequenceName = "transaction_seq", allocationSize = 1)
	@Setter(value = AccessLevel.NONE)
	private long tid;
	
	@Column(name = "amount")
	@DecimalMin(value = "0.01")
	@NotNull
	private BigDecimal amount;
	
	@Column(name = "date_time")
	@NotNull
	private LocalDateTime dateTime;
	
	@Column(name = "description")
	@NotEmpty
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "said")
	private BankAccount senderAccount;
	
	@ManyToOne
	@JoinColumn(name = "taid")
	private BankAccount receiverAccount;
	
	
	public Transaction(BigDecimal amount, LocalDateTime dateTime, String description) {
		setAmount(amount);
		setDateTime(dateTime);
		setDescription(description);
	}
}
