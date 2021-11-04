package com.example.task_timer

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task_timer.db.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Main : Fragment() {
        private lateinit var tvtotal:TextView
        private lateinit var rvAdapter: RVAdapterMain
        private lateinit var ourRv: RecyclerView
        val mvm by lazy { ViewModelProvider(this).get(ViewModel::class.java)}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_main, container, false)
        setHasOptionsMenu(true)
        init(v)




        return v
    }
    fun init(v: View) {

        tvtotal = v.findViewById(R.id.tvtotal)
        ourRv = v.findViewById(R.id.rvMain)
        rvAdapter = RVAdapterMain(this)
        ourRv.adapter = rvAdapter
        ourRv.layoutManager = LinearLayoutManager(requireContext())

        mvm.getAll().observe(viewLifecycleOwner, {
            rvAdapter.setTask(it)
            Toast.makeText(requireContext(), "updated", Toast.LENGTH_SHORT).show()
        })
        mvm.gettime().observe(viewLifecycleOwner, {
            var tot=0
            for (i in it){
                tot+=i
            }
            tvtotal.text = "Total time spent on tasks : ${
                String.format(
                    "%02d:%02d:%02d",
                    tot / 3600,
                    (tot / 60) % 60,
                    tot % 60
                )
            }"
        })
        CoroutineScope(Dispatchers.IO).launch {
            var temp=mvm.getAll().value
            if (temp != null) {
                for (i in mvm.getAll().value!!){
                    Task.total_time.postValue(Task.total_time.value?.plus(i.Time_spent))
                }
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

    }

    override fun onPrepareOptionsMenu(menu: Menu) {

        val item1 = menu!!.getItem(0)
        item1.setTitle("switch to Editor")


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
    //////////// dialog //////////////

    fun raiseDialog (task : Task) {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        val showTask = TextView(requireContext())
        showTask.text = task.desc
        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })
           // .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
             //   dialog.cancel()
            //})
        val alert = dialogBuilder.create()
        alert.setTitle(task.name)
        alert.setView(showTask)
        alert.show()
    }



    }

