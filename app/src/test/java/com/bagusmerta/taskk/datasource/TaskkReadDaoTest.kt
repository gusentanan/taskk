package com.bagusmerta.taskk.datasource

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.bagusmerta.taskk.DateFactory
import com.bagusmerta.taskk.data.local.dao.TaskkReadDao
import com.bagusmerta.taskk.data.local.dao.TaskkWriteDao
import com.bagusmerta.taskk.data.local.db.TaskkDatabase
import com.bagusmerta.taskk.expect
import com.bagusmerta.taskk.model.TaskkOverallCount
import com.bagusmerta.taskk.model.TaskkStatus
import com.bagusmerta.taskk.model.TaskkToDo
import com.bagusmerta.taskk.utils.mapper.toTaskkTodoDb
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.time.ExperimentalTime


@ExperimentalTime
@RunWith(RobolectricTestRunner::class)
class TaskkReadDaoTest {

    private lateinit var taskkReadDao: TaskkReadDao
    private lateinit var taskkWriteDao: TaskkWriteDao
    private lateinit var taskkDatabase: TaskkDatabase

    @Before
    fun setUp(){
        taskkDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TaskkDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        taskkReadDao = taskkDatabase.taskkReadDao()
        taskkWriteDao = taskkDatabase.taskkWriteDao()
    }

    @After
    fun tearDown(){
        taskkDatabase.close()
    }


    @Test
    fun `getListTaskk() should return list of taskk`() = runBlocking {
        val expectedValue1 = TaskkToDo(
            id = "1",
            name = "Do something",
            createdAt = DateFactory.constantDate,
            updatedAt = DateFactory.constantDate
        )
        val expectedValue2 = TaskkToDo(
            id = "2",
            name = "Do Nothing",
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
    fun `getTaskkById() should return single taskk`() = runBlocking {
        val expectedValue1 = TaskkToDo(
            id = "1",
            name = "Do something",
            createdAt = DateFactory.constantDate,
            updatedAt = DateFactory.constantDate
        )

        taskkWriteDao.insertTaskk(expectedValue1.toTaskkTodoDb())

        taskkReadDao.getTaskkById(expectedValue1.id).expect(
            expectedValue1.toTaskkTodoDb()
        )
    }

    @Test
    fun `getOverallCountTaskk() success`() = runBlocking {
        val expectedValue1 = TaskkToDo(
            id = "1",
            name = "Do something",
            createdAt = DateFactory.constantDate,
            updatedAt = DateFactory.constantDate,
            status = TaskkStatus.COMPLETE
        )
        val expectedValue2 = TaskkToDo(
            id = "2",
            name = "Do Nothing",
            createdAt = DateFactory.constantDate,
            updatedAt = DateFactory.constantDate,
            status = TaskkStatus.COMPLETE
        )

        val expectedCount = TaskkOverallCount(2, 0)

        taskkWriteDao.insertTaskk(expectedValue1.toTaskkTodoDb())
        taskkWriteDao.insertTaskk(expectedValue2.toTaskkTodoDb())

        taskkReadDao.getOverallCountTaskk().expect(
            expectedCount
        )
    }

}