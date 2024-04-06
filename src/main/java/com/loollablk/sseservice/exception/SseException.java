/*
 * Copyright (c) 2024. Loollablk
 *
 * All right reserved.
 */

package com.loollablk.sseservice.exception;

/**
 * Custom Exception for Sse Service
 */
public class SseException extends  Exception{

    public SseException(String errorMsg){
        super(errorMsg);
    }


}
