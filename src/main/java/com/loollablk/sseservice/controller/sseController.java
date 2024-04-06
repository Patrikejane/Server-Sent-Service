/*
 * Copyright (c) 2024. Loollablk
 *
 * All right reserved.
 */

package com.loollablk.sseservice.controller;

import com.loollablk.sseservice.dto.MessageReq;
import com.loollablk.sseservice.exception.SseException;
import com.loollablk.sseservice.service.SseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.awt.*;

@RestController
@RequestMapping(value = "sse")
@ResponseBody
public class sseController {


    @Autowired
    private SseService sseService;

    @GetMapping(value = "/suscribe/{seesionId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@PathVariable("sessionId") String sessionId){
        try {
            return sseService.connect(sessionId);
        } catch (SseException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/send/{seesionId}")
    public String sendMessage(@PathVariable("sessionId") String sessionId ,@RequestBody MessageReq MegRequest)  {
        try {
            if(sseService.send(MegRequest.getSeesionId(), MegRequest.getMessageDetail())){
                return "Success";
            }
        } catch (SseException e) {
            throw new RuntimeException(e);
        }
        return "Failed to Send";
    }


    @GetMapping(value = "/close/{seesionId}")
    public void close(@PathVariable("sessionId") String sessionId){
        sseService.closeConnection(sessionId);
    }


}
