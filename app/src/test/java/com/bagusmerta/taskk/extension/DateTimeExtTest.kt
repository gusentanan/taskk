package com.bagusmerta.taskk.extension

import com.bagusmerta.taskk.utils.extensions.formatDateTime
import com.bagusmerta.taskk.utils.extensions.isSameDay
import com.bagusmerta.taskk.utils.extensions.isTomorrow
import com.bagusmerta.taskk.utils.extensions.isYesterday
import org.junit.Assert
import org.junit.Test
import java.time.LocalDateTime


class DateTimeExtTest {

    @Test
    fun isSameDay(){
        val currentDate = LocalDateTime.now()
        val isTodayDate = LocalDateTime.now().isSameDay(currentDate)
        Assert.assertEquals(true, isTodayDate)
    }

    @Test
    fun isTomorrow(){
        val isTomorrowDate = LocalDateTime.now().plusDays(1).isTomorrow(
            LocalDateTime.now()
        )
        Assert.assertEquals(true, isTomorrowDate)
    }

    @Test
    fun isYesterday(){
        val isYesterdayDate = LocalDateTime.now().minusDays(1).isYesterday(
            LocalDateTime.now()
        )
        Assert.assertEquals(true, isYesterdayDate)
    }

    @Test
    fun isDateFormatted(){
        val formattedDate = LocalDateTime.now().formatDateTime()
        Assert.assertEquals(LocalDateTime.now().formatDateTime(), formattedDate)
    }





}