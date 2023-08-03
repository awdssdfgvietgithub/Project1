package com.example.project_1_home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.project_1_home.MainActivity
import com.example.project_1_home.R
import com.example.project_1_home.`interface`.OnPassData
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class InsertTaskFragment : Fragment(), View.OnClickListener {
    lateinit var navController: NavController
    lateinit var myToolbar: androidx.appcompat.widget.Toolbar
    lateinit var edtTile: EditText
    lateinit var edtBody: EditText
    private lateinit var onPassData: OnPassData

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

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fabSave -> {
                if (edtTile.text.toString() == "") {
                    val snackBar =
                        Snackbar.make(
                            v,
                            "Tasks cannot be empty",
                            Snackbar.LENGTH_SHORT
                        )
                    snackBar.show()
                } else {
                    val snackBar =
                        Snackbar.make(
                            v,
                            "Task added",
                            Snackbar.LENGTH_SHORT
                        )
                    snackBar.show()
                    onPassData.onPassData(edtTile.text.toString(), edtBody.text.toString())
                }
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
        edtTile.text.clear()
        edtBody.text.clear()
    }
}