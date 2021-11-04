package com.example.task_timer

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.task_timer.db.Task
import kotlinx.android.synthetic.main.rvlisttime.view.*
import kotlin.system.exitProcess


class RVAdapterMain(val cont: Fragment): RecyclerView.Adapter<RVAdapterMain.ItemViewHolder>()  {
    private var rv: List<Task> = listOf()
    private var TimeOff =-1


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rvlisttime, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val task = rv[position]

        holder.itemView.apply {
            var counter = object : CountUpTimer(5941, 1) {
                override fun onCount(count: Int) {
                    tvtimer.text = String.format("%02d:%02d", (count / 60) % 99, count % 60)
                    if (count >= 5941) {
                        cancel()
                        timerover(holder, position, count)
                    }
                }

                override fun onFinish() {}
            }
            tvtaskname.text = task.name
            tvtaskdesc.text = task.desc

            tvtaskdesc.setOnClickListener {
                if (cont is Main)
                    cont.raiseDialog(task)
            }

            taskclick.setOnClickListener {

                if (TimeOff == -1) { // ckeck other task
                    if (rv[position].timer_state == false) {
                        counter.start = rv[position].Time_spent
                        counter.start()

                        rv[position].timer_state = true
                        TimeOff = position

                    }
                } else {
                    Toast.makeText(
                        cont.context,
                        "wait sec to stop task then start another task",
                        Toast.LENGTH_SHORT
                    ).show()
                    rv[TimeOff].Time_spent = counter.time
                    if (cont is Main)
                        cont.mvm.addedit(rv[TimeOff])
                    counter.cancel()
                    rv[TimeOff].timer_state = false

                    if (TimeOff == position) {
                        TimeOff = -1
                    } else {
                        if (rv[position].timer_state == false) {
                            counter.start = rv[position].Time_spent
                            counter.start()
                            rv[position].timer_state = true
                            TimeOff = position

                        } else {
                            rv[position].Time_spent = counter.time
                            if (cont is Main) {
                                cont.mvm.addedit(rv[position])
                            }
                            tvtimer.text = String.format(
                                "%02d:%02d",
                                (counter.time / 60) % 99,
                                counter.time % 60
                            )
                            counter.cancel()
                            rv[position].timer_state = false
                            TimeOff = -1
                        }
                    }


                }

            }


        }


    }

    override fun getItemCount() = rv.size

    fun timerover(holder: ItemViewHolder, position: Int, count: Int) {
        if(count>5943){
            exitProcess(0)
        }
        holder.itemView.apply {
            rv[position].Time_spent = count
            if (cont is Main)
                cont.mvm.addedit(rv[position])
            tvtimer.text = String.format("%02d:%02d", (count / 60) % 99, count % 60)
            rv[position].timer_state = false
            TimeOff = -1
        }

    }

    fun setTask(n: List<Task>) {
        rv = n
        notifyDataSetChanged()
    }

    abstract class CountUpTimer(private var secondsInFuture: Int, countUpIntervalSeconds: Int,var start:Int=0) :
        CountDownTimer(secondsInFuture.toLong() * 1000, countUpIntervalSeconds.toLong() * 1000) {
        var time: Int=0


        open fun onCount(count: Int) {}

        override fun onTick(msUntilFinished: Long) {
            time=(((secondsInFuture.toLong() * 1000 - msUntilFinished) / 1000).toInt())+start
            onCount(time)
            Task.total_time.postValue(Task.total_time.value!!+1)
        }

        override fun onFinish() {}
    }

}