package med.voll.api.models.entities;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.models.enums.Specialty;
import med.voll.api.models.records.DoctorRegisterRecord;
import med.voll.api.models.records.DoctorUpdateRecord;

@Entity
@Table(name = "tb_doctors")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String email;
	private String phone;
	private String crm;
	private Boolean active;
	
	@Enumerated(EnumType.STRING)
	private Specialty specialty;
	
	@OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	
	@OneToMany(mappedBy = "doctor")
    Set<Scheduling> schedules;

	
	public Doctor(DoctorRegisterRecord obj, Address address) {
		this.name = obj.name();
		this.crm = obj.crm();
		this.email = obj.email();
		this.phone = obj.phone();
		this.active = true;
		this.specialty = obj.specialty();
		this.address = address;		
	}


	public void updateFields(DoctorUpdateRecord data) {
		if (data.name() != null) {
			this.name = data.name();
		}
		if (data.phone() != null) {
			this.phone = data.phone();
		}
		if (data.address() != null) {
			this.address.updateFields(data.address());
		}
		
	}
	
	public void disable() {
		this.active = false;
	}
	
	
}
