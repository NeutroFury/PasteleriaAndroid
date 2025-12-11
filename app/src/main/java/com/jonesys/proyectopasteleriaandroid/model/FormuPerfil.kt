package com.jonesys.proyectopasteleriaandroid.model

data class FormuPerfil(
    val nombre: String = "",
    val nombreUsuario: String = "",
    val contrasena: String = "",
    val error: PerfilErrores = PerfilErrores(),
)

data class PerfilErrores(
    val nombre: String? = null,
    val nombreUsuario: String? = null,
    val contrasena: String? = null
)
