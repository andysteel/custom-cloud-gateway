package com.gmail.andersoninfonet.authserver.handler;

import java.time.Instant;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import com.gmail.andersoninfonet.authserver.dto.ExceptionDTO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LogManager.getLogger(RestExceptionHandler.class);
    
    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class, ConstraintViolationException.class,
        MethodArgumentTypeMismatchException.class, NumberFormatException.class })
    public ResponseEntity<ExceptionDTO> handlerConstraintViolationException(final RuntimeException ex) {
        LOG.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ExceptionDTO(ex.getMessage(),HttpStatus.BAD_REQUEST.value(), ex.getClass().getName(), Instant.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handlerGenericException(final Exception ex) {
        LOG.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ExceptionDTO(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getClass().getName(), Instant.now()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /** {@inheritDoc} */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                            final HttpHeaders headers,
                                            final HttpStatus status,
                                            final WebRequest request) {

        final String newLine = System.getProperty("line.separator");
        final ExceptionDTO exceptionDTO = new ExceptionDTO();
        final String  validations = ex.getBindingResult().getFieldErrors()
                                    .stream()
                                    .filter(FieldError.class::isInstance)
                                    .map(FieldError.class::cast)
                                    .map(FieldError::getDefaultMessage)
                                    .collect(Collectors.joining(newLine));

        exceptionDTO.setMessage(validations);
        exceptionDTO.setClassName(ex.getClass().getName());
        exceptionDTO.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionDTO.setTimestamp(Instant.now());

        LOG.error(ex.getMessage(), ex);
        return new ResponseEntity<>(exceptionDTO,HttpStatus.BAD_REQUEST);
    }

    /** {@inheritDoc} */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(final Exception ex,
                                    final @Nullable Object body,
                                    final HttpHeaders headers,
                                    final HttpStatus status,
                                    final WebRequest request) {

        LOG.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ExceptionDTO(ex.getMessage(),status.value(), ex.getClass().getName(), Instant.now()), headers, status);
    }
}
