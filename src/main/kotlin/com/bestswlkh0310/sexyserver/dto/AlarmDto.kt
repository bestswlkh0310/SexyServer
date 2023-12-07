package com.bestswlkh0310.sexyserver.dto

import java.time.LocalDateTime

data class AlarmDto(
    val title: String,
    val text: String,
    val roomName: String,
    val packageName: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
)



