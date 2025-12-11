package com.jonesys.proyectopasteleriaandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonesys.proyectopasteleriaandroid.model.Meal
import com.jonesys.proyectopasteleriaandroid.repository.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MealViewModel : ViewModel() {

    private val repository = MealRepository()

    // StateFlow para exponer la lista de postres a la UI
    private val _dessertMeals = MutableStateFlow<List<Meal>>(emptyList())
    val dessertMeals: StateFlow<List<Meal>> = _dessertMeals

    // StateFlow para manejar el estado de carga (Loading, Success, Error)
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    init {
        fetchDessertMeals()
    }

    fun fetchDessertMeals() {
        viewModelScope.launch {
            _loading.value = true // Iniciar carga
            try {
                val response = repository.getDessertMeals()

                if (response.isSuccessful) {
                    // Mapear la lista de Meal (postres) si el body no es nulo
                    _dessertMeals.value = response.body()?.meals ?: emptyList()
                    android.util.Log.d("MealViewModel", "Postres cargados: ${_dessertMeals.value.size}")
                } else {
                    android.util.Log.e("MealViewModel", "Error al cargar postres: ${response.code()}")
                }
            } catch (e: Exception) {
                android.util.Log.e("MealViewModel", "Excepción de red: ${e.message}")
            } finally {
                _loading.value = false // Finalizar carga
            }
        }
    }
}