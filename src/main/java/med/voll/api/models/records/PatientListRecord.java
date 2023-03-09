package med.voll.api.models.records;

import med.voll.api.models.entities.Patient;

public record PatientListRecord(Long id, String name, String email, String cpf) {
	public PatientListRecord(Patient p) {
		this(p.getId(),p.getName(), p.getEmail(), p.getCpf());
	}

}
