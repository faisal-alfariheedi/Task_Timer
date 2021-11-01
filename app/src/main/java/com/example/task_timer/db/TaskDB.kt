package com.example.task_timer.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities=[Task::class],version = 1,exportSchema = false)
abstract class TaskDB: RoomDatabase() {

    companion object {
        @Volatile
        var instance: TaskDB?=null
        fun getInstance(cont: Context): TaskDB {
            return instance ?:synchronized(this){
                instance ?: buildDatabase(cont).also{ instance =it}
            }
        }
        fun buildDatabase(cont: Context): TaskDB {
            return Room.databaseBuilder(cont, TaskDB::class.java,"note").build()
        }
    }
    abstract fun TaskDao(): TaskDao
}