package com.ppv.notifier.service;

import com.ppv.notifier.enums.ErrorCode;
import com.ppv.notifier.enums.MessageType;
import com.ppv.notifier.exception.NotificationException;
import com.ppv.notifier.model.NotificationModel;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Twilio API for SMS
 *
 * @author Pavlo.Pavlichenko
 */
@Service
public class TwilioSmsSender implements NotificationService {
    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.phoneNumber}")
    private String phoneNumber;

    @Override
    public void send(NotificationModel notificationModel) {
        try {
            Twilio.init(accountSid, authToken);
            Message.creator(
                            new com.twilio.type.PhoneNumber(notificationModel.getContact()),
                            new com.twilio.type.PhoneNumber(phoneNumber),
                            notificationModel.getSubject())
                    .create();
        } catch (Exception e) {
            throw new NotificationException(ErrorCode.SEND_SMS_ERROR, e.getMessage(), e.getCause());
        }
    }

    @Override
    public MessageType getType() {
        return MessageType.SMS;
    }
}
