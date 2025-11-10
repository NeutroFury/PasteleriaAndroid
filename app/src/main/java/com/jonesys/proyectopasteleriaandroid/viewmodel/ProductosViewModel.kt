package com.jonesys.proyectopasteleriaandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonesys.proyectopasteleriaandroid.R
import com.jonesys.proyectopasteleriaandroid.model.Product
import com.jonesys.proyectopasteleriaandroid.model.ProductUi
import com.jonesys.proyectopasteleriaandroid.model.formatearPesos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class ProductosViewModel : ViewModel() {

    private val _productosBase = MutableStateFlow(
        listOf(
            Product(1, R.drawable.pastel1, "Torta Cuadrada de Chocolate",
                "Deliciosa torta de chocolate con capas de ganache y un toque de avellanas. Personalizable con mensajes especiales",
                precioBase = 45000, descuentoPercent = 20),
            Product(2, R.drawable.pastel4, "Torta Circular de Manjar",
                "Torta tradicional chilena con manjar y nueces, un deleite para los amantes de los sabores dulces y clásicos.",
                precioBase = 42000, descuentoPercent = 15),
            Product(3, R.drawable.pastel5, "Mousse de Chocolate",
                "Postre individual cremoso y suave, hecho con chocolate de alta calidad, ideal para los amantes del chocolate.",
                precioBase = 5000, descuentoPercent = 10),
            Product(4, R.drawable.pastel10, "Brownie Sin Gluten",
                "Rico y denso, este brownie es perfecto para quienes necesitan evitar el gluten sin sacrificar el sabor.",
                precioBase = 4000, descuentoPercent = 12),
            Product(5, R.drawable.pastel14, "Torta Especial de Cumpleaños",
                "Diseñada especialmente para celebraciones, personalizable con decoraciones y mensajes únicos.",
                precioBase = 55000, descuentoPercent = 25),
            Product(6, R.drawable.pastel2, "Torta Cuadrada de Frutas",
                "Mezcla de frutas frescas y crema chantilly sobre bizcocho de vainilla.", precioBase = 50000),
            Product(7, R.drawable.pastel3, "Torta Circular de Vainilla",
                "Bizcocho de vainilla clásico relleno con crema pastelera y glaseado.", precioBase = 40000),
            Product(8, R.drawable.pastel6, "Tiramisú Clásico",
                "Postre italiano con capas de café, mascarpone y cacao.", precioBase = 5500),
            Product(9, R.drawable.pastel7, "Torta Sin Azúcar de Naranja",
                "Torta ligera endulzada naturalmente.", precioBase = 48000),
            Product(10, R.drawable.cheesecake, "Cheesecake Sin Azúcar",
                "Suave y cremoso para disfrutar sin culpa.", precioBase = 47000),
            Product(11, R.drawable.pastel8, "Empanada de Manzana",
                "Rellena de manzanas especiadas, ideal para merienda.", precioBase = 3000),
            Product(12, R.drawable.pastel9, "Tarta de Santiago",
                "Tradicional tarta española de almendras.", precioBase = 6000),
            Product(13, R.drawable.pastel11, "Pan Sin Gluten",
                "Suave y esponjoso, ideal para acompañar.", precioBase = 3500),
            Product(14, R.drawable.pastel12, "Torta Vegana de Chocolate",
                "Torta húmeda sin productos animales.", precioBase = 50000),
            Product(15, R.drawable.pastel13, "Galletas Veganas de Avena",
                "Crujientes y sabrosas para snack saludable.", precioBase = 4500),
            Product(16, R.drawable.pastel15, "Torta Especial de Boda",
                "Elegante y deliciosa para bodas.", precioBase = 60000)
        )
    )

    val productosUi: StateFlow<List<ProductUi>> = _productosBase
        .map { lista -> lista.map { toUi(it) } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = _productosBase.value.map { toUi(it) }
        )

    private fun aplicarDescuento(precioBase: Int, descuento: Int?): Int {
        if (descuento == null || descuento <= 0) return precioBase
        return precioBase - (precioBase * descuento / 100)
    }

    private fun toUi(p: Product): ProductUi {
        val final = aplicarDescuento(p.precioBase, p.descuentoPercent)
        return ProductUi(
            id = p.id,
            imagenRes = p.imagenRes,
            titulo = p.titulo,
            descripcion = p.descripcion,
            precioBase = p.precioBase,
            descuentoPercent = p.descuentoPercent,
            precioFinal = final,
            tieneDescuento = p.descuentoPercent != null && p.descuentoPercent > 0,
            precioBaseFormateado = p.precioBase.formatearPesos(),
            precioFinalFormateado = final.formatearPesos()
        )
    }
}
