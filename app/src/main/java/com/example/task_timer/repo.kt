package com.example.task_timer

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.task_timer.db.Task
import com.example.task_timer.db.TaskDB
import com.example.task_timer.db.TaskDao

class repo{
    var db:TaskDao
    var list:LiveData<List<Task>>

    constructor(cont:Application){
        db=TaskDB.getInstance(cont).TaskDao()
        list=db.getall()
    }

    fun addedit(t: Task) {
        insTask(db).execute(t)
    }

    fun delete(t: Task) {
        delTask(db).execute(t)
    }

    fun getAll(): LiveData<List<Task>> {
        return list
    }

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
