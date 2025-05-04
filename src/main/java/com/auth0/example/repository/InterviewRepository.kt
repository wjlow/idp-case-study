package com.auth0.example.repository

import com.auth0.example.model.Interview
import com.auth0.example.model.UserId
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

fun interface InterviewRepository {
    fun getByCandidateId(candidateId: UserId): List<Interview>
}

@Repository
class InMemoryInterviewRepository: InterviewRepository {
    private val db: Map<UserId, List<Interview>> = createStubData()

    override fun getByCandidateId(candidateId: UserId): List<Interview> {
        return db.getOrDefault(candidateId, listOf())
    }

    private fun createStubData(): Map<UserId, List<Interview>> {
        val interview1 = Interview(
            uuid = UUID.randomUUID(),
            candidateId = UserId("auth0|68137403c7dacf733071e651"),
            recruiterId = UserId("auth0|6815992f74cf164e7edf5fd5"),
            startTime = LocalDateTime.parse("2025-06-01T10:00:00"),
            endTime = LocalDateTime.parse("2025-06-01T11:00:00")
        )

        val interview2 = Interview(
            uuid = UUID.randomUUID(),
            candidateId = UserId("auth0|68137403c7dacf733071e651"),
            recruiterId = UserId("auth0|6815992f74cf164e7edf5fd5"),
            startTime = LocalDateTime.parse("2025-06-02T10:00:00"),
            endTime = LocalDateTime.parse("2025-06-02T11:00:00")
        )

        val interview3 = Interview(
            uuid = UUID.randomUUID(),
            candidateId = UserId("auth0|100037403c7dacf7330e999"),
            recruiterId = UserId("auth0|681598e344065548a3214aaa"),
            startTime = LocalDateTime.parse("2025-04-01T10:00:00"),
            endTime = LocalDateTime.parse("2025-04-01T11:00:00")
        )

        val interview4 = Interview(
            uuid = UUID.randomUUID(),
            candidateId = UserId("auth0|abc123"),
            recruiterId = UserId("auth0|681598e344065548a3214aaa"),
            startTime = LocalDateTime.parse("2025-06-03T10:00:00"),
            endTime = LocalDateTime.parse("2025-06-03T11:00:00")
        )

        return mapOf(
            UserId("auth0|68137403c7dacf733071e651") to listOf(
                interview1, interview2, interview3
            ),
            UserId("auth0|abc123") to listOf(
                interview4
            )
        )
    }
}
