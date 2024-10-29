package com.victorsdd.androidmaster.my_app
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.victorsdd.androidmaster.R
import com.victorsdd.androidmaster.my_app.ImcApp.ImcMainActivity
import com.victorsdd.androidmaster.my_app.firstApp.FirstAppActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val firstProject = findViewById<Button>(R.id.firstProject)
        val imgApp = findViewById<Button>(R.id.imc_app)

        firstProject.setOnClickListener{
            navigateTo("firstProject")
        }

        imgApp.setOnClickListener{
            navigateTo("imgApp")
        }


    }


    private fun navigateTo(project:String){
        when(project){
            "firstProject" -> {
                val intent = Intent(this, FirstAppActivity::class.java)
                startActivity(intent)
            }
            "imgApp" -> {
                val intent = Intent(this, ImcMainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}