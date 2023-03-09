package med.voll.api.models.records;

import med.voll.api.models.entities.Address;

public record DetailAddressDTO(
		String streetName, 
		String neighborhood, 
		String postalCode,
		String city, 
		String state, 
		String number, 
		String complement) {

	public DetailAddressDTO(Address address) {
		this(address.getStreetName(), address.getNeighborhood(), address.getPostalCode(), address.getCity(), address.getState(), address.getNumber(), address.getComplement());
	}

}
