package com.ppv.notifier.model;

import lombok.Builder;
import lombok.Getter;

/**
 * Class which describes the structure of error responses.
 *
 * @author Pavlo.Pavlichenko
 */
@Builder
@Getter
public class ErrorResponse {
    private String title;
    private String detail;
    private int status;
}
