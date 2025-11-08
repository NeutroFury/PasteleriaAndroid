package com.jonesys.proyectopasteleriaandroid.ui.screen

data class FormularioLogin(
    val email: String = "",
    val password: String = "",
    val error: String? = null,
    val isLogin: Boolean = false,
)