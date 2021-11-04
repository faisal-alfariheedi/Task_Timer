package com.example.task_timer.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*


@Dao
interface TaskDao {
    @Query("SELECT * from Task")
    fun getall(): LiveData<List<Task>>

    @Query("SELECT Time_spent from Task")
    fun gettime(): List<Int>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun addeditTask(task:Task)

    @Delete
    fun deleteTask(task:Task)
}