package com.ppv.notifier.exception;

import com.ppv.notifier.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Objects;

/**
 * Basic exception class for the Notification service.
 *
 * @author Pavlo.Pavlichenko
 */
@Getter
public class DispatcherException extends RuntimeException {
	private final ErrorCode errorCode;
	private final String detail;

	public DispatcherException(ErrorCode errorCode) {
		this(errorCode, null);
	}

	public DispatcherException(ErrorCode errorCode, String detail) {
		super(errorCode.name());
		this.errorCode = errorCode;
		this.detail = Objects.toString(detail, errorCode.getDescription());
	}

	public DispatcherException(ErrorCode errorCode, String message, Throwable cause) {
		super(errorCode.name(), cause);
		this.errorCode = errorCode;
		this.detail = Objects.toString(message, errorCode.getDescription());
	}

	public HttpStatus getHttpStatus() {
		return errorCode.getHttpStatus();
	}
}
