package med.voll.api.models.records;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record SchedulingCreateRecord ( 
		Long 
		doctorId, 
		@NotNull
		Long 
		PatientId, 
		@NotNull
		LocalDateTime
		time) {

}
