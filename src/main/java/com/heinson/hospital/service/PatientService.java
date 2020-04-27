package com.heinson.hospital.service;

import static com.google.common.base.Preconditions.checkArgument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heinson.hospital.api.dto.PatientDto;
import com.heinson.hospital.repository.PatientRepository;
import com.heinson.hospital.repository.model.Patient;

@Service
public class PatientService implements IPatientService {

	private static final String DUPLICATE_KEY = "Patient already exists.";
	private final PatientRepository repository;

	@Autowired
	public PatientService(PatientRepository repository) {
		this.repository = repository;
	}

	@Override
	public Integer registerPatient(PatientDto dto) {
		checkArgument(repository.findByPatienId(dto.getId()) == null && 
				repository.findByEmail(dto.getEmail()) == null, DUPLICATE_KEY);
		
		return repository.save(Patient.builder()
					.id(dto.getId())
					.firstName(dto.getFirstname())
					.lastName(dto.getLastname())
					.email(dto.getEmail())
					.phone(dto.getPhone())
					.build())
				.getPatientId();
	}

}
