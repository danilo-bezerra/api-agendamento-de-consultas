package med.voll.api.models.records;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorUpdateRecord(
		@NotNull
		Long  id,
		String name, 
		@Pattern(regexp = "\\d{10,15}")
		String phone, 
		AddressRecord address) {

}
