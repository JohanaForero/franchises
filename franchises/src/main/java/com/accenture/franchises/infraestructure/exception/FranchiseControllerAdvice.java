package com.accenture.franchises.infraestructure.exception;

import com.accenture.franchises.domain.exception.FranchiseException;
import com.accenture.franchises.infraestructure.controller.FranchiseController;
import com.accenture.franchises.openapi.model.ErrorObjectDto;
import org.springframework.data.mongodb.UncategorizedMongoDbException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

import java.util.AbstractMap;
import java.util.Map;

@ControllerAdvice(assignableTypes = FranchiseController.class)
public class FranchiseControllerAdvice {
    private static final Map<Class<? extends RuntimeException>, HttpStatus> HTTP_STATUS_BY_CODE_EXCEPTION = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(FranchiseException.FranchiseAlreadyExistsException.class, HttpStatus.BAD_REQUEST),
            new AbstractMap.SimpleEntry<>(FranchiseException.BranchServiceException.class, HttpStatus.BAD_REQUEST),
            new AbstractMap.SimpleEntry<>(FranchiseException.ProductNotFoundException.class, HttpStatus.BAD_REQUEST),
            new AbstractMap.SimpleEntry<>(FranchiseException.ServerExceptionDB.class, HttpStatus.BAD_REQUEST));

    @ExceptionHandler(FranchiseException.class)
    public Mono<ResponseEntity<ErrorObjectDto>> handleFranchiseException(FranchiseException ex) {
        return Mono.just(buildErrorResponseEntity(ex));
    }

    @ExceptionHandler(UncategorizedMongoDbException.class)
    public Mono<ResponseEntity<ErrorObjectDto>> handleUncategorizedMongoDbException(
            UncategorizedMongoDbException uncategorizedMongoDbException) {
        return Mono.just(buildErrorResponseEntity(uncategorizedMongoDbException));
    }

    private ResponseEntity<ErrorObjectDto> buildErrorResponseEntity(Exception ex) {
        HttpStatus status = getHttpStatusForException(ex);
        return new ResponseEntity<>(buildErrorDto(ex), status);
    }

    private HttpStatus getHttpStatusForException(Exception ex) {
        return HTTP_STATUS_BY_CODE_EXCEPTION.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorObjectDto buildErrorDto(Exception ex) {
        ErrorObjectDto errorDto = new ErrorObjectDto();
        errorDto.setMessage(ex.getMessage());
        return errorDto;
    }
}
