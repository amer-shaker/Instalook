package com.instalook.instalook.view.controller.utils;

import org.springframework.http.HttpStatus;

/**
 *
 * @author Amer Shaker
 */
public class BaseResponse {

    private HttpStatus statusCode;
    private String statusMessage;

    public BaseResponse() {

    }

    public BaseResponse(HttpStatus statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
