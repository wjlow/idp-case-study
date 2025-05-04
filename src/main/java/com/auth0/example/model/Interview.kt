package com.auth0.example.model

import java.time.LocalDateTime
import java.util.*

@JvmRecord
data class Interview(
    val uuid: UUID,
    val candidateId: UserId,
    val recruiterId: UserId,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime
)
