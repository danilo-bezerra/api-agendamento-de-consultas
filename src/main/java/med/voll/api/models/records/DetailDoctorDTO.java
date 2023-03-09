package med.voll.api.models.records;

import med.voll.api.models.entities.Doctor;
import med.voll.api.models.enums.Specialty;

public record DetailDoctorDTO(Long id, String name, String email, String crm, Specialty specialty,  String phone, DetailAddressDTO address, Boolean active) {

	public DetailDoctorDTO(Doctor obj) {
		this(obj.getId(),obj.getName(), obj.getEmail(), obj.getCrm(), obj.getSpecialty(),  obj.getPhone(), new DetailAddressDTO(obj.getAddress()), obj.getActive());
	}


}
