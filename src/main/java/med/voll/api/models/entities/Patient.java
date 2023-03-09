package med.voll.api.models.entities;

import java.io.Serializable;
import java.util.Set;

import javax.sql.DataSource;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.models.records.PatientCreateRecord;
import med.voll.api.models.records.PatientUpdateRecord;

@Entity
@Table(name ="tb_patients")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Patient implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String cpf;
	private Boolean active;
	
	@OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	
	@OneToMany(mappedBy = "patient")
    Set<Scheduling> schedules;
	
	public Patient(@Valid PatientCreateRecord obj, Address address) {
		this.name = obj.name();
		this.email = obj.email();
		this.phone = obj.phone();
		this.cpf = obj.cpf();
		this.address = address;
		this.active = true;
	}
	
	public void disable() {
		this.active = false;
	}

	public void updateFields(PatientUpdateRecord data) {
		if (data.name() != null) {
			this.name = data.name();
		}
		if (data.phone() != null) {
			this.name = data.phone();
		}
		if (data.address() != null) {
			this.address.updateFields(data.address());;
		}
		
	}
}
