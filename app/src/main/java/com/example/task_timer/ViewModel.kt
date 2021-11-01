package com.example.task_timer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.task_timer.db.Task

class ViewModel(application: Application) : AndroidViewModel(application) {
    var rep=repo(application)
    private var list=rep.getAll()


    fun addedit(t: Task){
        rep.addedit(t)
    }
    fun delete(note: Task){
        rep.delete(note)
    }
    fun getAll(): LiveData<List<Task>> {
        return list
    }
}