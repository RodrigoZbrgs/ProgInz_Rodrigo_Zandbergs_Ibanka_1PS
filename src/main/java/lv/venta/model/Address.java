package lv.venta.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lv.venta.model.enums.City;

@Table(name = "AddressTable")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Address {
	
	@Column(name = "Aid")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long aid;
	
	@Column(name = "house_number")
	@NotNull
	@Min(1)
	private int houseNumber;
	
	@Column(name = "street_title")
	@NotEmpty
	private String streetTitle;
	
	@Column(name = "city")
	@NotNull
	@Enumerated(EnumType.STRING)
	private City city;
	
	@OneToMany(mappedBy = "address")
	private Collection<Client> clients = new ArrayList<>();
	
	public Address(int houseNumber, String streetTitle, City city) {
		setHouseNumber(houseNumber);
		setStreetTitle(streetTitle);
		setCity(city);
	}
}
