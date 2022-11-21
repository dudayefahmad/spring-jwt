package com.ahmaddudayef.springjwt.security.jwt

import com.ahmaddudayef.springjwt.security.service.UserDetailsImpl
import com.ahmaddudayef.springjwt.util.Utils.logger
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*


@Component
class JwtUtils {

    @Value("{ahmaddudayef.app.jwtSecret}")
    private val jwtSecret: String? = null

    @Value("\${ahmaddudayef.app.jwtExpirationMs}")
    private val jwtExpirationMs = 0

    val key: Key = Keys.secretKeyFor(SignatureAlgorithm.HS256)

//    fun generateJwtToken(authentication: Authentication): String {
////        val key: Key = Keys.hmacShaKeyFor(jwtSecret?.toByteArray(StandardCharsets.UTF_8))
//        val userPrincipal = authentication.principal as UserDetailsImpl
//        return Jwts.builder()
//            .setSubject(userPrincipal.username)
//            .setIssuedAt(Date())
//            .setExpiration(Date(Date().time + jwtExpirationMs))
//            .signWith(key)
//            .compact()
//    }

    fun generateJwtToken(userPrincipal: UserDetailsImpl): String {
        return generateTokenFromUsername(userPrincipal.username)
    }

    fun generateTokenFromUsername(username: String): String {
        return Jwts.builder().setSubject(username).setIssuedAt(Date())
            .setExpiration(Date(Date().time + jwtExpirationMs)).signWith(key)
            .compact()
    }

    fun getUserNameFromJwtToken(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token).body.subject
    }

    fun validateJwtToken(authToken: String): Boolean {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(authToken)
            return true
        } catch (e: JwtException) {
            logger.error("Invalid JWT signature: {}", e.message)
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token: {}", e.message)
        } catch (e: ExpiredJwtException) {
            logger.error("JWT token is expired: {}", e.message)
        } catch (e: UnsupportedJwtException) {
            logger.error("JWT token is unsupported: {}", e.message)
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty: {}", e.message)
        }
        return false
    }
}