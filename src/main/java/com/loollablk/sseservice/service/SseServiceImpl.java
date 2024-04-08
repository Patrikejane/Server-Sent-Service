/*
 * Copyright (c) 2024. Loollablk
 *
 * All right reserved.
 */

package com.loollablk.sseservice.service;

import com.loollablk.sseservice.SseSession;
import com.loollablk.sseservice.exception.SseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

/**
 *  * SseService implementation
 */
@Service
public class SseServiceImpl implements SseService{

    private static final Logger log = LoggerFactory.getLogger(SseServiceImpl.class);

    /**
     * @param sessionId
     * @return
     * @throws SseException
     */
    @Override
    public SseEmitter connect(String sessionId) throws SseException {

        SseEmitter sseEmitter = new SseEmitter(2 * 60 * 1000L);

        sseEmitter.onCompletion(() -> {
            log.info( "SseSession Completion, session Id : {} ", sessionId);
            SseSession.remove(sessionId);
        });

        sseEmitter.onError((err) -> {
            log.info( "SseSessiom Error,  Error msg: {}," +
                    " session Id : {}",err.getMessage(), sessionId);
            SseSession.remove(sessionId);
        });

        sseEmitter.onTimeout(() -> {
            log.info( "SseSessiom TimeOut, session Id : {} ", sessionId);
            SseSession.remove(sessionId);
        });

        SseSession.addEmitter(sessionId, sseEmitter);
        return sseEmitter;
    }

    /**
     * @param sessionId
     * @param data
     * @return
     * @throws SseException
     */
    @Override
    public boolean send(String sessionId, String data) throws SseException {
        if(SseSession.exists(sessionId)){
            try {
                SseSession.send(sessionId,data);
                return true;
            } catch (IOException e) {
                log.error("SseSession send Erorr:IOException," +
                        " msg: {} session Id : {}",e.getMessage(), sessionId);
            }
        }
        else {
            throw new SseException("Session ID " + sessionId + " not Found");
        }
        return false;
    }

    /**
     * @param sessionId
     * @return
     */
    @Override
    public boolean closeConnection(String sessionId) {
        log.info( "SseSessiom Close, session Id : {} ", sessionId);
        return SseSession.remove(sessionId);
    }
}
