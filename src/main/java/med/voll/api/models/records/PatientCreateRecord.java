package med.voll.api.models.records;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PatientCreateRecord(
		@NotBlank
		String name, 
		@NotBlank
		@Email
		String email, 
		@NotBlank
		@Pattern(regexp = "\\d{10,15}")
		String phone,
		@NotBlank
		@Pattern(regexp = "\\d{11}")
		String cpf,
		@NotNull
		@Valid
		AddressRecord address
		
		) {

}
