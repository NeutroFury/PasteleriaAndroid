package com.jonesys.proyectopasteleriaandroid.model

import com.google.gson.annotations.SerializedName

data class Usuario (
    val id: Long? = null,
    val nombreUsuario: String,
    val contrasena: String,
    val rol: String,
    val nombre: String
)