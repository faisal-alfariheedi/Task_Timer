package com.example.task_timer

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView


class Main : Fragment() {
        private lateinit var taskTitle: TextView
        private lateinit var taskDescription: TextView
        private lateinit var taskNameET: EditText
        private lateinit var taskDescET: EditText
        private lateinit var addBtn: Button
        private lateinit var mainRV: RVAdapter
        private lateinit var ourRv: RecyclerView
        val mvm by lazy { ViewModelProvider(this).get(ViewModel::class.java)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_main, container, false)
        setHasOptionsMenu(true)

        taskTitle = v.findViewById(R.id.taskTitle)
        taskDescription = v.findViewById(R.id.taskDescription)

        taskNameET = v.findViewById(R.id.taskNameET)
        taskDescET = v.findViewById(R.id.taskDesET)

        addBtn = v.findViewById(R.id.addBtn)

        ourRv = v.findViewById(R.id.ourRv)
        mainRV = RVAdapter(this)


        return v
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

    }

    override fun onPrepareOptionsMenu(menu: Menu) {

        val item1 = menu!!.getItem(0)
        item1.setTitle("switch to game")


        return super.onPrepareOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.m1 -> {
                Navigation.findNavController(requireView()).navigate(R.id.action_main_to_editor)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}