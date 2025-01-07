package com.victorsdd.androidmaster.my_app
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.victorsdd.androidmaster.R
import com.victorsdd.androidmaster.my_app.ImcApp.ImcMainActivity
import com.victorsdd.androidmaster.my_app.firstApp.FirstAppActivity
import com.victorsdd.androidmaster.my_app.todoApp.TodoMainActivity


enum class ProjectType {
    FIRST_PROJECT,
    SECOND_PROJECT,
    THIRD_PROJECT,
}

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val firstProject = findViewById<Button>(R.id.firstProject)

        val imgApp = findViewById<Button>(R.id.imc_app)

        val todoApp = findViewById<Button>(R.id.todo_app)


        firstProject.setOnClickListener {
            navigateTo(ProjectType.FIRST_PROJECT)
        }

        imgApp.setOnClickListener {
            navigateTo(ProjectType.SECOND_PROJECT)
        }

        todoApp.setOnClickListener {
            navigateTo(ProjectType.THIRD_PROJECT)
        }


    }


    private fun navigateTo(project:ProjectType){
        when(project){
            ProjectType.FIRST_PROJECT -> {
                val intent = Intent(this, FirstAppActivity::class.java)
                startActivity(intent)
            }
            ProjectType.SECOND_PROJECT -> {
                val intent = Intent(this, ImcMainActivity::class.java)
                startActivity(intent)
            }
            ProjectType.THIRD_PROJECT -> {
                val intent = Intent(this, TodoMainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}