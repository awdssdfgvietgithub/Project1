package com.example.project_1_home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.project_1_home.MainActivity
import com.example.project_1_home.R
import com.example.project_1_home.adapter.TaskAdapter
import com.example.project_1_home.model.TaskModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class TaskListFragment : Fragment(), View.OnClickListener {
    lateinit var navController: NavController
    lateinit var dataSet: MutableList<TaskModel>
    lateinit var recyclerView: RecyclerView
    lateinit var taskAdapter: TaskAdapter
    lateinit var containerRecyclerView: ConstraintLayout
    lateinit var containerNoHaveTask: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataSet = mutableListOf<TaskModel>()
        dataSet.apply {
            add(
                TaskModel(
                    "Learning Kotlin",
                    "I'm learning Kotlin, do u want to learn Kotlin too?",
                    true
                )
            )
            add(
                TaskModel(
                    "Learning Java",
                    "I'm learning Java, do u want to learn Java too?",
                    true
                )
            )
            add(
                TaskModel(
                    "Learning Android",
                    "I'm learning Android, do u want to learn Android too?",
                    false
                )
            )
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        recyclerView = view.findViewById(R.id.recyclerView)
        containerRecyclerView = view.findViewById(R.id.container_recyclerview)
        containerNoHaveTask = view.findViewById(R.id.container_nohavetask)
        view.findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener(this)

        //adapter
        taskAdapter = TaskAdapter(dataSet) {
            navController!!.navigate(R.id.action_taskListFragment_to_editTaskFragment)
        }
        val linearLayoutManager = LinearLayoutManager(view.context)
        recyclerView.apply {
            setItemViewCacheSize(2)
            //setRecycledViewPool(5)
            layoutManager = linearLayoutManager
            adapter = taskAdapter
        }

        //show list or logo
        if (taskAdapter.itemCount > 0) {
            containerRecyclerView.visibility = View.VISIBLE
            containerNoHaveTask.visibility = View.GONE
        } else {
            containerRecyclerView.visibility = View.GONE
            containerNoHaveTask.visibility = View.VISIBLE
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fabAdd -> navController!!.navigate(R.id.action_taskListFragment_to_insertTaskFragment)
        }
    }
}