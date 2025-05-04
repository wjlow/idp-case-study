package com.auth0.example.web

import com.auth0.example.model.UserRole
import org.junit.jupiter.api.Test
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import kotlin.test.assertEquals

class RoleExtractorTest {
    private val headersMap = mapOf("dummy" to listOf("header"))
    private val partialJwt = Jwt
        .withTokenValue("token-value")
        .headers { it.putAll(headersMap) }

    @Test
    fun `extractFromToken should return CANDIDATE if 'candidate' appears in claims`() {
        val claimsMap = mapOf(RoleExtractor.roleClaim to listOf("candidate"))
        val jwt = partialJwt.claims { it.putAll(claimsMap) }.build()
        val token = JwtAuthenticationToken(jwt)

        val result = RoleExtractor.extractFromToken(token)
        assertEquals(UserRole.CANDIDATE, result)
    }

    @Test
    fun `extractFromToken should return RECRUITER if 'recruiter' appears in claims`() {
        val claimsMap = mapOf(RoleExtractor.roleClaim to listOf("recruiter"))
        val jwt = partialJwt.claims { it.putAll(claimsMap) }.build()
        val token = JwtAuthenticationToken(jwt)

        val result = RoleExtractor.extractFromToken(token)
        assertEquals(UserRole.RECRUITER, result)
    }

    @Test
    fun `extractFromToken should return UNKNOWN if neither 'candidate' nor 'recruiter' appear in claims`() {
        val claimsMap = mapOf(RoleExtractor.roleClaim to listOf("something-else"))
        val jwt = partialJwt.claims { it.putAll(claimsMap) }.build()
        val token = JwtAuthenticationToken(jwt)

        val result = RoleExtractor.extractFromToken(token)
        assertEquals(UserRole.UNKNOWN, result)
    }
}