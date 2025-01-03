package com.victorsdd.androidmaster.my_app.ImcApp

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.victorsdd.androidmaster.R
import com.victorsdd.androidmaster.my_app.ImcApp.ImcMainActivity.Companion.IMC_KEY

class ResultImcActivity : AppCompatActivity() {

    private lateinit var bmiTextView: TextView
    private lateinit var resultTextView: TextView
    private lateinit var backButton: ImageView
    private lateinit var recalculateBtn : Button
    private lateinit var resultType : TextView
    private var result: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result_activity)
        result = intent.getDoubleExtra(IMC_KEY, 0.0)
        initViews()
        setListeners()
        displayResult()
        setResultType()
    }

    private fun initViews() {

        backButton = findViewById(R.id.back_button)
        bmiTextView = findViewById(R.id.result)
        resultTextView = findViewById(R.id.result_text)
        resultType = findViewById(R.id.result_type)
        recalculateBtn = findViewById(R.id.btn_recalculate)
    }

    private fun setListeners() {
        backButton.setOnClickListener { navigateBack() }

        recalculateBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setResultType(){

        when (result) {
            in 0.0..18.5 -> {
                resultType.text = getText(R.string.eat_more)
                resultType.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
                resultTextView.text = getString(R.string.low_weight)
            }
            in 18.5..24.9 -> {
                resultType.text = getText(R.string.normal)
                resultType.setTextColor(ContextCompat.getColor(this, R.color.peso_normal))
                resultTextView.text = getText(R.string.healthy)
            }
            in 25.0..29.9 -> {
                resultType.text = getText(R.string.high_weight)
                resultType.setTextColor(ContextCompat.getColor(this, R.color.peso_sobrepeso))
                resultTextView.text = getText(R.string.over_weight)
            }
        }
    }

    private fun displayResult() {
        bmiTextView.text = String.format("%.2f", result)
    }

    private fun navigateBack() {
        Intent(this, ImcMainActivity::class.java).also { startActivity(it) }
    }
}
