
//Namespace
package com.microservices.zones.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Class that represents an API Response
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiMessageResponse {

    //Fields
    private String message;
    private HttpStatus status;

    private List<String> fieldErrors;
    private String errorKey;
    private String moreInfo;


    /**
     * Constructore
     */
    public ApiMessageResponse(){}

    /**
     * Constructor
     * @param status
     * @param message
     */
    public ApiMessageResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    //Getters and Setters

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public void setErrorKey(String errorKey) {
        this.errorKey = errorKey;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }
}
