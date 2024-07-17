package com.olamaps.consumermaps.exception.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olamaps.consumermaps.exception.AutoCompleteException;
import com.olamaps.consumermaps.model.AutoCompleteResponse;
import com.olamaps.consumermaps.model.ErrorMessage;
import com.olamaps.consumermaps.utility.Util;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.olamaps.consumermaps.constant.Constant.REQUEST_ID;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(AutoCompleteException.class)
    public ResponseEntity<ErrorMessage> handleAutoCompleteException(AutoCompleteException ex, HttpServletRequest request) throws JsonProcessingException {
        Integer statusCode = Util.statusCodeMatchFinder(ex.getMessage());
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now().toString());
        errorMessage.setRequestId(request.getAttribute(REQUEST_ID).toString());
        errorMessage.setPath(request.getRequestURI());
        if (statusCode.equals(HttpStatus.NOT_FOUND.value())) {
            errorMessage.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
            errorMessage.setStatus(String.valueOf(HttpStatus.NOT_FOUND.value()));
        }
        else if (statusCode.equals(HttpStatus.BAD_REQUEST.value())) {
            errorMessage.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
            ObjectMapper objectMapper = new ObjectMapper();
            AutoCompleteResponse autoCompleteResponse = objectMapper.readValue(
                    ex.getMessage().substring(ex.getMessage().indexOf('{')),
                    AutoCompleteResponse.class);
            errorMessage.setMessage(autoCompleteResponse.getErrorMessage());
        }
        else {
            errorMessage.setStatus(String.valueOf(statusCode));
            if(!StringUtils.isEmpty(ex.getMessage())){
                errorMessage.setMessage(ex.getMessage());
            }
            else {
                errorMessage.setMessage("Something went wrong, OLA Maps failed to process the request");
            }
        }
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(statusCode));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now().toString());
        errorMessage.setRequestId(request.getAttribute(REQUEST_ID).toString());
        errorMessage.setPath(request.getRequestURI());
        errorMessage.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorMessage.setMessage(Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
    }
}
