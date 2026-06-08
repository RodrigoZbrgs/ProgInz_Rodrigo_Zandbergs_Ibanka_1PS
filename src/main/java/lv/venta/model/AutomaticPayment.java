package lv.venta.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.venta.model.enums.AutomaticPaymentScheduleType;

@Table(name = "AutomaticPayment")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class AutomaticPayment {

	@Column(name = "schedule_type")
	@NotEmpty
	@NotNull
	private AutomaticPaymentScheduleType apScheduleType;
	
	@Column(name = "amount")
	@NotNull
	private int paymentAmount;
	
	@Column(name = "next_payment_date")
	@NotNull
	private LocalDate nextPaymentDate;
	
	@Column(name = "Idap")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idap;
	
	@Column(name = "description")
	@NotEmpty
	private String description;
	
	@Column(name = "title")
	@NotEmpty
	private String paymentTitle;
	
	public AutomaticPayment(AutomaticPaymentScheduleType apScheduleType, int paymentAmount, LocalDate nextPaymentDate,
			String description, String paymentTitle) {
		setApScheduleType(apScheduleType);
		setPaymentAmount(paymentAmount);
		setNextPaymentDate(nextPaymentDate);
		setDescription(description);
		setPaymentTitle(paymentTitle);
	}
}
