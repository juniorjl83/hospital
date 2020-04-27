package com.heinson.hospital.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * This class contains credentials details
 * 
 * @author jose.munoz
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)
public class FailureDetailDto {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty("timestamp")
	private Date timestamp;

	@JsonProperty("status")
	private Integer status;

	@JsonProperty("exception")
	private String exception;

	@JsonProperty("code")
	private Integer code;

	@JsonProperty("severity")
	private SeverityEnum severity;

	@JsonProperty("message")
	private String message;

	@JsonProperty("params")
	private List<String> params;

	@JsonProperty("path")
	private String path;

	public enum SeverityEnum {
		WARN("WARN"), ERROR("ERROR"), FATAL("FATAL");

		private String value;

		SeverityEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static SeverityEnum fromValue(String text) {
			for (SeverityEnum b : SeverityEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			throw new IllegalArgumentException(String.format("Invalid Severity %s", text));
		}
	}
}
