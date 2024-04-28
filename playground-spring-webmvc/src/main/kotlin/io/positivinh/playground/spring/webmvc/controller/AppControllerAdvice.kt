package io.positivinh.playground.spring.webmvc.controller

import io.positivinh.playgound.spring.commons.validation.exceptions.ValidationException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class AppControllerAdvice {

    @ExceptionHandler(ValidationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationException(exception: ValidationException): ProblemDetail {

        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.message ?: "error")
    }

}
