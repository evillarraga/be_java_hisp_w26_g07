package org.example.be_java_hisp_w26_g07.exception;

import org.apache.coyote.BadRequestException;
import org.example.be_java_hisp_w26_g07.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SocialMediaExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> notFound(NotFoundException ex) {
        ExceptionDto exdto = new ExceptionDto(ex.getMessage());
        return new ResponseEntity<>(exdto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestExecption.class)
    public ResponseEntity<ExceptionDto> badRequest(BadRequestException ex) {
        ExceptionDto exdto = new ExceptionDto(ex.getMessage());
        return new ResponseEntity<>(exdto, HttpStatus.BAD_REQUEST);
    }
}