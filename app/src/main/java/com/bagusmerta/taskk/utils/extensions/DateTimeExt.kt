package com.bagusmerta.taskk.utils.extensions

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.Locale

val DEFAULT_TASKK_LOCAL_TIME: LocalTime = LocalTime.of(23, 59)
fun LocalDateTime.isSameDay(dateTime: LocalDateTime): Boolean{
    return toLocalDate().isEqual(dateTime.toLocalDate())
}

fun LocalDateTime.isTomorrow(dateTime: LocalDateTime): Boolean{
    return toLocalDate().isEqual(dateTime.toLocalDate().plusDays(1))
}

fun LocalDateTime.isYesterday(dateTime: LocalDateTime): Boolean{
    return toLocalDate().isEqual(dateTime.toLocalDate().minusDays(1))
}


fun LocalDateTime.formatDateTime(): String {
    val pattern = "EEE, dd MMM yyyy"
    return format(pattern)
}

fun LocalDateTime.format(pattern: String, zoneId: ZoneId = ZoneId.systemDefault()): String {
    val locale = Locale.getDefault()

    return SimpleDateFormat(pattern, locale).format(atZone(zoneId).toInstant().toEpochMilli())
}
