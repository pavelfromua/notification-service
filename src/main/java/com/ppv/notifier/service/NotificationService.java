package com.ppv.notifier.service;

import com.ppv.notifier.enums.MessageType;
import com.ppv.notifier.exception.NotificationException;
import com.ppv.notifier.model.NotificationModel;

/**
 * General interface for the Notification service.
 *
 * @author Pavlo.Pavlichenko
 */
public interface NotificationService {

	void send(NotificationModel notificationModel) throws NotificationException;

	MessageType getType();
}
