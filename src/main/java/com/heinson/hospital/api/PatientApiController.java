package com.heinson.hospital.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heinson.hospital.api.dto.PatientDto;
import com.heinson.hospital.service.IPatientService;
import com.heinson.hospital.service.PatientService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.google.common.base.Preconditions.checkArgument;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1")
public class PatientApiController implements PatientApi {

	private static final Logger log = LoggerFactory.getLogger(PatientApiController.class);

	private static final Object ID_IS_REQUIRED = "Id is required.";
	private static final String FIRST_NAME_IS_REQUIRED = "First name is requered.";
	private static final String LAST_NAME_IS_REQUIRED = "Last name is requered.";
	private static final String EMAIL_IS_REQUIRED = "Email is required.";
	private static final String PHONE_IS_REQUIRED = "Phone is required.";
	private static final String EMAIL_WRONG_FORMAT = "Email wrong format.";
	private static final String EMAIL_VALIDATOR = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	private static final String ID_ONLY_NUMBERS_ALLOWED = "Id allows only numbers.";
	private static final String ID_WRONG_SIZE = "Id size must be 0 to 20 digits.";
	private static final String LAST_NAME_WRONG_SIZE = "Lastname size must be 0 to 255 characters.";
	private static final String FIRST_NAME_WRONG_SIZE = "Firstname size must be 0 to 255 characters.";
	private static final String PHONE_WRONG_SIZE = "Phone size must be 0 to 20 characters.";

	private final ObjectMapper objectMapper;
	private final HttpServletRequest request;
	private final IPatientService service;

	@Autowired
	public PatientApiController(ObjectMapper objectMapper, HttpServletRequest request, IPatientService service) {
		this.objectMapper = objectMapper;
		this.request = request;
		this.service = service;
	}

	@Override
	public ResponseEntity addPatient(PatientDto patient) {
		validateObject(patient);
		patient.setPatientId(service.registerPatient(patient));
		return new ResponseEntity(patient, HttpStatus.CREATED);
	}

	private void validateObject(PatientDto patient) {
		checkArgument(!StringUtils.isEmpty(patient.getId()), ID_IS_REQUIRED);
		checkArgument(!StringUtils.isEmpty(patient.getFirstname()), FIRST_NAME_IS_REQUIRED);
		checkArgument(!StringUtils.isEmpty(patient.getLastname()), LAST_NAME_IS_REQUIRED);
		checkArgument(!StringUtils.isEmpty(patient.getEmail()), EMAIL_IS_REQUIRED);
		checkArgument(!StringUtils.isEmpty(patient.getPhone()), PHONE_IS_REQUIRED);
		checkArgument(patient.getEmail().matches(EMAIL_VALIDATOR), EMAIL_WRONG_FORMAT);
		checkArgument(patient.getId().matches("[0-9]+"), ID_ONLY_NUMBERS_ALLOWED);
		checkArgument(patient.getId().length() < 21, ID_WRONG_SIZE);
		checkArgument(patient.getLastname().length() < 256, LAST_NAME_WRONG_SIZE);
		checkArgument(patient.getFirstname().length() < 256, FIRST_NAME_WRONG_SIZE);
		checkArgument(patient.getPhone().length() < 21, PHONE_WRONG_SIZE);
	}

}
