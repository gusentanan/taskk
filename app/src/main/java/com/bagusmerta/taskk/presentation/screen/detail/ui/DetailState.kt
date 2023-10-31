package com.bagusmerta.taskk.presentation.screen.detail.ui

import androidx.compose.ui.text.input.TextFieldValue
import com.bagusmerta.taskk.domain.model.TaskkCategory
import com.bagusmerta.taskk.domain.model.TaskkPriority
import com.bagusmerta.taskk.domain.model.TaskkRepeat
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
    val priorityItems: List<PriorityItems> = initPriorityItems(),
    val repeatableItems: List<RepeatableItems> = initRepeatableItems()
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

        private fun initRepeatableItems(): List<RepeatableItems> {
            return listOf(
                RepeatableItems(
                    TaskkRepeat.NEVER, true
                ),
                RepeatableItems(
                    TaskkRepeat.DAILY, false
                ),
                RepeatableItems(
                    TaskkRepeat.WEEKLY, false
                ),
                RepeatableItems(
                    TaskkRepeat.MONTHLY, false
                ),
                RepeatableItems(
                    TaskkRepeat.YEARLY, false
                ),
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

data class RepeatableItems(
    val repeat: TaskkRepeat,
    val applied: Boolean
)