package com.ppv.notifier.service;

import com.ppv.notifier.enums.MessageType;
import com.ppv.notifier.model.NotificationModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Message dispatcher class
 *
 * @author Pavlo.Pavlichenko
 */
@Slf4j
@Service
public class MessageDispatcher {

    private final Map<MessageType, NotificationService> dispatcherMap;

    public MessageDispatcher(List<NotificationService> listOfBeans) {
        this.dispatcherMap = listOfBeans.stream()
                .collect(Collectors.toMap(NotificationService::getType, Function.identity()));
    }

    public void process(NotificationModel notificationModel) {
        if (dispatcherMap.containsKey(notificationModel.getMessageType())) {
            NotificationService notificationService = dispatcherMap.get(notificationModel.getMessageType());
            notificationService.send(notificationModel);
        } else {
            log.warn(String.format("Unknown message type %s. Notification isn't sent",
                    notificationModel.getMessageType()));
        }

    }

}
