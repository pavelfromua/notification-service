package com.ppv.notifier.controller;

import com.ppv.notifier.model.NotificationModel;
import com.ppv.notifier.service.MessageDispatcher;
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
	private final MessageDispatcher messageDispatcher;

	public NotificationController(MessageDispatcher messageDispatcher) {
		this.messageDispatcher = messageDispatcher;
	}

	@PostMapping
	public ResponseEntity<String> sendMessage(@RequestBody NotificationModel notificationModel) {
        messageDispatcher.process(notificationModel, null);

		return ResponseEntity.ok().build();
	}

}
