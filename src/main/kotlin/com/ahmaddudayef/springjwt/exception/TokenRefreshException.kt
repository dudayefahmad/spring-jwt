package com.ahmaddudayef.springjwt.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.FORBIDDEN)
class TokenRefreshException(token: String, message: String) : RuntimeException(String.format("Failed for [%s]: %s", token, message)) {
    companion object {
        private const val serialVersionUID = 1L
    }
}