package com.example.task_timer.db

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface TaskDao {
    @Query("SELECT * from Task")
    fun getall(): LiveData<List<Task>>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun addeditTask(task:Task)

    @Delete
    fun deleteTask(task:Task)
}