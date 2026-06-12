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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "ClientTable")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Client {

	@Column(name = "Cid")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long cid;
	
	@Column(name = "client_code", unique = true)
	@NotEmpty
	@Pattern(regexp = "[A-Ž]{1}[_]{1}[A-Ž]{1}[_]{1}[0-9]{6}[-]{1}[0-9]{5}")
	private String clientCode;
	
	@Column(name = "email")
	@NotEmpty
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
	private String email;
	
	@Column(name = "name")
	@NotEmpty
	@Pattern(regexp = "[A-Ž]{1}[a-ž]{2,20}([ ][A-Ž]{1}[a-ž]{2,20})?")
	private String name;
	
	@Column(name = "person_code")
	@NotEmpty
	@Pattern(regexp = "[0-9]{6}[-]{1}[0-9]{5}")
	private String personCode;
	
	@Column(name = "surname")
	@NotEmpty
	@Pattern(regexp = "[A-Ž]{1}[a-ž]{2,20}([ ][A-Ž]{1}[a-ž]{2,20})?")
	private String surname;
	
	@ManyToOne
	@JoinColumn(name = "aid")
	private Address address;
	
	@OneToMany(mappedBy = "client")
	private Collection<BankAccount> bankAccounts = new ArrayList<BankAccount>();
	
	public Client(String clientCode, String email, String name, String personCode, String surname) {
		setClientCode(clientCode);
		setEmail(email);
		setName(name);
		setPersonCode(personCode);
		setSurname(surname);
	}
}
