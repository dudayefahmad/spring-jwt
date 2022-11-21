package com.ahmaddudayef.springjwt.advice

import com.ahmaddudayef.springjwt.exception.TokenRefreshException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import java.util.*

@RestController
class TokenRefreshException {

    @ExceptionHandler(value = [TokenRefreshException::class])
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun handleTokenRefreshException(ex: TokenRefreshException, request: WebRequest): ErrorMessage {
        return ErrorMessage(
            HttpStatus.FORBIDDEN.value(),
            Date(),
            ex.message!!,
            request.getDescription(false)
        )
    }
}