package med.voll.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import med.voll.api.models.entities.Patient;
import med.voll.api.models.records.PatientListRecord;
import med.voll.api.models.records.PatientUpdateRecord;
import med.voll.api.repositories.PatientRepository;

@Service
public class PatientService {
	@Autowired
	private PatientRepository repository;
	
	public void create(Patient obj) {
		repository.save(obj);
	}
	
	public void update(Long id, PatientUpdateRecord data) {
		var obj = repository.getReferenceById(id);
		obj.updateFields(data);
	}
	
	public void findById(Long id) {
		var obj = repository.getReferenceById(id);
		System.out.println(obj);
	}
	
	public Page<PatientListRecord> findAll(Pageable pagination) {
		return repository.findAll(pagination).map(PatientListRecord::new);
	}
	
	public Page<PatientListRecord> findAllActive(Pageable pagination) {
		return repository.findAllByActiveTrue(pagination).map(PatientListRecord::new);
	}

	public void delete(Long id) {
		var obj = repository.getReferenceById(id);
		obj.disable();
	}
}
