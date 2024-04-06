/*
 * Copyright (c) 2024. Loollablk
 *
 * All right reserved.
 */

package com.loollablk.sseservice.service;

import com.loollablk.sseservice.exception.SseException;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * Sse Service Interface
 */
public interface SseService {


    /**
     * @param sessionId
     * @return
     * @throws SseException
     */
    public SseEmitter connect(String sessionId) throws SseException;

    /**
     * @param sessionId
     * @param data
     * @return
     * @throws SseException
     */
    public  boolean send(String sessionId, String data) throws SseException;


    /**
     * @param sessionId
     * @return
     */
    public  boolean closeConnection(String sessionId);

}
