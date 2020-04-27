package com.heinson.hospital.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class contains role details
 * 
 * @author jose.munoz
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientDto {

	private String id;
	private Integer patientId;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;

}
