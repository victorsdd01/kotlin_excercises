package com.victorsdd.androidmaster.my_app
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.victorsdd.androidmaster.R
import com.victorsdd.androidmaster.databinding.ActivityMenuBinding
import com.victorsdd.androidmaster.my_app.ImcApp.ImcMainActivity
import com.victorsdd.androidmaster.my_app.firstApp.FirstAppActivity
import com.victorsdd.androidmaster.my_app.settings.SettingsActivity
import com.victorsdd.androidmaster.my_app.superHero.SuperHeroActivity
import com.victorsdd.androidmaster.my_app.todoApp.TodoMainActivity

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listeners()
    }

    private fun listeners(){
        binding.firstProject.setOnClickListener {
            navigateTo(FirstAppActivity::class.java)
        }

        binding.imcApp.setOnClickListener {
            navigateTo(ImcMainActivity::class.java)
        }

        binding.todoApp.setOnClickListener {
            navigateTo(TodoMainActivity::class.java)
        }

        binding.superHeroApp.setOnClickListener {
            navigateTo(SuperHeroActivity::class.java)
        }

        binding.settings.setOnClickListener {
            navigateTo(SettingsActivity::class.java)
        }
    }

    private fun navigateTo(activityClass: Class<*>){
       val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}