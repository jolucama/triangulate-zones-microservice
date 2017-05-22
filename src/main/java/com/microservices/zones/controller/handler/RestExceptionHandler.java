//Namespace
package com.microservices.zones.controller.handler;

//Imports
import com.microservices.zones.exception.AsynchronousException;
import com.microservices.zones.exception.CoordinateOutOfZoneException;
import com.microservices.zones.model.ApiMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Class that handles the common exceptions of all controllers
 */
@ControllerAdvice
@RestController
public class RestExceptionHandler {

    /**
     * Out of zone exception handler
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value = {CoordinateOutOfZoneException.class})
    public ResponseEntity<ApiMessageResponse> exceptionHandler(CoordinateOutOfZoneException e, WebRequest request) {

        //Return message
        return new ResponseEntity(
                new ApiMessageResponse(HttpStatus.NO_CONTENT, e.getMessage()), HttpStatus.NO_CONTENT
        );
    }
    
    /**
     * Out of zone exception handler
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value = {AsynchronousException.class})
    public ResponseEntity<ApiMessageResponse> exceptionAsyncHandler(AsynchronousException e, WebRequest request) {

        //Return message
        return new ResponseEntity(
                new ApiMessageResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
