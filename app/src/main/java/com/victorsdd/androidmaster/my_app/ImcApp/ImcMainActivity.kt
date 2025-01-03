package com.victorsdd.androidmaster.my_app.ImcApp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MotionEvent
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.victorsdd.androidmaster.R

enum class UnitMeasurement(val unit: String) {
    M("M"), CM("CM"), FT("FT"), IN("IN")
}

enum class AdjustmentMode { INCREASE, DECREASE }

class ImcMainActivity : AppCompatActivity() {

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var height: TextView
    private lateinit var slider: RangeSlider
    private lateinit var unit: TextView
    private lateinit var menu: ImageButton
    private lateinit var increaseWeightButton: FloatingActionButton
    private lateinit var decreaseWeightButton: FloatingActionButton
    private lateinit var weight: TextView
    private lateinit var weightUnitM: TextView
    private lateinit var tvAge: TextView
    private lateinit var increaseAgeButton: FloatingActionButton
    private lateinit var decreaseAgeButton: FloatingActionButton
    private lateinit var calculateBMI: Button

    private var unitMeasurement: UnitMeasurement = UnitMeasurement.M
    private var isMaleSelected = true
    private var currentWeight = 0
    private var currentAge = 0
    private var currentHeight = 120

    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_main)
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.view_male)
        viewFemale = findViewById(R.id.view_female)
        menu = findViewById(R.id.btn_popup_menu)
        unit = findViewById(R.id.tv_unitMeasurement)
        height = findViewById(R.id.tv_height)
        slider = findViewById(R.id.slider)
        increaseWeightButton = findViewById(R.id.btn_increase)
        decreaseWeightButton = findViewById(R.id.btn_decrease)
        weight = findViewById(R.id.textview_weight)
        weightUnitM = findViewById(R.id.weight_unitM)
        tvAge = findViewById(R.id.tv_age)
        increaseAgeButton = findViewById(R.id.btn_increase_age)
        decreaseAgeButton = findViewById(R.id.btn_decrease_age)
        calculateBMI = findViewById(R.id.btn_calculate)
    }

    private val handler = Handler(Looper.getMainLooper())
    private val incrementRunnable = object : Runnable {
        override fun run() {
            currentWeight = adjustValue(currentWeight, AdjustmentMode.INCREASE, 1)
            weight.text = currentWeight.toString()
            handler.postDelayed(this, 100)
        }
    }

    private val decrementRunnable = object : Runnable {
        override fun run() {
            currentWeight = adjustValue(currentWeight, AdjustmentMode.DECREASE, 1)
            weight.text = currentWeight.toString()
            handler.postDelayed(this, 50)
        }
    }

    private fun initListeners() {
        viewMale.setOnClickListener { toggleGender(true) }
        viewFemale.setOnClickListener { toggleGender(false) }
        menu.setOnClickListener { showMenu() }
        slider.addOnChangeListener { _, value, _ -> updateHeight(value) }
        increaseAgeButton.setOnClickListener { adjustAge(AdjustmentMode.INCREASE) }
        decreaseAgeButton.setOnClickListener { adjustAge(AdjustmentMode.DECREASE) }
        calculateBMI.setOnClickListener { calculateBMI() }


        increaseWeightButton.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    handler.post(incrementRunnable)
                    true
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    handler.removeCallbacks(incrementRunnable)
                    true
                }
                else -> false
            }
        }

        decreaseWeightButton.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    handler.post(decrementRunnable)
                    true
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    handler.removeCallbacks(decrementRunnable)
                    true
                }
                else -> false
            }
        }
    }

    private fun toggleGender(isMale: Boolean) {
        isMaleSelected = isMale
        updateGenderColors()
    }

    private fun updateGenderColors() {
        viewMale.setCardBackgroundColor(getGenderColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getGenderColor(!isMaleSelected))
    }

    private fun getGenderColor(isSelected: Boolean): Int {
        val colorRes = if (isSelected) R.color.background_component_selected else R.color.background_component
        return ContextCompat.getColor(this, colorRes)
    }

    private fun updateHeight(value: Float) {
        currentHeight = when (unitMeasurement) {
            UnitMeasurement.M -> value.toInt()
            UnitMeasurement.CM -> value.toInt()
            UnitMeasurement.FT -> (value / 30.48).toInt()
            UnitMeasurement.IN -> (value / 2.54).toInt()
        }
        height.text = currentHeight.toString()
    }

    private fun adjustWeight(mode: AdjustmentMode) {
        currentWeight = adjustValue(currentWeight, mode, 1)
        weight.text = currentWeight.toString()
    }

    private fun adjustAge(mode: AdjustmentMode) {
        currentAge = adjustValue(currentAge, mode, 5)
        tvAge.text = currentAge.toString()
    }

    private fun adjustValue(value: Int, mode: AdjustmentMode, min: Int): Int {
        return when (mode) {
            AdjustmentMode.INCREASE -> value + 1
            AdjustmentMode.DECREASE -> if (value > min) value - 1 else value
        }
    }

    private fun calculateBMI() {
        val heightInMeters = currentHeight / 100.0
        val bmi = currentWeight / (heightInMeters * heightInMeters)
        navigateToResult(bmi)
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultImcActivity::class.java).apply {
            putExtra(IMC_KEY, result)
        }
        startActivity(intent)
    }

    private fun showMenu() {
        val popupMenu = PopupMenu(this, menu).apply {
            inflate(R.menu.popup_menu)
            UnitMeasurement.values().forEach { unit ->
                menu.add(Menu.NONE, unit.ordinal, Menu.NONE, unit.unit)
            }
            setOnMenuItemClickListener { item ->
                unitMeasurement = UnitMeasurement.values()[item.itemId]
                unit.text = unitMeasurement.unit
                true
            }
        }
        popupMenu.show()
    }
}
