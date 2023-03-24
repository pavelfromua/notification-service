package com.ppv.notifier.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum to specify all kids of notifications.
 *
 * @author Pavlo.Pavlichenko
 */
@Getter
@AllArgsConstructor
public enum NotificationType {
    CONFIRM("confirm_notification"),
    CANCEL("cancel_notification");

    private final String templateName;
}
