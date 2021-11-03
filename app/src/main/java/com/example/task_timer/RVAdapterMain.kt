package com.example.task_timer

import android.app.Application
import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.task_timer.db.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.os.CountDownTimer
import android.widget.Toast
import com.example.task_timer.db.repo
import kotlinx.android.synthetic.main.rvlist.view.*


class RVAdapterMain( val cont: Context,application: Application): RecyclerView.Adapter<RVAdapterMain.ItemViewHolder>()  {
    private var rv: List<Task> = listOf()
    var rep = repo(application)
    private var TimeOff = true
    var counter = object : CountUpTimer(30, 1) {}

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rvlisttime, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.itemView.apply {
            taskclick.setOnClickListener {

                if (TimeOff == true) { // ckeck other task
                    if (rv[position].timer_state == false) {
                        counter.start()
                        rv[position].timer_state = true
                        TimeOff = false

                    } else {
                        rv[position].Time_spent = counter.time
                        rep.addedit(rv[position])
                        counter.cancel()
                        rv[position].timer_state = false
                    }
                } else {
                    Toast.makeText(cont,
                        "wait sec to stop task then click another task",
                        Toast.LENGTH_SHORT).show()
                    rv[position].Time_spent = counter.time
                    rep.addedit(rv[position])
                    counter.cancel()
                    rv[position].timer_state = false
                    TimeOff = true

                }

            }
        }
    }

    override fun getItemCount() = rv.size

    fun setTask(n: List<Task>) {
        rv = n
        notifyDataSetChanged()
    }


    abstract class CountUpTimer(private var secondsInFuture: Int, countUpIntervalSeconds: Int) :
        CountDownTimer(secondsInFuture.toLong() * 1000, countUpIntervalSeconds.toLong() * 1000) {
        var time: Int = 0

        fun onCount(count: Int) {

            time = count
        }

        override fun onTick(msUntilFinished: Long) {
            onCount(((secondsInFuture.toLong() * 1000 - msUntilFinished) / 1000).toInt())

        }

        override fun onFinish() {}
    }
}