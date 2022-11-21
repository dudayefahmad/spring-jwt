package com.ahmaddudayef.springjwt.repository

import com.ahmaddudayef.springjwt.models.ERole
import com.ahmaddudayef.springjwt.models.Role

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(name: ERole): Optional<Role>
}