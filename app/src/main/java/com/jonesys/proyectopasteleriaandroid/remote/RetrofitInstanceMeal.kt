package com.jonesys.proyectopasteleriaandroid.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceMeal {

    // La URL base de la API externa
    private const val BASE_URL = "https://www.themealdb.com/"

    val api: ApiServiceMeal by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceMeal::class.java)
    }
}