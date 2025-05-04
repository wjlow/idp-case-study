package com.auth0.example.web

import com.auth0.example.model.UserRole
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken

object RoleExtractor {
    const val roleClaim: String = "https://seek-idp.wjlow.com/roles"

    fun extractFromToken(authentication: Authentication): UserRole {
        if (authentication !is JwtAuthenticationToken) {
            return UserRole.UNKNOWN
        }

        val roles = authentication.token.getClaimAsStringList(roleClaim)

        val isCandidate = roles != null && roles.contains("candidate")
        val isRecruiter = roles != null && roles.contains("recruiter")

        return if (isCandidate) {
            UserRole.CANDIDATE
        } else if (isRecruiter) {
            UserRole.RECRUITER
        } else {
            UserRole.UNKNOWN
        }
    }
}
