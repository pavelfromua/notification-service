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
    CONFIRM("confirm_notification", "Confirm visit"),
    CANCEL("cancel_notification", "Cancel visit");

    private final String templateName;
    private final String name;
}
