package com.example.project_1_home.adapter

import android.graphics.Paint
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

class TaskAdapter(private var dataSet: List<TaskModel>) :
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
            check.setOnClickListener() { paint(it) }
        }

        private fun paint(v: View) {
            val position = absoluteAdapterPosition
            if (check.isChecked) {
                dataSet[position].checkBox = true
                title.apply {
                    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                }
            } else if (!check.isChecked) {
                dataSet[position].checkBox = false
                title.apply {
                    paintFlags = 0
                }
            }
        }

        private fun edit(v: View) {
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
            if (dataSet[position].checkBox) {
                holder.title.apply {
                    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                }.text = dataSet[position].title
            } else if (!dataSet[position].checkBox) {
                holder.title.apply {
                    paintFlags = 0
                }.text = dataSet[position].title
            }
            holder.check.isChecked = dataSet[position].checkBox
            holder.body.text = dataSet[position].body
        }
    }

    fun removeItem(position: Int) {
        dataSet
    }
}
