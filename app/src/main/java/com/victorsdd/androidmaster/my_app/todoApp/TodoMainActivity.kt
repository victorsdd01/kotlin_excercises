package com.victorsdd.androidmaster.my_app.todoApp

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.victorsdd.androidmaster.R
import com.victorsdd.androidmaster.my_app.todoApp.adapters.CategoriesAdapter
import com.victorsdd.androidmaster.my_app.todoApp.adapters.TaskAdapter
import com.victorsdd.androidmaster.my_app.todoApp.models.Task
import com.victorsdd.androidmaster.my_app.todoApp.models.TaskCategory

class TodoMainActivity : AppCompatActivity() {


    private val categories = listOf(
        TaskCategory.Other,
        TaskCategory.Personal,
        TaskCategory.Business,
    )

    private val tasks = mutableListOf(
        Task("PruebaBusiness", TaskCategory.Business),
        Task("PruebaPersonal", TaskCategory.Personal),
        Task("PruebaOther", TaskCategory.Other),
    )




    private lateinit var rvCategories : RecyclerView
    private lateinit var categoriesAdapter : CategoriesAdapter


    private lateinit var rvTasks : RecyclerView

    private lateinit var  tasksAdapter : TaskAdapter

    private lateinit var fabAddTask : FloatingActionButton

    private lateinit var addTaskBtn : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_main)
        initComponent()
        initListeners()
        initUI()
    }
    private fun initComponent() {
        rvCategories = findViewById(R.id.rv_category)
        rvTasks = findViewById(R.id.rv_tasks)
        fabAddTask = findViewById(R.id.fab_add_task)
    }

    private fun initListeners(){
        fabAddTask.setOnClickListener {
            showDialog()
        }
    }

    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories){ onCategorySelected(it) }
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter


        tasksAdapter = TaskAdapter(tasks) {
            onItemSelected(it)
        }
        rvTasks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvTasks.adapter = tasksAdapter
    }

    private fun onCategorySelected(position : Int) {
        categories[position].isSelected = !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTasks()
    }

    private fun onItemSelected(position : Int) {
        tasks[position].isSelected = !tasks[position].isSelected
        updateTasks()
    }

    private fun showDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)
        val editTextTask = dialog.findViewById<EditText>(R.id.etTask)
        val radioGroup = dialog.findViewById<RadioGroup>(R.id.rg_category)
        addTaskBtn = dialog.findViewById(R.id.btn_addTask)

        addTaskBtn.setOnClickListener {
            if(editTextTask.text.toString().isNotEmpty()){
                val currentTask = radioGroup.checkedRadioButtonId
                val selectedRadioButton = radioGroup.findViewById<Button>(currentTask)
                val currentCategory : TaskCategory = when(selectedRadioButton.text){
                    getString(R.string.td_business) -> TaskCategory.Business
                    getString(R.string.td_personal) -> TaskCategory.Personal
                    else -> TaskCategory.Other
                }

                tasks.add(Task(editTextTask.text.toString(), currentCategory))
                updateTasks()
                dialog.hide()
            }
        }
        dialog.show()
    }


    private fun updateTasks(){
        val selectedCategories : List<TaskCategory> = categories.filter { it.isSelected }
        val newTasks = tasks.filter { selectedCategories.contains(it.category) }
        tasksAdapter.tasks = newTasks
        tasksAdapter.notifyDataSetChanged()
    }
}