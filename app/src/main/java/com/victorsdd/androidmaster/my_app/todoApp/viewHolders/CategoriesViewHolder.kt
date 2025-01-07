package com.victorsdd.androidmaster.my_app.todoApp.viewHolders

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.victorsdd.androidmaster.R
import com.victorsdd.androidmaster.my_app.todoApp.models.TaskCategory

class CategoriesViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    private val textCategoryName : TextView = view.findViewById(R.id.tvCategoryName)
    private val divider : View = view.findViewById(R.id.divider)
    private val categoryCardView : CardView = view.findViewById(R.id.categoryCardView)
    fun render(taskCategory: TaskCategory, onSelectedCategory: (Int) -> Unit){

        val color = if(taskCategory.isSelected){
            R.color.todo_background_card
        }else{
            R.color.todo_background_disabled
        }

        categoryCardView.setCardBackgroundColor(ContextCompat.getColor(categoryCardView.context, color))
        itemView.setOnClickListener { onSelectedCategory(layoutPosition) }


        when (taskCategory){
            TaskCategory.Other -> {
                textCategoryName.text = "Other"
                divider.setBackgroundColor(ContextCompat.getColor(divider.context, R.color.todo_other_category))
            }
            TaskCategory.Personal -> {
                textCategoryName.text = "Personal"
                divider.setBackgroundColor(ContextCompat.getColor(divider.context, R.color.todo_personal_category))
            }
            TaskCategory.Business -> {
                textCategoryName.text = "Business"
                divider.setBackgroundColor(ContextCompat.getColor(divider.context, R.color.todo_business_category))
            }
        }
    }

}