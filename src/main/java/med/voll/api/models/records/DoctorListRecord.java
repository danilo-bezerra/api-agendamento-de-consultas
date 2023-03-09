package med.voll.api.models.records;

import med.voll.api.models.entities.Doctor;
import med.voll.api.models.enums.Specialty;

public record DoctorListRecord(Long id, String name, String email, String phone, String crm, Specialty specialty) {
	public DoctorListRecord(Doctor obj) {
		this(obj.getId(), obj.getName(), obj.getEmail(), obj.getPhone(), obj.getCrm(), obj.getSpecialty());
	}
}
