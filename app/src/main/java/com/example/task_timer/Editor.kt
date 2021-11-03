package com.example.task_timer

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView


class Editor : Fragment() {
    private lateinit var rvAdapter: RVAdapter
    private lateinit var ourRv: RecyclerView
    val mvm by lazy { ViewModelProvider(this).get(ViewModel::class.java)}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_editor, container, false)
        setHasOptionsMenu(true)
        init(v)

        return v
    }
    fun init(v: View) {

        ourRv=v.findViewById(R.id.ourRv)
        rvAdapter=RVAdapter(requireContext())
        mvm.getAll().observe(viewLifecycleOwner,{
            rvAdapter.setTask(it)
            Toast.makeText(requireContext(),"updated", Toast.LENGTH_SHORT).show()
        })
    }


    /////////////////////menu////////////////////////////


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

    }

    override fun onPrepareOptionsMenu(menu: Menu) {

        val item1 = menu!!.getItem(0)
        item1.title = "switch to main"


        return super.onPrepareOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.m1 -> {
                Navigation.findNavController(requireView()).navigate(R.id.action_editor_to_main)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}