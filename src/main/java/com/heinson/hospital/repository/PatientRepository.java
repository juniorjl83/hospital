package com.heinson.hospital.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.heinson.hospital.repository.model.Patient;

public interface PatientRepository extends CrudRepository<Patient, Integer> {

	@Query("SELECT u FROM Patient u WHERE u.id = ?1")
	public Patient findByPatienId(String id);

	@Query("SELECT u FROM Patient u WHERE u.email = ?1")
	public Patient findByEmail(String email);
}
