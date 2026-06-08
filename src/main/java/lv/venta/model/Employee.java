package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
import lv.venta.model.enums.EmployeeStatus;

@Table(name = "EmployeeTable")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Employee {

	
	@Column(name = "approval_limit")
	@Min(0)
	private int approvalLimit;
	
	@Column(name = "Ide")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long ide;
	
	@Column(name = "name")
	@NotEmpty
	@Pattern(regexp = "[A-Ž]{1}[a-ž]{2,20}([ ][A-Ž]{1}[a-ž]{2,20})?")
	private String name;
	
	@Column(name = "person_code")
	@Pattern(regexp = "[0-9]{6}[-]{1}[0-9]{5}")
	@NotEmpty
	private String personCode;
	
	@Column(name = "surname")
	@NotEmpty
	@Pattern(regexp = "[A-Ž]{1}[a-ž]{2,20}([ ][A-Ž]{1}[a-ž]{2,20})?")
	private String surname;
	
	@Column(name = "status")
	@NotEmpty
	@NotNull
	private EmployeeStatus status;
	
	public Employee(String name, String personCode, String surname, EmployeeStatus status) {
		setName(name);
		setPersonCode(personCode);
		setSurname(surname);
		setStatus(status);
		
	}
}
