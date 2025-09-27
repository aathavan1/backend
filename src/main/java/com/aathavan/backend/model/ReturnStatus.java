package com.aathavan.backend.model;

import lombok.Getter;

public class ReturnStatus {
    @Getter
    private Object objectdata;
    @Getter
    private boolean status = false;
    @Getter
    private String description;

    public ReturnStatus(boolean status) {
        this.status = status;
    }

    public ReturnStatus(boolean status, Object object) {
        this.objectdata = object;
        this.status = status;
    }

    public ReturnStatus(boolean status, String description) {
        this.description = description;
        this.status = status;
    }


}
