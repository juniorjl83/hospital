package com.heinson.hospital.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.heinson.hospital.api.dto.FailureDetailDto;
import com.heinson.hospital.api.dto.PatientDto;

import io.swagger.annotations.*;

@Api(value = "patients")
public interface PatientApi {

	@ApiOperation(value = "Register a new patient", nickname = "addPatient", notes = "", tags = { "patient", })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Patient registered successfully "),
			@ApiResponse(code = 400, message = "The request has an invalid format or validation failure. ", response = FailureDetailDto.class),
			@ApiResponse(code = 500, message = "Server error.", response = FailureDetailDto.class) })
	@RequestMapping(value = "/patient", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity addPatient(
			@ApiParam(value = "Patient object that needs to be registered to the hospital", required = true) @RequestBody PatientDto body);

}
