package com.ppv.notifier.service;

import com.ppv.notifier.model.NotificationModel;

/**
 * General interface for the Notification service.
 *
 * @author Pavlo.Pavlichenko
 */
public interface NotificationService {

	void send(NotificationModel notificationModel);

}
