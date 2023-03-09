package med.voll.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.models.entities.Address;
import med.voll.api.repositories.AddressRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository repository;

	public void create(Address obj) {
		repository.save(obj);
	}

}
