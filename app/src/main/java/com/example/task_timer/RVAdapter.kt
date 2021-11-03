package com.example.task_timer

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




class RVAdapter(val cont: Context): RecyclerView.Adapter<RVAdapter.ItemViewHolder>()  {

    private var rv: List<Task> = listOf()

    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rvlist,parent,false )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.itemView.apply {



        }
    }
    override fun getItemCount() = rv.size

    fun setNote(n:List<Task>){
        rv=n
        notifyDataSetChanged()
    }


    abstract class CountUpTimer(private val secondsInFuture: Int, countUpIntervalSeconds: Int) : CountDownTimer(secondsInFuture.toLong() * 1000, countUpIntervalSeconds.toLong() * 1000) {

        abstract fun onCount(count: Int)

        override fun onTick(msUntilFinished: Long) {
            onCount(((secondsInFuture.toLong() * 1000 - msUntilFinished) / 1000).toInt())
        }
    }
}