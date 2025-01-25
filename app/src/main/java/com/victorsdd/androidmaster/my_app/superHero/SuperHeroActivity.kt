package com.victorsdd.androidmaster.my_app.superHero

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.victorsdd.androidmaster.databinding.ActivitySuperHeroBinding
import com.victorsdd.androidmaster.my_app.superHero.adapters.SuperHeroAdapter
import com.victorsdd.androidmaster.my_app.superHero.api.ApiService
import com.victorsdd.androidmaster.my_app.superHero.models.SuperHero
import com.victorsdd.androidmaster.my_app.superHero.models.SuperHeroResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



enum class Route {
    HOME,
    DETAIL
}

class SuperHeroActivity : AppCompatActivity() {

    private lateinit var  binding : ActivitySuperHeroBinding
    private lateinit var retrofit : Retrofit
    private lateinit var adapter : SuperHeroAdapter


    companion object {
        const val ID = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = retrofitInstance()
        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                onSearch(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean = false

        })

        adapter = SuperHeroAdapter{
            navigate(Route.DETAIL, it.id)
        }
        binding.rvSuperHero.setHasFixedSize(true)
        binding.rvSuperHero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperHero.adapter = adapter
    }

    private fun onSearch(query: String){
        binding.circularProgressIndicator.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<SuperHeroResponse> = retrofit.create(ApiService::class.java).getSuperHeros(query)
            if (myResponse.isSuccessful) {
                val response : SuperHeroResponse? = myResponse.body()
                if (response != null) {
                    runOnUiThread {
                        adapter.updateList(response.results)
                        binding.circularProgressIndicator.isVisible = false
                    }

                }
            }
        }
    }

    private fun retrofitInstance() : Retrofit {
        return Retrofit
                .Builder()
                .baseUrl("https://superheroapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun navigate (route: Route, argument: String) {
        when (route) {
            Route.HOME -> {
                val intent = Intent(this, SuperHero::class.java)
                startActivity(intent)
            }
            Route.DETAIL -> {
                val intent = Intent(this, DetailSuperHeroActivity::class.java).putExtra(ID, argument)
                startActivity(intent)
            }
        }
    }
}