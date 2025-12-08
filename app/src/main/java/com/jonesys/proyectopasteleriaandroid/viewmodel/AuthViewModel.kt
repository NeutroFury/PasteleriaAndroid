package com.jonesys.proyectopasteleriaandroid.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel : ViewModel() {
    private val _isLogged = MutableStateFlow(false)
    val isLogged = _isLogged.asStateFlow()

    private val _userName = MutableStateFlow<String?>(null)
    val userName = _userName.asStateFlow()

    private val _userNombre = MutableStateFlow<String?>(null)
    val userNombre = _userNombre.asStateFlow()

    fun setLoggedIn(logged: Boolean, name: String? = null, nombre: String? = null) {
        _isLogged.value = logged
        _userName.value = name
        _userNombre.value = nombre
    }

    fun logout() {
        _isLogged.value = false
        _userName.value = null
        _userNombre.value = null
    }
}