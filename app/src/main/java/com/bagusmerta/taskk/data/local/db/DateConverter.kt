package com.bagusmerta.taskk.data.local.db

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

/**
 * type converter for DB so that any date time property will be able to use it.
 */
class DateConverter {
    /**
     * Convert date to LocalDateTime type
     */
    @TypeConverter
    fun toDate(date: Long?): LocalDateTime? {
        if (date == null) return null

        return date.toLocalDateTime()
    }

    /**
     * Convert date to Long type
     */
    @TypeConverter
    fun toDateLong(date: LocalDateTime?): Long? {
        if (date == null) return null

        return date.toMillis()
    }

}

/**
 * Extension to map into LocalDateTime type.
 * This needed because date time property from entity only receive [LocalDateTime]
 * @param zoneId date time to be transformed
 */
fun Long.toLocalDateTime(zoneId: ZoneId = ZoneId.systemDefault()): LocalDateTime {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(this), zoneId)
}

/**
 * Extension to map LocalDateTime into Long type.
 * This needed because notification and alarm manager require [Long]
 * transformed from [LocalDateTime]
 * @param zoneId date time to be transformed
 */
fun LocalDateTime.toMillis(zoneId: ZoneId = ZoneId.systemDefault()): Long {
    return atZone(zoneId).toInstant().toEpochMilli()
}

