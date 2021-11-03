package com.example.task_timer

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.task_timer.db.Task
import kotlinx.android.synthetic.main.fragment_custom_dialog.view.*

class CustomDialogFragment(task:Task, Frag : Fragment): DialogFragment() {
var edtask=task
    val frag=Frag


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_custom_dialog,container,false)
        rootView.btnCancel.setOnClickListener {
            dismiss()
        }
        rootView.setOnClickListener {
            val etTitle=rootView.findViewById<EditText>(R.id.etTitle)
            val etDec=rootView.findViewById<EditText>(R.id.etDec)
            edtask.name=etTitle.toString()
            edtask.desc=etDec.toString()
            if(frag is Editor)
            frag.mvm.addedit(edtask)
        }
        return rootView
    }

}
