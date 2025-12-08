package com.jonesys.proyectopasteleriaandroid.model

data class Producto(
    val id: Long,
    val codigo: String,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val stock: Int,
    val imagenUrl: String,
    val estado: String,
    val descuento: Int,
    val categoriaId: Long
)
