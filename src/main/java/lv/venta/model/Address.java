package lv.venta.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
	
	@Column(name = "Ida")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long ida;
	
	@Column(name = "house_number")
	@NotNull
	@Min(0)
	private int houseNumber;
	
	@Column(name = "street_title")
	@NotEmpty
	private String streetTitle;
	
	@Column(name = "city")
	@NotEmpty
	private City city;
	
	@ManyToMany(mappedBy = "addresses")
    private Collection<Client> clients = new ArrayList<Client>();
	
	public Address(int houseNumber, String streetTitle, City city) {
		setHouseNumber(houseNumber);
		setStreetTitle(streetTitle);
		setCity(city);
	}
}
