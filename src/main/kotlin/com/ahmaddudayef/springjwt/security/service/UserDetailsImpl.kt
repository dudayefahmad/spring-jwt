package com.ahmaddudayef.springjwt.security.service

import com.ahmaddudayef.springjwt.models.User
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors


class UserDetailsImpl constructor(
    @JvmField val id: Long,
    @JvmField val username: String,
    @JvmField val email: String,
    @JsonIgnore
    @JvmField val password: String,
    @JvmField val authorities: Set<GrantedAuthority>
) : UserDetails {

    companion object {
        const val serialVersionUID = 1L
        fun build(user: User): UserDetailsImpl {
            val authorities: List<GrantedAuthority> = user.roles.stream()
                .map { role -> SimpleGrantedAuthority(role.name?.name) }
                .collect(Collectors.toList())

            return UserDetailsImpl(
                user.id!!,
                user.username!!,
                user.email!!,
                user.password!!,
                authorities.toSet()
            )
        }
    }


    override fun getAuthorities(): Collection<out GrantedAuthority> {
        return authorities
    }

    fun getId(): Long {
        return id
    }

    fun getEmail(): String {
        return email
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}