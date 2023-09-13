package com.bagusmerta.taskk.utils.wrapper

import java.time.LocalDateTime

interface DateTimeProvider {
    fun getNowDate(): LocalDateTime
}

class DateTimeProviderImpl: DateTimeProvider {
    override fun getNowDate(): LocalDateTime {
        return LocalDateTime.now()
    }
}