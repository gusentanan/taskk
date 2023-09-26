package com.bagusmerta.taskk.datasource

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.bagusmerta.taskk.DateFactory
import com.bagusmerta.taskk.data.local.dao.TaskkReadDao
import com.bagusmerta.taskk.data.local.dao.TaskkWriteDao
import com.bagusmerta.taskk.data.local.db.TaskkDatabase
import com.bagusmerta.taskk.data.local.entity.TaskkTodoDb
import com.bagusmerta.taskk.domain.model.TaskkCategory
import com.bagusmerta.taskk.domain.model.TaskkPriority
import com.bagusmerta.taskk.domain.model.TaskkStatus
import com.bagusmerta.taskk.domain.model.TaskkToDo
import com.bagusmerta.taskk.expect
import com.bagusmerta.taskk.utils.mapper.toTaskkTodoDb
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.time.LocalDateTime
import kotlin.time.ExperimentalTime

@ExperimentalTime
@RunWith(RobolectricTestRunner::class)
class TaskkWriteDaoTest {
    private lateinit var taskkWriteDao: TaskkWriteDao
    private lateinit var taskkReadDao: TaskkReadDao
    private lateinit var taskkDb: TaskkDatabase

    @Before
    fun setUp(){
        taskkDb = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TaskkDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        taskkWriteDao = taskkDb.taskkWriteDao()
        taskkReadDao = taskkDb.taskkReadDao()
    }

    @After
    fun tearDown(){
        taskkDb.close()
    }

    @Test
    fun `insertTaskk() success`() = runBlocking {
        val expectedValue1 = TaskkToDo(
            id = "1",
            name = "Do something",
            createdAt = DateFactory.constantDate,
            updatedAt = DateFactory.constantDate
        )
        val expectedValue2 = TaskkToDo(
            id = "2",
            name = "Do something again",
            createdAt = DateFactory.constantDate,
            updatedAt = DateFactory.constantDate
        )

        taskkWriteDao.insertTaskk(expectedValue1.toTaskkTodoDb())
        taskkWriteDao.insertTaskk(expectedValue2.toTaskkTodoDb())

        taskkReadDao.getListTaskk().expect(
            listOf(expectedValue1.toTaskkTodoDb(), expectedValue2.toTaskkTodoDb())
        )
    }

    @Test
    fun `deleteTaskk() success`() = runBlocking {
        val expectedValue1 = TaskkToDo(
            id = "1",
            name = "Do something",
            createdAt = DateFactory.constantDate,
            updatedAt = DateFactory.constantDate
        )
        val expectedValue2 = TaskkToDo(
            id = "2",
            name = "Do something again",
            createdAt = DateFactory.constantDate,
            updatedAt = DateFactory.constantDate
        )

        taskkWriteDao.insertTaskk(expectedValue1.toTaskkTodoDb())
        taskkWriteDao.insertTaskk(expectedValue2.toTaskkTodoDb())
        taskkWriteDao.deleteTaskkById(expectedValue1.id)

        taskkReadDao.getListTaskk().expect(
            listOf(expectedValue2.toTaskkTodoDb())
        )
    }


    @Test
    fun `updateTaskkTitle() success`() = runBlocking {
        val expectedValue1 = TaskkToDo(
            id = "1",
            name = "Do something",
            createdAt = DateFactory.constantDate,
            updatedAt = DateFactory.constantDate
        )

        val newName = "Do nothing"
        val updateAt = LocalDateTime.of(2023, 1, 2, 0, 0, 0, 0)

        taskkWriteDao.insertTaskk(expectedValue1.toTaskkTodoDb())
        taskkWriteDao.updateTaskkTitle(expectedValue1.id, newName, updateAt)

        taskkReadDao.getTaskkById(expectedValue1.id).expect(
            expectedValue1.copy(name = newName, updatedAt = updateAt).toTaskkTodoDb()
        )
    }

    @Test
    fun `updateTaskkNote() success`() = runBlocking {
        val expectedValue1 = TaskkToDo(
            id = "1",
            name = "Do something",
            createdAt = DateFactory.constantDate,
            updatedAt = DateFactory.constantDate
        )

        val newNote = "Do bla bla bla"
        val updateAt = LocalDateTime.of(2023, 1, 2, 0, 0, 0, 0)

        taskkWriteDao.insertTaskk(expectedValue1.toTaskkTodoDb())
        taskkWriteDao.updateTaskkNote(expectedValue1.id, newNote, updateAt)

        taskkReadDao.getTaskkById(expectedValue1.id).expect(
            expectedValue1.copy(note = newNote, updatedAt = updateAt).toTaskkTodoDb()
        )
    }

    @Test
    fun `updateTaskkCategory() success`() = runBlocking {
        val expectedValue1 = TaskkToDo(
            id = "1",
            name = "Do something",
            createdAt = DateFactory.constantDate,
            updatedAt = DateFactory.constantDate,
            taskkCategory = TaskkCategory.SELF_HELP
        )

        val newCategory = TaskkCategory.WORK
        val updateAt = LocalDateTime.of(2023, 1, 2, 0, 0, 0, 0)

        taskkWriteDao.insertTaskk(expectedValue1.toTaskkTodoDb())
        taskkWriteDao.updateTaskkCategory(expectedValue1.id, newCategory, updateAt)

        taskkReadDao.getTaskkById(expectedValue1.id).expect(
            expectedValue1.copy(taskkCategory = newCategory, updatedAt = updateAt).toTaskkTodoDb()
        )
    }

    @Test
    fun `updateTaskkPriority() success`() = runBlocking {
        val expectedValue1 = TaskkToDo(
            id = "1",
            name = "Do something",
            createdAt = DateFactory.constantDate,
            updatedAt = DateFactory.constantDate,
            taskkPriority = TaskkPriority.EASY
        )

        val newPriority = TaskkPriority.HARD
        val updateAt = LocalDateTime.of(2023, 1, 2, 0, 0, 0, 0)

        taskkWriteDao.insertTaskk(expectedValue1.toTaskkTodoDb())
        taskkWriteDao.updateTaskkPriority(expectedValue1.id, newPriority, updateAt)

        taskkReadDao.getTaskkById(expectedValue1.id).expect(
            expectedValue1.copy(taskkPriority = newPriority, updatedAt = updateAt).toTaskkTodoDb()
        )
    }

    @Test
    fun `updateTaskkDueDate() success`() = runBlocking {
        val expectedValue1 = TaskkToDo(
            id = "1",
            name = "Do something",
            createdAt = DateFactory.constantDate,
            updatedAt = DateFactory.constantDate,
        )

        val newLocalDate = LocalDateTime.of(2023, 1, 3, 0, 0, 0, 0)
        val updateAt = LocalDateTime.of(2023, 1, 2, 0, 0, 0, 0)
        val isDueDateSet = true
        taskkWriteDao.insertTaskk(expectedValue1.toTaskkTodoDb())
        taskkWriteDao.updateTaskkDueDate(expectedValue1.id, newLocalDate, updateAt, isDueDateSet)

        taskkReadDao.getTaskkById(expectedValue1.id).expect(
            expectedValue1.copy(dueDate = newLocalDate, isDueDateTimeSet = isDueDateSet, updatedAt = updateAt).toTaskkTodoDb()
        )
    }

    @Test
    fun `resetTaskkDueDate() success`() = runBlocking {
        val expectedValue1 = TaskkToDo(
            id = "1",
            name = "Do something",
            createdAt = DateFactory.constantDate,
            updatedAt = DateFactory.constantDate,
        )

        val newLocalDate = LocalDateTime.of(2023, 1, 3, 0, 0, 0, 0)
        val updateAt = LocalDateTime.of(2023, 1, 2, 0, 0, 0, 0)
        val isDueDateSet = false
        taskkWriteDao.insertTaskk(expectedValue1.toTaskkTodoDb())
        taskkWriteDao.updateTaskkDueDate(expectedValue1.id, newLocalDate, updateAt, isDueDateSet)

        taskkWriteDao.resetTaskkDueDate(expectedValue1.id, null, updateAt, false)

        taskkReadDao.getTaskkById(expectedValue1.id).expect(
            expectedValue1.copy(dueDate = null, isDueDateTimeSet = false, updatedAt = updateAt).toTaskkTodoDb()
        )
    }

    @Test
    fun `updateTaskkStatus() success`() = runBlocking {
        val expectedValue1 = TaskkToDo(
            id = "1",
            name = "Do something",
            createdAt = DateFactory.constantDate,
            updatedAt = DateFactory.constantDate,
        )

        val newStatus = TaskkStatus.COMPLETE
        val updateAt = LocalDateTime.of(2023, 1, 2, 0, 0, 0, 0)

        taskkWriteDao.insertTaskk(expectedValue1.toTaskkTodoDb())
        taskkWriteDao.updateTaskkStatus(newStatus, expectedValue1.id, updateAt)

        taskkReadDao.getTaskkById(expectedValue1.id).expect(
            expectedValue1.copy(status = newStatus, updatedAt = updateAt).toTaskkTodoDb()
        )
    }







}