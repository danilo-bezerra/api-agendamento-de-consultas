package med.voll.api.models.records;

import med.voll.api.models.entities.Address;
import med.voll.api.models.entities.Patient;

public record DetailPatientDTO(Long id, String name, String email, String cpf, String phone, DetailAddressDTO address, Boolean active) {

	public DetailPatientDTO(Patient obj) {
		this(obj.getId(),obj.getName(), obj.getEmail(), obj.getCpf(), obj.getPhone(), new DetailAddressDTO(obj.getAddress()), obj.getActive());
	}

}
