package com.ppv.notifier.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum to specify all possibly types for message sending.
 *
 * @author Pavlo.Pavlichenko
 */
@Getter
@AllArgsConstructor
public enum MessageType {
    EMAIL("Email"), SMS("Sms");

    private final String name;
}
