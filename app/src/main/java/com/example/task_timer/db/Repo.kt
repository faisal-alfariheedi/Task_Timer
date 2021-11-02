package com.example.task_timer.db

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

//this class should not be used with in main or other activity


//this class is responsible for getting and setting the data in database

class repo{
    var db:TaskDao
    var list:LiveData<List<Task>>

    constructor(cont:Application){
        db=TaskDB.getInstance(cont).TaskDao()
        list=db.getall()
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


    //classes below are methods to update the database in the background
    class insTask(var db: TaskDao) : AsyncTask<Task, Void, String>(){
        override fun doInBackground(vararg p0: Task?): String {
            db.addeditTask(p0[0]!!)
            return ""
        }
    }
    class delTask(var db: TaskDao) : AsyncTask<Task, Void, String>(){
        override fun doInBackground(vararg p0: Task?): String {
            db.deleteTask(p0[0]!!)
            return ""
        }
    }
}
