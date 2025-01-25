package com.victorsdd.androidmaster.my_app.superHero

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.squareup.picasso.Picasso
import com.victorsdd.androidmaster.databinding.ActivityDetailSuperHeroBinding
import com.victorsdd.androidmaster.my_app.superHero.SuperHeroActivity.Companion.ID
import com.victorsdd.androidmaster.my_app.superHero.api.ApiService
import com.victorsdd.androidmaster.my_app.superHero.models.PowerStatsResponse
import com.victorsdd.androidmaster.my_app.superHero.models.SuperHeroDetailResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DetailSuperHeroActivity : AppCompatActivity() {


    private lateinit var binding: ActivityDetailSuperHeroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getStringExtra(ID).orEmpty()
        Log.i("DetailSuperHeroActivity", "id: $id")
        getSuperHeroById(id)
    }

    private fun getSuperHeroById(id: String){
        binding.circularProgressIndicator.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
           val superheroDetail =  retrofitInstance().create(ApiService::class.java).getSuperHero(id)
            if(superheroDetail.body() != null){
                runOnUiThread {
                    createUI(superheroDetail.body()!!)
                    binding.circularProgressIndicator.isVisible = false
                }
            }
        }
    }

    private fun pixelToDp(px: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).toInt()
    }


    private fun createUI(body: SuperHeroDetailResponse) {
        Picasso.get().load(body.image.url).into(binding.ivSuperHero)
        binding.tvSuperheroName.text = body.name
        preparestats(body.powerstats)
        binding.tvSuperheroRealName.text = body.biography.fullName
        binding.tvPublisher.text = body.biography.publisher
    }

    private fun updateHeight(view: View, newHeight: Int): View {
        val params = view.layoutParams
        params.height = pixelToDp(newHeight.toFloat())
        view.layoutParams = params
        return view
    }

    private fun preparestats(powerstats : PowerStatsResponse){
        updateHeight(binding.viewCombat, powerstats.combat.toInt())
        updateHeight(binding.viewDurability, powerstats.durability.toInt())
        updateHeight(binding.viewSpeed, powerstats.speed.toInt())
        updateHeight(binding.viewStrength, powerstats.strength.toInt())
        updateHeight(binding.viewIntelligence, powerstats.intelligence.toInt())
        updateHeight(binding.viewPower, powerstats.power.toInt())
    }

    private fun retrofitInstance() : Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}