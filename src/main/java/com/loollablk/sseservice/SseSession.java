/*
 * Copyright (c) 2024. Loollablk
 *
 * All right reserved.
 */

package com.loollablk.sseservice;

import com.loollablk.sseservice.exception.SseException;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class Manage the server-sent event session objects
 */
public class SseSession {


    // Session MAp to hold the Emitter session Objects
    private static final Map<String, SseEmitter> sseSessionMap = new ConcurrentHashMap<>();

    public static void addEmitter(String sessionId, SseEmitter sseEmitter) throws SseException {

        if(sseSessionMap.get(sessionId) != null){
            throw new SseException("User session Exists");
        }
        sseSessionMap.put(sessionId,sseEmitter);
    }

    public static boolean exists(String sessionId){
        return sseSessionMap.get(sessionId) != null;
    }

    public  static boolean remove(String sessionId){
        SseEmitter sseEmitter = sseSessionMap.get(sessionId);
        if(sseEmitter != null){
            sseEmitter.complete();
        }
        return false;
    }

    public static void onError(String sessionId,Throwable throwable){
        SseEmitter sseEmitter = sseSessionMap.get(sessionId);
        if(sseEmitter != null){
            sseEmitter.completeWithError(throwable);
        }
    }

    public static void send(String sessionKey,String content) throws IOException {
        sseSessionMap.get(sessionKey).send(content);
    }


}
