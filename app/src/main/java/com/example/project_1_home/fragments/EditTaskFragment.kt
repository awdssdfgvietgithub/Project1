package com.example.project_1_home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.project_1_home.MainActivity
import com.example.project_1_home.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class EditTaskFragment : Fragment(), View.OnClickListener {
    lateinit var navController: NavController
    lateinit var myToolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).supportActionBar!!.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        myToolbar = view.findViewById(R.id.toolBar_EditTask)
        view.findViewById<FloatingActionButton>(R.id.fabSave1).setOnClickListener(this)

        customToolBar(myToolbar)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fabSave1 -> {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun customToolBar(toolbar: androidx.appcompat.widget.Toolbar) {
        toolbar.setNavigationOnClickListener() {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onDetach() {
        super.onDetach()
        (activity as MainActivity).supportActionBar!!.show()
    }
}