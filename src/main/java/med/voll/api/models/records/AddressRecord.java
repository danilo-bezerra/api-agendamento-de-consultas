package med.voll.api.models.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressRecord(
		@NotBlank 
		String streetName, 
		@NotBlank 
		String neighborhood, 
		@NotBlank 
		@Pattern(regexp = "\\d{8}")
		String postalCode,
		@NotBlank 
		String city, 
		@NotBlank String state, 
		String number, 
		String complement) {

}
