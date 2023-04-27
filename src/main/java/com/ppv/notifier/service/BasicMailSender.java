package com.ppv.notifier.service;

import com.ppv.notifier.enums.ErrorCode;
import com.ppv.notifier.enums.MessageType;
import com.ppv.notifier.exception.NotificationException;
import com.ppv.notifier.model.NotificationModel;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;

/**
 * Java Spring Mailer
 *
 * @author Pavlo.Pavlichenko
 */
@Service
public class BasicMailSender implements NotificationService {
    private static final String TEMPLATE_EXT = ".html";

    @Autowired
    private JavaMailSender javaMailSender;

    @Qualifier("getFreeMarkerConfiguration")
    @Autowired
    Configuration fmConfiguration;

    @Override
    public void send(NotificationModel notificationModel) throws NotificationException {

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);

            mimeMessageHelper.setTo(notificationModel.getContact());
            mimeMessageHelper.setSubject(notificationModel.getSubject());
            mimeMessageHelper.setText(geContentFromTemplate(notificationModel), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (Exception e) {
            throw new NotificationException(ErrorCode.SEND_EMAIL_ERROR, e.getMessage(), e.getCause());
        }

    }

    @Override
    public MessageType getType() {
        return MessageType.EMAIL;
    }

    private String geContentFromTemplate(NotificationModel model) throws IOException, TemplateException {
        return FreeMarkerTemplateUtils
                .processTemplateIntoString(fmConfiguration
                        .getTemplate(model.getNotificationType().getTemplateName() + TEMPLATE_EXT), model);

    }

}
