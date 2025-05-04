package com.auth0.example.service

import com.auth0.example.model.Interview
import com.auth0.example.model.UserId
import com.auth0.example.repository.InterviewRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.Clock
import java.time.LocalDateTime

@Service
class InterviewService(private val interviewRepository: InterviewRepository, private val clock: Clock) {
    fun getByCandidateId(candidateId: UserId): List<Interview> {
        val allInterviews = interviewRepository.getByCandidateId(candidateId)
        val candidateInterviews = allInterviews.filter { authorizeCandidateId(it, candidateId) }
        val currentTime = LocalDateTime.now(clock)
        return candidateInterviews.filter { it.startTime.isAfter(currentTime) }
    }

    private fun authorizeCandidateId(interview: Interview, candidateId: UserId): Boolean {
        if (interview.candidateId == candidateId) {
            return true
        } else {
            log.warn("$candidateId is attempting unauthorized access to interview ${interview.uuid}")
            return false
        }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(InterviewService::class.java)
    }
}
