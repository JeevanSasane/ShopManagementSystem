package com.shopmanagement.exceptionhandler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@RestControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {


//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(ResponseEntityExceptionHandler ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        logger.info("handleMethodArgumentNotValid");
//        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(status);
//        apiErrorResponse.addValidationErrors(ex.ge);
//        apiErrorResponse.addValidationError(ex.getBindingResult().getGlobalErrors());
//        apiErrorResponse.setDebugMessages(
//                ex.getBindingResult()
//                        .getFieldErrors()
//                        .stream()
//                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                        .collect(Collectors.toList()));
//
//        return new ResponseEntity<>(apiErrorResponse, status);
//    }

//    @Override
//    protected ResponseEntity<Object> handleMissingServletRequestParameter(
//            MissingServletRequestParameterException ex, HttpHeaders headers,
//            HttpStatus status, WebRequest request) {
//        logger.info("handleMissingServletRequestParameter");
//        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(status);
//        apiErrorResponse.setDebugMessage(ex.getParameterName() + " parameter is missing");
//        apiErrorResponse.setMessage(ex.getMessage());
//        return new ResponseEntity<>(apiErrorResponse, headers, status);
//    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        logger.info(
                "Constraint violation exception encountered: {}" +
                        ex.getConstraintViolations(),
                ex
        );
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(BAD_REQUEST);
        apiErrorResponse.setMessage("Validation error");
        apiErrorResponse.addValidationErrors(ex.getConstraintViolations());
        apiErrorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(apiErrorResponse, BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
                                                                  WebRequest request) {
        logger.info("handleDataIntegrityViolation");
        if (ex.getCause() instanceof DataIntegrityViolationException) {
            ApiErrorResponse apiErrorResponse = new ApiErrorResponse(CONFLICT, ex.getMessage(), ex.getCause().getCause().getMessage());
            return new ResponseEntity<>(apiErrorResponse, CONFLICT);
        }
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(INTERNAL_SERVER_ERROR, ex.getMessage(), ex.getCause());
        return new ResponseEntity<>(apiErrorResponse, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                      WebRequest request) {
        logger.info("handleMethodArgumentTypeMismatch");
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(BAD_REQUEST);
        apiErrorResponse.setMessage("The parameter " + ex.getName() + " of value " + ex.getValue() +
                " could not be converted to type " + Objects.requireNonNull(ex.getRequiredType()).getSimpleName() + " ");
        apiErrorResponse.setDebugMessage(ex.getMessage());
        return new ResponseEntity<>(apiErrorResponse, BAD_REQUEST);
    }

//    @ExceptionHandler(javax.persistence.EntityNotFoundException.class)
//    protected ResponseEntity<Object> handleEntityNotFound(javax.persistence.EntityNotFoundException ex) {
//        logger.info("handleEntityNotFound");
//        return new ResponseEntity<>(new ApiErrorResponse(NOT_FOUND, ex), NOT_FOUND);
//    }

//    @Override
//    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
//            HttpMediaTypeNotSupportedException ex,
//            HttpHeaders headers,
//            HttpStatus status,
//            WebRequest request) {
//        StringBuilder builder = new StringBuilder();
//        builder.append(ex.getContentType());
//        builder.append(" media type is not supported. Supported media types are ");
//        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
//        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(UNSUPPORTED_MEDIA_TYPE, ex);
//        apiErrorResponse.setDebugMessage(builder.substring(0, builder.length() - 2));
//        return new ResponseEntity<>(apiErrorResponse, UNSUPPORTED_MEDIA_TYPE);
//    }

//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
//        logger.info("handleAllExceptions");
//        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(INTERNAL_SERVER_ERROR);
//        apiErrorResponse.getDebugMessages().add("" + ex);
//        apiErrorResponse.setMessage(ex.getMessage());
//        return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }


}