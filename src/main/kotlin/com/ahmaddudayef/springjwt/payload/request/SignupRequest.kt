package com.ahmaddudayef.springjwt.payload.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class SignupRequest (
    @NotBlank
    @Size(min = 3, max = 20)
    val username: String,

    @NotBlank
    @Size(max = 50)
    @Email
    val email: String,

    val role: Set<String>?,

    @NotBlank
    @Size(min = 6, max = 40)
    val password: String
)