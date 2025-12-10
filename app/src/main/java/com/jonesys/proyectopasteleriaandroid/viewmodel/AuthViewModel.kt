package com.jonesys.proyectopasteleriaandroid.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {

    private val _isLogged = MutableStateFlow(false)
    val isLogged: StateFlow<Boolean> = _isLogged

    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName

    private val _userNombre = MutableStateFlow("")
    val userNombre: StateFlow<String> = _userNombre

    private val _userId = MutableStateFlow<Long?>(null)
    val userId: StateFlow<Long?> = _userId

    fun login(userId: Long, userName: String, userNombre: String) {
        _userId.value = userId
        _userName.value = userName
        _userNombre.value = userNombre
        _isLogged.value = true
    }

    fun logout() {
        _userId.value = null
        _userName.value = ""
        _userNombre.value = ""
        _isLogged.value = false
    }
}
