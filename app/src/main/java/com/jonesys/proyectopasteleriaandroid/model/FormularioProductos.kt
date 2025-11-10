package com.jonesys.proyectopasteleriaandroid.model

data class Product(
    val id: Int,
    val imagenRes: Int,
    val titulo: String,
    val descripcion: String,
    val precioBase: Int,              // Precio antes de descuento
    val descuentoPercent: Int? = null // null si no hay descuento
)

data class ProductUi(
    val id: Int,
    val imagenRes: Int,
    val titulo: String,
    val descripcion: String,
    val precioBase: Int,
    val descuentoPercent: Int?,
    val precioFinal: Int,
    val tieneDescuento: Boolean,
    val precioBaseFormateado: String,
    val precioFinalFormateado: String
)

fun Int.formatearPesos(): String {
    // Formato chileno simple con puntos para miles
    return "$" + "%,d".format(this).replace(',', '.')
}