package com.victorsdd.androidmaster.my_app.superHero.api

import com.victorsdd.androidmaster.my_app.superHero.models.SuperHero
import com.victorsdd.androidmaster.my_app.superHero.models.SuperHeroDetailResponse
import com.victorsdd.androidmaster.my_app.superHero.models.SuperHeroResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/aaabcb9a3e0f111f7034a5c00753e6d2/search/{name}")
    suspend fun getSuperHeros(@Path("name") superHeroName: String) : Response<SuperHeroResponse>

    @GET("api/aaabcb9a3e0f111f7034a5c00753e6d2/{id}")
    suspend fun getSuperHero(@Path("id") superHeroId: String) : Response<SuperHeroDetailResponse>
}