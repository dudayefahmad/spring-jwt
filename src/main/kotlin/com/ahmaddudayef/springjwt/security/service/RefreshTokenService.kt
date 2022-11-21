package com.ahmaddudayef.springjwt.security.service

import com.ahmaddudayef.springjwt.exception.TokenRefreshException
import com.ahmaddudayef.springjwt.models.RefreshToken
import com.ahmaddudayef.springjwt.repository.RefreshTokenRepository
import com.ahmaddudayef.springjwt.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.util.*


@Service
class RefreshTokenService @Autowired constructor(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val userRepository: UserRepository
) {

    @Value("\${ahmaddudayef.app.jwtRefreshExpirationMs}")
    private val refreshTokenDurationMs: Long? = null

    fun findByToken(token: String): Optional<RefreshToken> {
        return refreshTokenRepository.findByToken(token)
    }

    fun createRefreshToken(userId: Long): RefreshToken {
        var refreshToken = RefreshToken()
        refreshToken.user = userRepository.findById(userId).get()
        refreshToken.expiryDate = refreshTokenDurationMs?.let { Instant.now().plusMillis(it) }
        refreshToken.token = UUID.randomUUID().toString()
        refreshToken = refreshTokenRepository.save(refreshToken)
        return refreshToken
    }

    fun verifyExpiration(token: RefreshToken): RefreshToken {
        token.expiryDate?.let { expiryDate ->
            if (expiryDate < Instant.now()) {
                refreshTokenRepository.delete(token)
                throw TokenRefreshException(token.token!!, "Refresh token was expired. Please make a new signin request")
            }
        }
        return token
    }

    @Transactional
    fun deleteByUserId(userId: Long): Int {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get())
    }


}