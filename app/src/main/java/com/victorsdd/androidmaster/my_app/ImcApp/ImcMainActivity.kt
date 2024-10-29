package com.victorsdd.androidmaster.my_app.ImcApp
import android.content.Intent
import android.os.Bundle
import android.view.Menu
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
import java.text.DecimalFormat


enum class UnitMeasurement(val unit: String) {
    M("M"),
    CM("CM"),
    FT("FT"),
    IN("IN"),
}


enum class WeightMode{
    INCREASE,
    DECREASE,
}

enum class AgeMode{
    INCREASE,
    DECREASE,
}


class ImcMainActivity : AppCompatActivity() {
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var height: TextView
    private lateinit var slider: RangeSlider
    private lateinit var unit: TextView
    private lateinit var menu: ImageButton

    private var unitMeasurement: UnitMeasurement = UnitMeasurement.M

    private var isMaleSelected: Boolean = true

    private lateinit var increaseWeightButton : FloatingActionButton

    private lateinit var decreaseWeightButton : FloatingActionButton

    private lateinit var weight : TextView

    private lateinit var weightUnitM : TextView

    private lateinit var tvAge: TextView

    private lateinit var increaseAgeButton : FloatingActionButton

    private lateinit var decreaseAgeButton : FloatingActionButton

    private var currentWeight: Int = 0
    private var currentAge: Int = 0
    private var currentHeight: Int = 0

    private lateinit var calculateBMI : Button


    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_main)
        initComponents()
        initListeners()

    }

    private fun initComponents(){
        viewMale = findViewById(R.id.view_male)
        viewFemale = findViewById(R.id.view_female)
        menu = findViewById(R.id.btn_popup_menu)
        unit = findViewById(R.id.tv_unitMeasurement)
        unit.text = unitMeasurement.unit

        height = findViewById(R.id.tv_height)
        slider = findViewById(R.id.slider)

        increaseWeightButton = findViewById(R.id.btn_increase)
        decreaseWeightButton = findViewById(R.id.btn_decrease)
        weight = findViewById(R.id.textview_weight)

        weightUnitM = findViewById(R.id.weight_unitM)

        tvAge = findViewById(R.id.tv_age)

        increaseAgeButton = findViewById(R.id.btn_increase_age)
        decreaseAgeButton = findViewById(R.id.btn_decrease_age)

        calculateBMI = findViewById(R.id.btn_calculatebmi)


    }

    private fun initListeners(){
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        menu.setOnClickListener {
            showMenu()
        }

        slider.addOnChangeListener { _, value, _ ->
            setHeight(value)
        }

        increaseWeightButton.setOnClickListener{
            increaseOrDecreaseWeight(WeightMode.INCREASE)
        }

        decreaseWeightButton.setOnClickListener{
            increaseOrDecreaseWeight(WeightMode.DECREASE)
        }

        increaseAgeButton.setOnClickListener{
            increaseOrDecreaseAge(AgeMode.INCREASE)
        }

        decreaseAgeButton.setOnClickListener{
            increaseOrDecreaseAge(AgeMode.DECREASE)
        }

        calculateBMI.setOnClickListener{
            navigateToResult(calculateBMI())
        }


    }

    private fun navigateToResult(result: Double){
        val intent = Intent(this, ResultImcActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun calculateBMI() : Double{
        val df = DecimalFormat("#.##")
        val imc = currentWeight / (currentHeight.toDouble() / 100  * currentHeight.toDouble() / 100)
        return df.format(imc).toDouble()
    }

    private fun increaseOrDecreaseWeight(mode: WeightMode) {
        when (mode) {
            WeightMode.INCREASE -> {
                weight.text = (weight.text.toString().toInt() + 1).toString()
                currentWeight = weight.text.toString().toInt()
            }
            WeightMode.DECREASE -> {
                if(weight.text.toString().toInt() > 1){
                    weight.text = (weight.text.toString().toInt() - 1).toString()
                    currentWeight = weight.text.toString().toInt()
                }

            }
            else -> println("Invalid option")
        }
    }

    private fun increaseOrDecreaseAge(mode: AgeMode){
        when(mode){
            AgeMode.INCREASE -> {
                tvAge.text = (tvAge.text.toString().toInt() + 1).toString()
                currentAge = tvAge.text.toString().toInt()
            }
            AgeMode.DECREASE -> {
                if(tvAge.text.toString().toInt() > 5) {
                    tvAge.text = (tvAge.text.toString().toInt() - 1).toString()
                    currentAge = tvAge.text.toString().toInt()
                }
            }
            else -> println("Invalid option")
        }
    }

    private fun setHeight(value: Float){
        when(this.unitMeasurement){
            UnitMeasurement.M -> {
                val formattedValue = String.format("%.0f", value).split("")
                var completedValue : String = "${formattedValue[1]}.${formattedValue[2]}${formattedValue[3]}"
                height.text = "$completedValue"
            }
            UnitMeasurement.CM -> {
                var formattedValue = String.format("%.0f", value)
                height.text = "$formattedValue"
            }
            UnitMeasurement.FT -> {
                val formattedValue = String.format("%.2f", value / 30.48)
                height.text = "$formattedValue"
            }
            UnitMeasurement.IN -> {
                val formattedValue = String.format("%.2f", value / 2.54)
                height.text = "$formattedValue"
            }
        }
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(!isMaleSelected))
    }

    private fun getBackgroundColor(isSelected: Boolean): Int {
        val colorReference = if (isSelected) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }

        return ContextCompat.getColor(this, colorReference)
    }

    private fun changeGender(){
        isMaleSelected = !isMaleSelected
    }

    private fun showMenu(){
        val popupMenu = PopupMenu(this, menu)
        popupMenu.inflate(R.menu.popup_menu)
        val menu = popupMenu.menu
        for (unit in UnitMeasurement.values()){
            menu.add(Menu.NONE, unit.ordinal, Menu.NONE, unit.unit)
        }
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                UnitMeasurement.M.ordinal -> {
                    setUnitMeasurement(UnitMeasurement.M)
                    true
                }
                UnitMeasurement.CM.ordinal -> {
                    setUnitMeasurement(UnitMeasurement.CM)
                    true
                }
                UnitMeasurement.FT.ordinal -> {
                    setUnitMeasurement(UnitMeasurement.FT)
                    true
                }
                UnitMeasurement.IN.ordinal -> {
                    setUnitMeasurement(UnitMeasurement.IN)
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }

    private fun setUnitMeasurement(unitMeasurement: UnitMeasurement){
        this.unitMeasurement = unitMeasurement
        unit.text = unitMeasurement.unit
    }

}