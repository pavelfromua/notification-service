package com.ppv.notifier.model;

import com.ppv.notifier.enums.MessageType;
import com.ppv.notifier.enums.NotificationType;
import lombok.Data;

/**
 * Class which describes the structure of income data.
 *
 * @author Pavlo.Pavlichenko
 */
@Data
public class NotificationModel {

	private MessageType messageType;
	private NotificationType notificationType;
	private String contact;
	private String name;
	private String subject;
	private String message;

}
