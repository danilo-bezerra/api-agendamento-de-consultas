package med.voll.api.controllers;

import java.net.URI;

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
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.models.entities.Address;
import med.voll.api.models.entities.Patient;
import med.voll.api.models.records.DetailAddressDTO;
import med.voll.api.models.records.DetailPatientDTO;
import med.voll.api.models.records.PatientCreateRecord;
import med.voll.api.models.records.PatientListRecord;
import med.voll.api.models.records.PatientUpdateRecord;
import med.voll.api.repositories.AddressRepository;
import med.voll.api.services.PatientService;

@RestController
@RequestMapping("/pacientes")
public class PatientController {
	@Autowired
	private PatientService service;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DetailPatientDTO> register(@RequestBody @Valid PatientCreateRecord obj, UriComponentsBuilder uriBuilder) {
		Patient patient = new Patient(obj);		
		service.create(patient);
		URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(patient.getId()).toUri();
		return ResponseEntity.created(uri).body(new DetailPatientDTO(patient));
	}
	
	@GetMapping("/all")
	public ResponseEntity<Page<PatientListRecord>> findAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
		return ResponseEntity.ok(service.findAll(pagination));
	}
	
	@GetMapping
	public ResponseEntity<Page<PatientListRecord>> findAllActives(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
		return ResponseEntity.ok(service.findAllActive(pagination));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetailPatientDTO> findById(@PathVariable Long id) {
		Patient obj = service.findById(id);
		DetailPatientDTO dto = new DetailPatientDTO(obj);
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DetailPatientDTO> update(@PathVariable Long id,  @RequestBody @Valid PatientUpdateRecord data) {
		Patient obj = service.update(id, data);
		return ResponseEntity.ok(new DetailPatientDTO(obj));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
