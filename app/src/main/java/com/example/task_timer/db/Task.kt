package com.example.task_timer.db

import androidx.lifecycle.MutableLiveData
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
    var Time_spent:Int=0,
    @ColumnInfo(name="timer state")
    var timer_state:Boolean=false,
    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name="ID")
    var id:Int=0
){
    companion object{
        var total_time= MutableLiveData<Int>().apply{postValue(0)}

    }
}