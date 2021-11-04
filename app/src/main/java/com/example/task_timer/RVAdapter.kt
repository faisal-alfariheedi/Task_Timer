package com.example.task_timer

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.task_timer.db.Task
import kotlinx.android.synthetic.main.rvlist.view.*


class RVAdapter(val cont: Fragment): RecyclerView.Adapter<RVAdapter.ItemViewHolder>()  {

    private var rv: List<Task> = listOf()

    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rvlist,parent,false )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val task = rv[position]
        holder.itemView.apply {
            tvtaskname.text = task.name
            tvtaskdesc.text = task.desc
            editbutton.setOnClickListener {
                if (cont is Editor)
                    cont.raiseDialog(task)
            }
            deletebutton.setOnClickListener {
                if (cont is Editor)
                    cont.mvm.delete(task)
            }
        }
    }

    override fun getItemCount() = rv.size

    fun setTask(n:List<Task>){
        rv=n
        notifyDataSetChanged()
    }

}