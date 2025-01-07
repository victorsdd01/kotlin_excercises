package com.victorsdd.androidmaster.my_app.todoApp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.victorsdd.androidmaster.R
import com.victorsdd.androidmaster.my_app.todoApp.viewHolders.CategoriesViewHolder
import com.victorsdd.androidmaster.my_app.todoApp.models.TaskCategory

class CategoriesAdapter(private val categories: List<TaskCategory>, private val onSelectedCategory: (Int) -> Unit) : RecyclerView.Adapter<CategoriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoriesViewHolder(view)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.render(categories[position], onSelectedCategory)
    }
}