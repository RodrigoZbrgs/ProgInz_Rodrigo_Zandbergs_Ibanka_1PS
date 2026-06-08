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

@Table(name = "LargeTransactionTable")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class LargeTransaction {
	@Column(name = "Idltr")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idltr;
	
	@Column(name = "amount")
	@Min(300)
	@Max(1000000000)
	@NotNull
	private float largeAmount;
	
	@Column(name = "date_time")
	@NotNull
	private LocalDateTime dateTime;
	
	@Column(name = "description")
	@NotEmpty
	private String description;
	
	public LargeTransaction(float largeAmount, LocalDateTime dateTime, String description) {
		setLargeAmount(largeAmount);
		setDateTime(dateTime);
		setDescription(description);
	}
}
