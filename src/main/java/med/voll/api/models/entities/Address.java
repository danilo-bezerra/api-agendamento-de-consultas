package med.voll.api.models.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import med.voll.api.models.records.AddressRecord;

@Entity
@Table(name = "tb_address")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String streetName;
	private String neighborhood;
	private String postalCode;
	private String city;
	private String state;
	private String number;
	private String complement;
	
	@OneToOne(mappedBy = "address")
	private Patient patient;
	
	@OneToOne(mappedBy = "address")
	private Doctor doctor;
	

	public Address(AddressRecord record) {
		this.streetName = record.streetName();
		this.neighborhood = record.neighborhood();
		this.postalCode = record.postalCode();
		this.city = record.city();
		this.state = record.state();
		this.number = record.number();
		this.complement = record.complement();
	}


	public void updateFields(AddressRecord data) {
		if (data.streetName() != null) {
			this.streetName = data.streetName();
		}
		if (data.neighborhood() != null) {
			this.neighborhood = data.neighborhood();
		}
		if (data.postalCode() != null) {
			this.postalCode = data.postalCode();
		}
		if (data.city() != null) {
			this.city = data.city();
		}
		if (data.state() != null) {
			this.state = data.state();
		}
		if (data.number() != null) {
			this.number = data.number();
		}
		if (data.complement() != null) {
			this.complement = data.complement();
		}
		
	}
}
