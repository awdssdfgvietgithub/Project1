package com.example.project_1_home.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_1_home.MainActivity
import com.example.project_1_home.R
import com.example.project_1_home.`interface`.OnPassData
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
    lateinit var layoutTaskList: ConstraintLayout
    lateinit var itemMenuFilterClick: String
    lateinit var itemMenuMoreClick: String
    private lateinit var onPassNumberTasks: OnPassData
    var title: String? = null
    var body: String? = null
    var i: Int? = null
    var titleUpdated: String? = null
    var bodyUpdated: String? = null
    var checkBoxUpdated: Boolean? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataSet = (activity as MainActivity).data()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        layoutTaskList = view.findViewById(R.id.layout_task_list)
        onPassNumberTasks = activity as OnPassData
        view.findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener(this)

        itemMenuFilterClick = arguments?.getString("filter").toString()
        this.arguments?.remove("filter")
        itemMenuMoreClick = arguments?.getString("more").toString()
        this.arguments?.remove("more")

        //ADAPTER AND FILTER
        val linearLayoutManager = LinearLayoutManager(view.context)

        taskAdapter = TaskAdapter(dataSet)
        if (itemMenuFilterClick == "filter_all") {
            taskAdapter = TaskAdapter(dataSet)
        } else if (itemMenuFilterClick == "filter_completed") {
            taskAdapter = TaskAdapter(dataSet.filter { it.checkBox })
        } else if (itemMenuFilterClick == "filter_active") {
            taskAdapter = TaskAdapter(dataSet.filter { !it.checkBox })
        } else if (itemMenuMoreClick == "more_refresh") {
            taskAdapter = TaskAdapter(dataSet)
        } else if (itemMenuMoreClick == "more_clear") {
            dataSet.removeAll { it.checkBox }
        }
        recyclerView.apply {
            //setItemViewCacheSize(2)
            //setRecycledViewPool(5)
            layoutManager = linearLayoutManager
            adapter = taskAdapter
        }

        //SHOW LIST OR LOGO NO HAVE TASK
        if (taskAdapter.itemCount > 0) {
            containerRecyclerView.visibility = View.VISIBLE
            containerNoHaveTask.visibility = View.GONE
        } else {
            containerRecyclerView.visibility = View.GONE
            containerNoHaveTask.visibility = View.VISIBLE
        }

        //INSERT
        title = arguments?.getString("title_key")
        body = arguments?.getString("body_key")
        this.arguments?.remove("title_key")
        this.arguments?.remove("body_key")
        if (title != "" && title != null) {
            (activity as MainActivity).insert(title.toString(), body.toString())
            taskAdapter.notifyDataSetChanged()
        }

        //UPDATE
        i = arguments?.getInt("update_i_key")
        titleUpdated = arguments?.getString("update_title_key")
        bodyUpdated = arguments?.getString("update_body_key")
        checkBoxUpdated = arguments?.getBoolean("update_checkbox_key")
        this.arguments?.remove("update_i_key")
        this.arguments?.remove("update_title_key")
        this.arguments?.remove("update_body_key")
        this.arguments?.remove("update_checkbox_key")
        if (titleUpdated != null && titleUpdated != "") {
            checkBoxUpdated?.let {
                (activity as MainActivity).update(
                    i!!, titleUpdated!!, bodyUpdated!!,
                    it
                )
            }
            taskAdapter.notifyDataSetChanged()
        }
    }

    override fun onPause() {
        super.onPause()
        var numCompleted = dataSet.count { it.checkBox }
        var numActive = dataSet.count { !it.checkBox }
        onPassNumberTasks.onPassNumberTasks(numCompleted.toFloat(), numActive.toFloat())
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.fabAdd -> {
                navController!!.navigate(R.id.action_taskListFragment_to_insertTaskFragment)
            }
        }
    }
}