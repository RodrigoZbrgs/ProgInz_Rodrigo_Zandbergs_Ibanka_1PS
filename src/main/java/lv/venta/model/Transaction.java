package lv.venta.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
@ToString
public class Transaction {
	
	@Column(name = "Idtr")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idtr;
	
	@Column(name = "amount")
	@Min(0)
	@Max(300)
	@NotNull
	private float amount;
	
	@Column(name = "date_time")
	@NotNull
	private LocalDateTime dateTime;
	
	@Column(name = "description")
	@NotEmpty
	private String description;
	
	public Transaction(float amount, LocalDateTime dateTime, String description) {
		setAmount(amount);
		setDateTime(dateTime);
		setDescription(description);
	}
}
