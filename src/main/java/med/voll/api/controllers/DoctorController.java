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
import med.voll.api.models.entities.Doctor;
import med.voll.api.models.entities.Patient;
import med.voll.api.models.records.CreateDoctorDTO;
import med.voll.api.models.records.DetailDoctorDTO;
import med.voll.api.models.records.DetailPatientDTO;
import med.voll.api.models.records.DoctorListRecord;
import med.voll.api.models.records.DoctorUpdateRecord;
import med.voll.api.models.records.PatientListRecord;
import med.voll.api.models.records.PatientUpdateRecord;
import med.voll.api.services.DoctorService;

@RestController
@RequestMapping("/medicos")
public class DoctorController {
	@Autowired
	private DoctorService service;

	
	@PostMapping
	public ResponseEntity<DetailDoctorDTO> register(@RequestBody @Valid CreateDoctorDTO data, UriComponentsBuilder uriBuilder) {
		Doctor obj = new Doctor(data);		
		service.create(obj);
		URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(new DetailDoctorDTO(obj));
	}
	
	@GetMapping("/all")
	public ResponseEntity<Page<DoctorListRecord>> findAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
		return ResponseEntity.ok(service.findAll(pagination));
	}
	
	@GetMapping
	public ResponseEntity<Page<DoctorListRecord>> findAllActives(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
		return ResponseEntity.ok(service.findAllActive(pagination));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetailDoctorDTO> findById(@PathVariable Long id) {
		Doctor obj = service.findById(id);
		DetailDoctorDTO dto = new DetailDoctorDTO(obj);
		return ResponseEntity.ok(dto);
	}
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DetailDoctorDTO> update(@PathVariable Long id,  @RequestBody @Valid DoctorUpdateRecord data) {
		Doctor obj = service.update(id, data);
		return ResponseEntity.ok(new DetailDoctorDTO(obj));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
