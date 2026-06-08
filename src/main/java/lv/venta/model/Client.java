package lv.venta.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

	@Column(name = "Idcl")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idcl;
	
	@Column(name = "client_code")
	@NotEmpty
	@Pattern(regexp = "[A-Ž]{1}[_]{1}[A-Ž]{1}[_]{1}[0-9]{6}[-]{1}[0-9]{5}")
	private String clientCode;
	
	@Column(name = "email")
	@NotEmpty
	@Pattern(regexp = "^(?=.{1,64}@)")
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
	
	@ManyToMany
	@JoinTable(name = "client_address_table",
	joinColumns = @JoinColumn(name = "Idcl"),
	inverseJoinColumns = @JoinColumn(name = "Ida"))
	private Collection<Address> addresses = new ArrayList<Address>();
	
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
