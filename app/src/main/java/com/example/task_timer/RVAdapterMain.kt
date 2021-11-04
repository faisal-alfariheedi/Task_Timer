package com.example.task_timer

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.task_timer.db.Task
import kotlinx.android.synthetic.main.rvlisttime.view.*


class RVAdapterMain(val cont: Fragment): RecyclerView.Adapter<RVAdapterMain.ItemViewHolder>()  {
    private var rv: List<Task> = listOf()
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
                        if(cont is Main)
                            cont.mvm.addedit(rv[position])
                        counter.cancel()
                        rv[position].timer_state = false
                    }
                } else {
                    Toast.makeText(
                        cont.context,
                        "wait sec to stop task then click another task",
                        Toast.LENGTH_SHORT
                    ).show()
                    rv[position].Time_spent = counter.time
                    if(cont is Main)
                        cont.mvm.addedit(rv[position])
                    counter.cancel()
                    rv[position].timer_state = false
                    TimeOff = true

                }

            }
/*
            val task = rv[position]
                    tvtaskname.text = task.name
                    tvtaskdesc.text = task.desc
                    tvtimer.text = task.Time_spent.toString()
                    tvtaskdesc.setOnClickListener {
                        if (cont is Editor)
                            Main.raiseDialog(task)
                }

 */
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