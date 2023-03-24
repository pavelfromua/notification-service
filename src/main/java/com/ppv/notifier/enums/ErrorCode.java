package com.ppv.notifier.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Enum to specify all errors possibly returned by REST endpoints.
 *
 * @author Pavlo.Pavlichenko
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
	SEND_EMAIL_ERROR("Error during sending an email", HttpStatus.INTERNAL_SERVER_ERROR),
	SEND_SMS_ERROR("Error during sending SMS", HttpStatus.INTERNAL_SERVER_ERROR);

	private final String description;
	private final HttpStatus httpStatus;
}
