package com.example.project_1_home.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.project_1_home.R
import com.example.project_1_home.model.TaskModel

class TaskAdapter(private val dataSet: List<TaskModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class TaskViewHolder(viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {
        var check: CheckBox
        var title: TextView
        var body: TextView

        init {
            check = viewHolder.findViewById(R.id.checkbox)
            title = viewHolder.findViewById(R.id.txtTitle)
            body = viewHolder.findViewById(R.id.txtBody)
            viewHolder.setOnClickListener { edit(it) }
        }

        fun edit(v: View) {
            val position = absoluteAdapterPosition
            var navController = v.findNavController()
            navController.navigate(R.id.editTaskFragment, Bundle().apply {
                putInt("single_i_key", position)
                putString("single_title_key", title.text.toString())
                putString("single_body_key", body.text.toString())
                if (check.isChecked) {
                    putBoolean("single_checkbox_key", true)
                } else {
                    putBoolean("single_checkbox_key", false)
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val taskViewHolderTemplate =
            LayoutInflater.from(parent.context).inflate(R.layout.view_task_list, parent, false)
        return TaskViewHolder(taskViewHolderTemplate)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TaskViewHolder) {
            holder.title.text = dataSet[position].title
            holder.check.isChecked = dataSet[position].checkBox
            holder.body.text = dataSet[position].body
        }
    }
}