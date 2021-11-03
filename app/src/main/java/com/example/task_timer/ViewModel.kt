package com.example.task_timer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.task_timer.db.Task
import com.example.task_timer.db.repo

//use this class to to get data from database

class ViewModel(application: Application) : AndroidViewModel(application) {
    var rep= repo(application)
    private var list=rep.getAll()

    //this method will add the task to the data base but if there is conflict
    // with the id the element in database will be updated
    fun addedit(t: Task){
        rep.addedit(t)
    }

    //this method will delete an element with the same id
    fun delete(t: Task){
        rep.delete(t)
    }

    //this method will return all the data from the database
    fun getAll(): LiveData<List<Task>> {
        return list
    }
}