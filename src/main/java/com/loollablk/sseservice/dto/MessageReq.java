/*
 * Copyright (c) 2024. Loollablk
 *
 * All right reserved.
 */

package com.loollablk.sseservice.dto;

public class MessageReq {

    private String seesionId;
    private String messageDetail;


    public String getSeesionId() {
        return seesionId;
    }

    public void setSeesionId(String seesionId) {
        this.seesionId = seesionId;
    }

    public String getMessageDetail() {
        return messageDetail;
    }

    public void setMessageDetail(String messageDetail) {
        this.messageDetail = messageDetail;
    }
}
