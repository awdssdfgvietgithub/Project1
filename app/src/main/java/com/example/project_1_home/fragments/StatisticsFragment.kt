package com.example.project_1_home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.project_1_home.MainActivity
import com.example.project_1_home.R

class StatisticsFragment : Fragment() {
    lateinit var txtPercentOfActiveTasks: TextView
    lateinit var txtPercentOfCompletedTasks: TextView
    var sizeCompleted: Float? = 0f
    var sizeActive: Float? = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).supportActionBar!!.title = "Statistics"
        (activity as MainActivity).myToolbar.menu.clear()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onPause() {
        super.onPause()
        (activity as MainActivity).myToolbar.inflateMenu(R.menu.menu_toolbar_tasklist)
        (activity as MainActivity).myToolbar.title = "Todo"

    }
}