package com.example.task_timer.db

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.CoroutinesRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//this class should not be used with in main or other activity


//this class is responsible for getting and setting the data in database

class repo{
    var db:TaskDao
    var list:LiveData<List<Task>>
    var total_time: MutableLiveData<List<Int>>

    constructor(cont:Application){
        db=TaskDB.getInstance(cont).TaskDao()
        list=db.getall()

        total_time=MutableLiveData<List<Int>>().apply{CoroutineScope(Dispatchers.IO).launch{postValue(db.gettime())}}
    }

    //this method will add the task to the data base but if there is conflict
    // with the id the element in database will be updated
    fun addedit(t: Task) {
        insTask(db).execute(t)
    }

    //this method will delete an element with the same id
    fun delete(t: Task) {
        delTask(db).execute(t)
    }

    //this method will return all the data from the database
    fun getAll(): LiveData<List<Task>> {
        return list
    }

    fun gettime(): MutableLiveData<List<Int>> {
        return total_time
    }


    //classes below are methods to update the database in the background
    class insTask(var db: TaskDao) : AsyncTask<Task, Void, String>(){
        override fun doInBackground(vararg p0: Task?): String {
            db.addeditTask(p0[0]!!)
            return ""
        }
    }
    class delTask(var db: TaskDao) : AsyncTask<Task, Void, String>(){
        override fun doInBackground(vararg p0: Task?): String {
            Task.total_time.postValue(Task.total_time.value!!-p0[0]!!.Time_spent)
            db.deleteTask(p0[0]!!)

            return ""
        }
    }
}
