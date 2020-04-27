package com.heinson.hospital.repository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer patientId;
	@Column(unique = true)
	private String id;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String email;
	private String phone;

}
