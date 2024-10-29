package com.victorsdd.androidmaster.my_app.ImcApp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.victorsdd.androidmaster.R
import com.victorsdd.androidmaster.my_app.ImcApp.ImcMainActivity.Companion.IMC_KEY

class ResultImcActivity : AppCompatActivity() {

    private lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result_activity)
        val result : Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initialize()
    }

    private fun initialize(){

        //result = findViewById(R.id.tvResult)
        //result.text = result.toString()


    }
}