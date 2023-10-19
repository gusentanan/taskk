package com.bagusmerta.taskk.extension

import com.bagusmerta.taskk.R
import com.bagusmerta.taskk.domain.model.TaskkPriority
import com.bagusmerta.taskk.utils.extensions.displayable
import org.junit.Assert
import org.junit.Test

class TaskkPriorityExtTest {

    @Test
    fun mapTaskPriorityDisplayable(){
        Assert.assertEquals( R.string.taskk_priority_displayable_easy, TaskkPriority.EASY.displayable())
        Assert.assertEquals( R.string.taskk_priority_displayable_mid, TaskkPriority.MID.displayable())
        Assert.assertEquals( R.string.taskk_priority_displayable_hard, TaskkPriority.HARD.displayable())
    }
}