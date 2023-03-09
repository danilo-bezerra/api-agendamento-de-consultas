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
	
	@Autowired
	private AddressService addressService;
	
	public void create(Patient obj) {
		addressService.create(obj.getAddress());
		repository.save(obj);
	}
	
	public Patient update(Long id, PatientUpdateRecord data) {
		Patient obj = repository.getReferenceById(id);
		obj.updateFields(data);
		return obj;
	}
	
	public Patient findById(Long id) {
		Patient obj = repository.getReferenceById(id);
		return obj;
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
