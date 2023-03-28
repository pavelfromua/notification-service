package com.ppv.notifier.service;

import com.ppv.notifier.entity.Message;
import com.ppv.notifier.entity.User;
import com.ppv.notifier.model.NotificationModel;
import com.ppv.notifier.repository.MessageRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Pavlo.Pavlichenko
 */
@Service
public class MessageService {

    private final MessageRepo messageRepo;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public List<Message> findMessagesByAuthorId(String id) {
        return messageRepo.findAllByAuthorId(id);
    }

    public void save(NotificationModel notificationModel, User author) {
        Message message = new Message();

        message.setAuthor(author);
        message.setMessageType(notificationModel.getMessageType());
        message.setRecipientName(notificationModel.getName());
        message.setRecipientContact(notificationModel.getContact());
        message.setSubject(notificationModel.getSubject());
        message.setSentBy(LocalDateTime.now());

        messageRepo.save(message);
    }
}
