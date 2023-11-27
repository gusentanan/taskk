package com.bagusmerta.taskk.extension

import com.bagusmerta.taskk.R
import com.bagusmerta.taskk.model.TaskkCategory
import com.bagusmerta.taskk.utils.extensions.displayable
import org.junit.Assert
import org.junit.Test

class TaskkCategoryExtTest {
    @Test
    fun mapCategoryDisplayable(){
        Assert.assertEquals(R.string.taskk_category_displayable_study, TaskkCategory.STUDY.displayable())
        Assert.assertEquals(R.string.taskk_category_displayable_work, TaskkCategory.WORK.displayable())
        Assert.assertEquals(R.string.taskk_category_displayable_gym, TaskkCategory.GYM.displayable())
        Assert.assertEquals(R.string.taskk_category_displayable_house, TaskkCategory.HOUSEHOLD.displayable())
        Assert.assertEquals(R.string.taskk_category_displayable_selfhelp, TaskkCategory.SELF_HELP.displayable())
    }
}
