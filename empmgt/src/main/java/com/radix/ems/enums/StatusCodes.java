package com.radix.ems.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCodes {
    ERROR("ERROR"),
    SUCCESS("SUCCESS"),
    CREATED("CREATED"),
    UPDATED("UPDATED"),
    DELETED("DELETED"),
    NOT_FOUND("NOT_FOUND");

    private final String status;
}
