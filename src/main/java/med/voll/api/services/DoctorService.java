package med.voll.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import med.voll.api.models.entities.Doctor;
import med.voll.api.models.records.DoctorListRecord;
import med.voll.api.models.records.DoctorUpdateRecord;
import med.voll.api.repositories.DoctorRepository;

@Service
public class DoctorService {
	@Autowired
	private DoctorRepository repository;

	@Autowired
	private AddressService addressService;

	public void create(Doctor obj) {
		addressService.create(obj.getAddress());
		repository.save(obj);
	}

	public Doctor update(Long id, DoctorUpdateRecord data) {
		Doctor obj = repository.getReferenceById(id);
		obj.updateFields(data);
		return obj;
	}

	public Doctor findById(Long id) {
		Doctor obj = repository.getReferenceById(id);
		return obj;
	}

	public Page<DoctorListRecord> findAll(Pageable pagination) {
		return repository.findAll(pagination).map(DoctorListRecord::new);
	}

	public Page<DoctorListRecord> findAllActive(Pageable pagination) {
		return repository.findAllByActiveTrue(pagination).map(DoctorListRecord::new);
	}

	public void delete(Long id) {
		var obj = repository.getReferenceById(id);
		obj.disable();
	}
}
