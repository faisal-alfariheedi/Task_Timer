package com.example.task_timer.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="Task")
class Task(
    @ColumnInfo(name="Name")
    var name: String,
    @ColumnInfo(name="Description")
    var desc:String,
    @ColumnInfo(name="Time_spent")
    var Time_spent:Int,
    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name="ID")
    var id:Int
)