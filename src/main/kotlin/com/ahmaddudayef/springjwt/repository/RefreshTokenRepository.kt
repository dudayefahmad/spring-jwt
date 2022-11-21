package com.ahmaddudayef.springjwt.repository

import com.ahmaddudayef.springjwt.models.RefreshToken
import com.ahmaddudayef.springjwt.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface RefreshTokenRepository : JpaRepository<RefreshToken, Long> {

    fun findByToken(token: String): Optional<RefreshToken>

    @Modifying
    fun deleteByUser(user: User): Int

}