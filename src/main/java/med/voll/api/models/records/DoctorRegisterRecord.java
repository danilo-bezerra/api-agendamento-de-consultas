package med.voll.api.models.records;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.models.enums.Specialty;

public record DoctorRegisterRecord(
		@NotBlank
		String name, 
		@NotBlank
		@Email
		String email, 
		@NotBlank
		@Pattern(regexp = "\\d{10,15}")
		String phone,
		@NotBlank
		@Pattern(regexp = "\\d{4,6}")
		String crm,
		@NotNull
		Specialty specialty,
		@NotNull
		@Valid
		AddressRecord address) {

}
