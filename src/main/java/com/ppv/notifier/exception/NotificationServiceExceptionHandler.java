package com.ppv.notifier.exception;

import com.ppv.notifier.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Class for handling service exceptions.
 *
 * @author Pavlo.Pavlichenko
 */
@ResponseBody
@ControllerAdvice
@Slf4j
public class NotificationServiceExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handler for NotificationException
	 *
	 * @param e NotificationException
	 * @return ResponseEntity with error code
	 */
	@ExceptionHandler(NotificationException.class)
	public ResponseEntity<ErrorResponse> handleMigrationServiceException(final NotificationException e) {
		ErrorResponse response = ErrorResponse.builder()
				.title(e.getMessage())
				.detail(e.getDetail())
				.status(e.getHttpStatus().value())
				.build();

		log.error("Handling NotificationException: {}, {}", e.getMessage(), e.getDetail());

		return ResponseEntity.status(response.getStatus()).body(response);
	}

	/**
	 * Generic handler for all types of exception
	 *
	 * @param e Exception
	 * @return ResponseEntity with error code
	 */
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorResponse> handleException(final Exception e) {
		ErrorResponse response = ErrorResponse.builder()
				.title(e.getMessage())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();

		log.error("Handling Exception: {}", e);

		return ResponseEntity.status(response.getStatus()).body(response);
	}

}
