package com.example.project_1_home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_1_home.R
import com.example.project_1_home.model.TaskModel

typealias OnItemClicked = (task: TaskModel) -> Unit

class TaskAdapter(private val dataSet: List<TaskModel>, val itemClickListener: OnItemClicked) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class TaskViewHolder(viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {
        val check: CheckBox
        val title: TextView

        init {
            check = viewHolder.findViewById(R.id.checkbox)
            title = viewHolder.findViewById(R.id.txtTitle)
            viewHolder.setOnClickListener {
                itemClickListener(dataSet[absoluteAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val taskViewHolderTemplate =
            LayoutInflater.from(parent.context).inflate(R.layout.view_task_list, parent, false)
        return TaskViewHolder(taskViewHolderTemplate)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (holder is TaskViewHolder) {
            holder.title.text = dataSet[position].title
            holder.check.isChecked = dataSet[position].checkBox
        }
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}