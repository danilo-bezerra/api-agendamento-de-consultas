package med.voll.api.models.records;

import jakarta.validation.constraints.Pattern;

public record DoctorUpdateRecord(
		String name, 
		@Pattern(regexp = "\\d{10,15}")
		String phone, 
		AddressRecord address) {

}
