package lv.venta.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.venta.model.enums.AutomaticPaymentScheduleType;

@Table(name = "AutomaticPayment")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class AutomaticPayment extends Transaction {

	@Column(name = "schedule_type")
	@Enumerated(EnumType.STRING)
	@NotNull
	private AutomaticPaymentScheduleType apScheduleType;
	
	@Column(name = "active")
	@NotNull
	private Boolean activity;
	
	@Column(name = "next_payment_date")
	@NotNull
	private LocalDate nextPaymentDate;
	
	@Column(name = "title")
	@NotEmpty
	private String paymentTitle;
	
	public AutomaticPayment(Boolean activity, AutomaticPaymentScheduleType apScheduleType, BigDecimal amount,LocalDate nextPaymentDate,
			LocalDateTime dateTime, String description, String paymentTitle) {
		setActivity(activity);
		setApScheduleType(apScheduleType);
		setAmount(amount);
		setNextPaymentDate(nextPaymentDate);
		setDateTime(dateTime);
		setDescription(description);
		setPaymentTitle(paymentTitle);
	}
}
