package med.voll.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.models.entities.Address;
import med.voll.api.models.entities.Doctor;
import med.voll.api.models.records.DoctorListRecord;
import med.voll.api.models.records.DoctorRegisterRecord;
import med.voll.api.models.records.DoctorUpdateRecord;
import med.voll.api.repositories.AddressRepository;
import med.voll.api.repositories.DoctorRepository;

@RestController
@RequestMapping("/medicos")
public class DoctorController {
	@Autowired
	private DoctorRepository repository;
	@Autowired
	private AddressRepository addressRepository;

	
	@PostMapping
	@Transactional
	public void register(@RequestBody @Valid DoctorRegisterRecord obj) {
		Address address = new Address(obj.address());
		addressRepository.save(address);
		repository.save(new Doctor(obj, address));
	}
	
	@GetMapping
	public Page<DoctorListRecord> findAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
		return repository.findAllByActiveTrue(pagination).map(DoctorListRecord::new);
	}
	
	@PutMapping
	@Transactional
	public void update(@RequestBody @Valid DoctorUpdateRecord data) {
		var obj = repository.getReferenceById(data.id());
		obj.updateFields(data);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void delete(@PathVariable Long id) {
		var obj = repository.getReferenceById(id);
		obj.disable();
	}
}
