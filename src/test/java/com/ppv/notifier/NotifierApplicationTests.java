package com.ppv.notifier;

import com.ppv.notifier.enums.NotificationType;
import com.ppv.notifier.exception.NotificationException;
import com.ppv.notifier.model.NotificationModel;
import com.ppv.notifier.service.BasicMailSender;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotifierApplicationTests {

	@Mock
	private JavaMailSender javaMailSender;

	@Mock
	private Configuration fmConfiguration;

	@Mock
	private MimeMessage message;

	@Mock
	private Template template;

	@InjectMocks
	BasicMailSender mailSender;

	@Test
	void send_shouldSendNotification_success() throws IOException {
		when(javaMailSender.createMimeMessage()).thenReturn(message);
		when(fmConfiguration.getTemplate(anyString())).thenReturn(template);

		NotificationModel model = getNotificationModel();
		mailSender.send(model);

		assertDoesNotThrow(() -> mailSender.send(model));
	}

	@Test
	void send_notificationTypeIsNull_expectNotificationException() {
		when(javaMailSender.createMimeMessage()).thenReturn(message);

		NotificationModel model = getNotificationModel();
		model.setNotificationType(null);

		assertThrows(NotificationException.class, () -> mailSender.send(model));
	}

	@Test
	void send_subjectIsNull_expectNotificationException() {
		when(javaMailSender.createMimeMessage()).thenReturn(message);

		NotificationModel model = getNotificationModel();
		model.setSubject(null);

		assertThrows(NotificationException.class, () -> mailSender.send(model));
	}

	@Test
	void send_emailIsNull_expectNotificationException() {
		when(javaMailSender.createMimeMessage()).thenReturn(message);

		NotificationModel model = getNotificationModel();
		model.setContact(null);

		assertThrows(NotificationException.class, () -> mailSender.send(model));
	}

	private NotificationModel getNotificationModel() {
		NotificationModel model = new NotificationModel();

		model.setNotificationType(NotificationType.CONFIRM);
		model.setContact("test@test.com");
		model.setSubject("subject");

		return model;
	}
}
