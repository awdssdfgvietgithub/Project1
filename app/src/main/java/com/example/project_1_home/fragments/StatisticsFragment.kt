package com.example.project_1_home.fragments

import com.example.project_1_home.R
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.project_1_home.MainActivity


class StatisticsFragment : Fragment() {


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