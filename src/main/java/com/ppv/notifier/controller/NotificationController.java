package com.ppv.notifier.controller;

import com.ppv.notifier.model.NotificationModel;
import com.ppv.notifier.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for testing purposes.
 *
 * @author Pavlo.Pavlichenko
 */
@RestController
@RequestMapping("/api/notification")
public class NotificationController {
	private final NotificationService notificationService;

	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@PostMapping("/email")
	public ResponseEntity<String> emailSend(@RequestBody NotificationModel notificationModel) {
		notificationService.send(notificationModel);

		return ResponseEntity.ok().build();
	}

}
