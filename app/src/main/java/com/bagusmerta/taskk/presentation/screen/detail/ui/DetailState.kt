package com.bagusmerta.taskk.presentation.screen.detail.ui

import androidx.compose.ui.text.input.TextFieldValue
import com.bagusmerta.taskk.domain.model.TaskkCategory
import com.bagusmerta.taskk.domain.model.TaskkPriority
import com.bagusmerta.taskk.domain.model.TaskkToDo
import com.bagusmerta.taskk.utils.wrapper.DateTimeProviderImpl
import javax.annotation.concurrent.Immutable

@Immutable
data class DetailState(
    val editTaskkTitle: TextFieldValue = TextFieldValue(),
    val editTaskkNote: TextFieldValue = TextFieldValue(),
    val taskk: TaskkToDo = TaskkToDo(
        createdAt = DateTimeProviderImpl().getNowDate(),
        updatedAt = DateTimeProviderImpl().getNowDate()
    ),
    val categoryItems: List<CategoryItems> = initCategoryItems(),
    val priorityItems: List<PriorityItems> = initPriorityItems()
){
    val validTaskkTitle = editTaskkTitle.text.isNotBlank()
    val validTaskkNote = editTaskkNote.text.isNotBlank()

    companion object {
        private fun initCategoryItems(): List<CategoryItems> {
            return listOf(
                CategoryItems(
                    TaskkCategory.WORK, false
                ),
                CategoryItems(
                    TaskkCategory.GYM, false
                ),
                CategoryItems(
                    TaskkCategory.HOUSEHOLD, false
                ),
                CategoryItems(
                    TaskkCategory.SELF_HELP, true
                ),
                CategoryItems(
                    TaskkCategory.STUDY, false
                )
            )
        }

        private fun initPriorityItems(): List<PriorityItems> {
            return listOf(
                PriorityItems(
                    TaskkPriority.EASY, true
                ),
                PriorityItems(
                    TaskkPriority.HARD, false
                ),
                PriorityItems(
                    TaskkPriority.MID, false
                )
            )
        }
    }
}

data class CategoryItems(
    val category: TaskkCategory,
    val applied: Boolean
)

data class PriorityItems(
    val priority: TaskkPriority,
    val applied: Boolean
)
