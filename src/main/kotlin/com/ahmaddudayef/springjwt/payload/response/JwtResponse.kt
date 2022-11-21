package com.ahmaddudayef.springjwt.payload.response

class JwtResponse(
    val token: String,
    val id: Long,
    val username: String,
    val email: String,
    val roles: List<String>,
    val type: String = "Bearer"
)