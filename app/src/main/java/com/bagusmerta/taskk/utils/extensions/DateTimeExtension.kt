package com.bagusmerta.taskk.utils.extensions

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Locale

fun LocalDateTime.isSameDay(dateTime: LocalDateTime): Boolean{
    return toLocalDate().isEqual(dateTime.toLocalDate())
}

fun LocalDateTime.isTomorrow(dateTime: LocalDateTime): Boolean{
    return toLocalDate().isEqual(dateTime.toLocalDate().plusDays(1))
}

fun LocalDateTime.isYesterday(dateTime: LocalDateTime): Boolean{
    return toLocalDate().isEqual(dateTime.toLocalDate().minusDays(1))
}

fun LocalDateTime.formatDateTimeSecond(): String {
    val pattern = "dd MMM yyyy HH:mm:ss"
    return format(pattern)
}

fun LocalDateTime.formatDateSecond(): String {
    val pattern = "HH:mm:ss"
    return format(pattern)
}

fun LocalDateTime.formatDateMinute(): String {
    val pattern = "HH:mm"
    return format(pattern)
}

fun LocalDateTime.formatDateTime(): String {
    val pattern = "EEE, dd MMM yyyy"
    return format(pattern)
}

fun LocalDateTime.formatDate(): String {
    val pattern = "dd MMM yyyy"
    return format(pattern)
}

fun LocalDateTime.formatMonth(): String {
    val pattern = "MMM yyyy"
    return format(pattern)
}

fun LocalDateTime.format(pattern: String, zoneId: ZoneId = ZoneId.systemDefault()): String {
    val locale = Locale.getDefault()

    return SimpleDateFormat(pattern, locale).format(atZone(zoneId).toInstant().toEpochMilli())
}