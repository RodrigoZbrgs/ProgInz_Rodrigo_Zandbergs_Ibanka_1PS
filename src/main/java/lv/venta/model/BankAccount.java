package lv.venta.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "BankAccountTable")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BankAccount {
	
	@Column(name = "Bid")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long bid;
	
	@Column(name = "active")
	@NotNull
	private boolean activity;
	
	@Column(name = "balance")
	@NotNull
	@Min(0)
	private int balance;
	
	@Column(name = "iban")
	@NotEmpty
	@Pattern(regexp = "[LV]{2}[0-9]{2}[UNLA]{4}[0-9]{6}[4]{1}[0-9]{6}")
	private String iban;
	
	@OneToMany(mappedBy = "bank")
    private Collection<Card> cards = new ArrayList<Card>();
	
	@OneToMany(mappedBy = "senderAccount")
	private Collection<Transaction> sentTransactions = new ArrayList<Transaction>();

	@OneToMany(mappedBy = "receiverAccount")
	private Collection<Transaction> receivedTransactions = new ArrayList<Transaction>();
	
	@ManyToOne
    @JoinColumn(name = "Cid")
    private Client client;
	
	public BankAccount(boolean activity, int balance, String iban) {
		setActivity(activity);
		setBalance(balance);
		setIban(iban);
	}
}
