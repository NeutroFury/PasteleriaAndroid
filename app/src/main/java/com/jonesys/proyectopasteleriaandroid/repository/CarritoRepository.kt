package com.jonesys.proyectopasteleriaandroid.repository

import com.jonesys.proyectopasteleriaandroid.model.Carrito
import com.jonesys.proyectopasteleriaandroid.model.CarritoItem
import com.jonesys.proyectopasteleriaandroid.remote.RetrofitInstanceCarrito

class CarritoRepository {

    suspend fun getCarritoByUsuario(usuarioId: Long) =
        RetrofitInstanceCarrito.api.getCarritoByUsuarioId(usuarioId)

    suspend fun saveCarrito(carrito: Carrito) =
        RetrofitInstanceCarrito.api.saveCarrito(carrito)

    suspend fun getItemsByCarrito(carritoId: Long) =
        RetrofitInstanceCarrito.api.getItemsByCarritoId(carritoId)

    suspend fun saveCarritoItem(item: CarritoItem) =
        RetrofitInstanceCarrito.api.saveCarritoItem(item)

    suspend fun updateCarritoItem(id: Long, item: CarritoItem) =
        RetrofitInstanceCarrito.api.updateCarritoItem(id, item)

    suspend fun deleteCarritoItem(id: Long) =
        RetrofitInstanceCarrito.api.deleteCarritoItem(id)

    suspend fun clearCarrito(carritoId: Long) =
        RetrofitInstanceCarrito.api.clearCarrito(carritoId)
}