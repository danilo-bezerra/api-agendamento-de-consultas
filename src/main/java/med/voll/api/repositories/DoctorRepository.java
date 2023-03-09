package med.voll.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import med.voll.api.models.entities.Doctor;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{

	//Page<Doctor> findAllByActive(Pageable pagination);
	Page<Doctor> findAllByActiveTrue(Pageable pagination);

	//Page<Doctor> findAllActiveTrue(Pageable pagination);

}
