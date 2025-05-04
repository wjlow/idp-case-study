package com.auth0.example.service

import com.auth0.example.model.Interview
import com.auth0.example.model.UserId
import com.auth0.example.repository.InterviewRepository
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import kotlin.test.assertEquals

class InterviewServiceTest {
    private val fixedInstant = Instant.parse("2025-06-10T10:00:00Z")
    private val zoneId = ZoneId.of("UTC")
    private val clock = Clock.fixed(fixedInstant, zoneId)
    private val candidateId = UserId("test-candidate-id")

    @Test
    fun `getByCandidateId should return interviews with a future start time`() {
        val now = LocalDateTime.now(clock)
        val notFutureInterview = Interview(
            uuid = UUID.randomUUID(),
            candidateId = candidateId,
            recruiterId = UserId("recruiter"),
            startTime = now,
            endTime = now.plusDays(1)
        )
        val expectedFutureInterview = Interview(
            uuid = UUID.randomUUID(),
            candidateId = candidateId,
            recruiterId = UserId("recruiter"),
            startTime = now.plusMinutes(1),
            endTime = now.plusDays(1)
        )

        val interviews = listOf(
            notFutureInterview,
            expectedFutureInterview
        )

        val stubInterviewRepository = InterviewRepository { _ -> interviews }

        val interviewService = InterviewService(stubInterviewRepository, clock)
        val result = interviewService.getByCandidateId(candidateId)
        assertEquals(listOf(expectedFutureInterview), result)
    }

    @Test
    fun `getByCandidateId should exclude interviews with a different candidateId`() {
        val now = LocalDateTime.now(clock)
        val anotherCandidatesInterview = Interview(
            uuid = UUID.randomUUID(),
            candidateId = UserId("another-candidate-id"),
            recruiterId = UserId("recruiter"),
            startTime = now.plusDays(1),
            endTime = now.plusDays(2)
        )

        val interviews = listOf(anotherCandidatesInterview)

        val stubInterviewRepository = InterviewRepository { _ -> interviews }

        val interviewService = InterviewService(stubInterviewRepository, clock)
        val result = interviewService.getByCandidateId(candidateId)
        assertEquals(listOf(), result)
    }
}