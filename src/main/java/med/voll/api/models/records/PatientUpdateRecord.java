package med.voll.api.models.records;

import jakarta.validation.constraints.Pattern;
import med.voll.api.models.entities.Address;

public record PatientUpdateRecord(
		String name,
		@Pattern(regexp = "\\d{10,15}") 
		String phone,
		
		AddressRecord address
		) {

}
