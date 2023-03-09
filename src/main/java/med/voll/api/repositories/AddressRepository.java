package med.voll.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import med.voll.api.models.entities.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

	//Page<Doctor> findAllByActive(Pageable pagination);
	//Page<Doctor> findAllByActiveTrue(Pageable pagination);

	//Page<Doctor> findAllActiveTrue(Pageable pagination);

}
