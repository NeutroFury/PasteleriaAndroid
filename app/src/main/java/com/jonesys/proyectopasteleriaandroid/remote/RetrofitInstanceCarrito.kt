package com.jonesys.proyectopasteleriaandroid.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceCarrito {

    val api: ApiServiceCarrito by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceCarrito::class.java)
    }
}