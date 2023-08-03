package com.example.project_1_home.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
    var title: String? = null
    var body: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataSet = (activity as MainActivity).data()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(requireContext(), "???", Toast.LENGTH_SHORT).show()
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

        title = arguments?.getString("title_key")
        body = arguments?.getString("body_key")
        if (title != null && title != "") {
            (activity as MainActivity).insert(title.toString(), body.toString())
            taskAdapter.notifyDataSetChanged()
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fabAdd -> {
                navController!!.navigate(R.id.action_taskListFragment_to_insertTaskFragment)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.filter_all -> {
            (activity as MainActivity).filter("all")
            true
        }
        R.id.filter_active -> {
            (activity as MainActivity).filter("active")
            Toast.makeText(view?.context, "filter_active", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.filter_completed -> {
            (activity as MainActivity).filter("completed")
            //Toast.makeText(this, "filter_completed", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.more_clear -> {
            //(activity as MainActivity).removeAllTaskCompleted()
            //Toast.makeText(this, "more_clear", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.more_refresh -> {
            //(activity as MainActivity).refreshListTask()
            //Toast.makeText(this, "more_refresh", Toast.LENGTH_SHORT).show()
            true
        }
        else -> {
            false
        }
    }

    fun addTask(title: String, body: String) {
        (activity as MainActivity).insert(title, body)
        taskAdapter.notifyDataSetChanged()
    }
}