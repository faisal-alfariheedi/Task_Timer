package com.example.task_timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//view model will return live data
//use this
//Viewmodel.getAll().observe(this,{
//            ad.setTask(it)
//            Toast.makeText(this,"updated",Toast.LENGTH_SHORT).show()
//        })
//
// ///////////////////////////////////////this function must be in RVadapter
// fun setTask(t:List<Note>){
//        rv=t
//        notifyDataSetChanged()
//    }

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}