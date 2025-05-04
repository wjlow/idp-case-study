package com.auth0.example.web

import com.auth0.example.model.Interview
import com.auth0.example.model.UserId
import com.auth0.example.service.InterviewService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Handles requests to "/api" endpoints.
 * @see com.auth0.example.security.SecurityConfig to see how these endpoints are protected.
 */
@RestController
@RequestMapping(
    path = ["api"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@CrossOrigin(origins = ["http://localhost:3000"])
class InterviewController(private val interviewService: InterviewService) {

    @GetMapping(value = ["/interviews"])
    fun getInterviews(authentication: Authentication): List<Interview> {
        val userId = UserId(authentication.name)
        val userRole = RoleExtractor.extractFromToken(authentication)

        log.info("Retrieving interviews for ${userId.value} with role=${userRole.name}")

        // Recruiter flow is intentionally unimplemented for this PoC
        return interviewService.getByCandidateId(userId)
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(InterviewController::class.java)
    }

}
