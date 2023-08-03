package com.example.project_1_home.fragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.project_1_home.MainActivity
import com.example.project_1_home.R
import com.example.project_1_home.`interface`.OnPassData
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class EditTaskFragment : Fragment(), View.OnClickListener {
    lateinit var navController: NavController
    lateinit var myToolbar: androidx.appcompat.widget.Toolbar
    lateinit var edtTitle: EditText
    lateinit var edtBody: EditText
    var currentI: Int? = null
    var currentTitle: String? = null
    var currentBody: String? = null
    var currentCheck: Boolean? = null
    private lateinit var onPassDataToUpdate: OnPassData

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

    private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        myToolbar = view.findViewById(R.id.toolBar_EditTask)
        edtTitle = view.findViewById(R.id.edtTitle)
        edtBody = view.findViewById(R.id.edtBody)
        onPassDataToUpdate = activity as OnPassData
        view.findViewById<FloatingActionButton>(R.id.fabSave1).setOnClickListener(this)
        customToolBar(myToolbar)

        currentI = arguments?.getInt("single_i_key")
        currentTitle = arguments?.getString("single_title_key").toString()
        currentBody = arguments?.getString("single_body_key").toString()
        currentCheck = arguments?.getBoolean("single_checkbox_key")

        edtTitle.text = currentTitle!!.toEditable()
        edtBody.text = currentBody!!.toEditable()

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fabSave1 -> {
                val oldI = currentI
                val newTitle = edtTitle.text.toString()
                val newBody = edtBody.text.toString()
                val oldCheck = currentCheck

                if (newTitle == "") {
                    val snackBar =
                        Snackbar.make(
                            v,
                            "Title cannot be empty",
                            Snackbar.LENGTH_SHORT
                        )
                    snackBar.show()
                } else {
                    if (oldI != null && oldCheck != null) {
                        val snackBar =
                            Snackbar.make(
                                v,
                                "Edit successfully",
                                Snackbar.LENGTH_SHORT
                            )
                        snackBar.show()
                        onPassDataToUpdate.onPassDataToUpdate(oldI, newTitle, newBody, oldCheck)
                    }
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
    }
}