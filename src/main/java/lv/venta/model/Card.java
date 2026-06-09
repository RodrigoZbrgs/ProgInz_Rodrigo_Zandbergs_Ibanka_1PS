package lv.venta.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "CardTable")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Card {

	@Column(name = "Caid")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long caid;
	
	@Column(name = "cvv")
	@NotEmpty
	@Pattern(regexp = "[0-9]{3}")
	private String cvv;
	
	@Column(name = "expiry_date")
	@NotNull
	private LocalDate expiryDate;
	
	@Column(name = "card_number")
	@NotEmpty
	@Pattern(regexp = "[0-9]{16}")
	private String cardNumber;
	
	@ManyToOne
    @JoinColumn(name = "Bid")
    private BankAccount bank;
	
	public Card(String cvv, LocalDate expiryDate, String cardNumber) {
		setCvv(cvv);
		setExpiryDate(expiryDate);
		setCardNumber(cardNumber);
	}
}
