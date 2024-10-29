package com.victorsdd.androidmaster.my_app.firstApp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.victorsdd.androidmaster.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val lbl =  findViewById<TextView>(R.id.lbl)

        val name : String = intent.extras?.getString("name").orEmpty()
        setName(name, lbl)



    }


    private fun setName(name : String, lbl : TextView){
        if (name.isNotEmpty()) {
            lbl.text = "Bienvenido $name"
        }
    }
}