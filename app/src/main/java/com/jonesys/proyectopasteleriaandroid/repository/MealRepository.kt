package com.jonesys.proyectopasteleriaandroid.repository

import com.jonesys.proyectopasteleriaandroid.remote.RetrofitInstanceMeal

class MealRepository {

    suspend fun getDessertMeals() =
        RetrofitInstanceMeal.api.getDessertMeals()

}