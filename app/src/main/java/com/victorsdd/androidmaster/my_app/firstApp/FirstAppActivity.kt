package com.victorsdd.androidmaster.my_app.firstApp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.victorsdd.androidmaster.R


class FirstAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_app)

        val btnStart = findViewById<Button>(R.id.btn_start)
        val etName =  findViewById<AppCompatEditText>(R.id.etName)

        btnStart.setOnClickListener{
            if (etName.text.toString().isNotEmpty()) {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("name", etName.text.toString().trim())
                startActivity(intent)
            }
        }
    }
}