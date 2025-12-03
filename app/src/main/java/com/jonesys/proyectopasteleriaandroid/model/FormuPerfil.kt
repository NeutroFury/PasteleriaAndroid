package com.jonesys.proyectopasteleriaandroid.model

data class FormuPerfil(
    val nombre: String = "",
    val email: String = "",
    val telefono: String = "",
    val password: String = "",
    val error: PerfilErrores = PerfilErrores(),
)

data class PerfilErrores(
    val nombre: String? = null,
    val email: String? = null,
    val telefono: String? = null,
    val password: String? = null
)
