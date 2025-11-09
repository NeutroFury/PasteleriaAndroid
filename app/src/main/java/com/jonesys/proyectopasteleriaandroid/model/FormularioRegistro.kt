package com.jonesys.proyectopasteleriaandroid.model
data class FormularioRegistro(
    val nombre: String = "",
    val email: String = "",
    val password: String = "",
    val confirmarPassword: String = "",
    val error: UsuarioErrores = UsuarioErrores(),
    val aceptarTerminos: Boolean
)


data class UsuarioErrores(
    val nombre: String? = null,
    val email: String? = null,
    val password: String? = null,
    val confirmarPassword: String? = null,
    val aceptaTerminos: String? = null
)

