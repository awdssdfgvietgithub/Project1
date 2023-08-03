package com.example.project_1_home.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.project_1_home.MainActivity
import com.example.project_1_home.R
import com.example.project_1_home.`interface`.OnPassData
import com.google.android.material.floatingactionbutton.FloatingActionButton


class InsertTaskFragment : Fragment(), View.OnClickListener {
    lateinit var navController: NavController
    lateinit var myToolbar: androidx.appcompat.widget.Toolbar
    lateinit var edtTile: EditText
    lateinit var edtBody: EditText
    private lateinit var onPassData: OnPassData

//    lateinit var callBack: ActivityCallBack
//
//    interface ActivityCallBack {
//        fun sendData(title: String, body: String = "")
//    }
//
//    fun setActivityCallBack(callback: ActivityCallBack) {
//        this.callBack = callback
//    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).supportActionBar!!.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_insert_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        myToolbar = view.findViewById(R.id.toolBar_InsertTask)
        edtTile = view.findViewById(R.id.edtTitle)
        edtBody = view.findViewById(R.id.edtBody)
        onPassData = activity as OnPassData
        view.findViewById<FloatingActionButton>(R.id.fabSave).setOnClickListener(this)
        customToolBar(myToolbar)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fabSave -> {
                onPassData.onPassData(edtTile.text.toString(), edtBody.text.toString())
            }
        }
    }

    private fun customToolBar(toolbar: androidx.appcompat.widget.Toolbar) {
        toolbar.setNavigationOnClickListener() {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onPause() {
        super.onPause()
        (activity as MainActivity).supportActionBar!!.show()
    }
}