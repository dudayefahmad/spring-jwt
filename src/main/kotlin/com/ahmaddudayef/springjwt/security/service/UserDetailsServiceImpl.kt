package com.ahmaddudayef.springjwt.security.service

import com.ahmaddudayef.springjwt.models.User
import com.ahmaddudayef.springjwt.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDetailsServiceImpl @Autowired constructor(
    val userRepository: UserRepository
) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = userRepository.findByUsername(username)
            .orElseThrow {
                UsernameNotFoundException(
                    "User Not Found with username: $username"
                )
            }
        return UserDetailsImpl.build(user)
    }

}