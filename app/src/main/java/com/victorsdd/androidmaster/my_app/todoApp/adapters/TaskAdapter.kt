package com.victorsdd.androidmaster.my_app.todoApp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.victorsdd.androidmaster.R
import com.victorsdd.androidmaster.my_app.todoApp.models.Task
import com.victorsdd.androidmaster.my_app.todoApp.viewHolders.TaskViewHolder

class TaskAdapter(var tasks: List<Task>, private val onTaskSelected: (Int) -> Unit) : RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        holder.render(tasks[position])
        holder.itemView.setOnClickListener{
            onTaskSelected(position)
        }

    }
}