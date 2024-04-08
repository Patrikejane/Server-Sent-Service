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
import java.time.LocalTime;

@RestController
@RequestMapping(value = "/sse")
@ResponseBody
@CrossOrigin
public class sseController {


    @Autowired
    private SseService sseService;

    @GetMapping(value = "/subscribe/{sessionId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@PathVariable("sessionId") String sessionId){
        try {
            return sseService.connect(sessionId);
        } catch (SseException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public String streamEvents() {
        return "data:" + LocalTime.now() + "\n\n";
    }



    @PostMapping(value = "/send/{sessionId}")
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


    @GetMapping(value = "/close/{sessionId}")
    public void close(@PathVariable("sessionId") String sessionId){
        sseService.closeConnection(sessionId);
    }

    @GetMapping(value = "/home")
    public String home(){
        return "Home";
    }


}
