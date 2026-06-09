package lv.venta.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "LargeTransactionTable")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class LargeTransaction extends Transaction {

	@Column(name = "is_accepted")
	private boolean isAccepted;
	
	@ManyToOne
	@JoinColumn(name = "Eid")
	private Employee employee;

	
	public LargeTransaction(BigDecimal amount, boolean isAccepted, LocalDateTime dateTime, String description) {
		setAmount(amount);
		setAccepted(isAccepted);
		setDateTime(dateTime);
		setDescription(description);

	}
}
