package med.voll.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
import med.voll.api.models.entities.Patient;
import med.voll.api.models.records.PatientCreateRecord;
import med.voll.api.models.records.PatientListRecord;
import med.voll.api.models.records.PatientUpdateRecord;
import med.voll.api.repositories.AddressRepository;
import med.voll.api.repositories.PatientRepository;
import med.voll.api.services.PatientService;

@RestController
@RequestMapping("/pacientes")
public class PatientController {
	@Autowired
	private PatientService service;
	@Autowired
	private AddressRepository addressRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Void> register(@RequestBody @Valid PatientCreateRecord obj) {
		Address address = new Address(obj.address());
		addressRepository.save(address);
		service.create(new Patient(obj, address));
		return ResponseEntity.created(null).build();
	}
	
	@GetMapping("/all")
	public Page<PatientListRecord> findAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
		return service.findAll(pagination);
	}
	
	@GetMapping
	public Page<PatientListRecord> findAllActives(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
		return service.findAllActive(pagination);
	}
	
	@GetMapping("/{id}")
	public void findById(@PathVariable Long id) {
		service.findById(id);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> update(@PathVariable Long id,  @RequestBody @Valid PatientUpdateRecord data) {
		service.update(id, data);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
