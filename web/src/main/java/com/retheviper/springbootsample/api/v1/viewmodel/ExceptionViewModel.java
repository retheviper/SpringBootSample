package com.retheviper.springbootsample.api.v1.viewmodel;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionViewModel implements Serializable {

    private LocalDateTime timeStamp;

    private String errorType;

    private String errorCode;

    private String errorMessage;
}
