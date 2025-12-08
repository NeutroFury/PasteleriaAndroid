package com.jonesys.proyectopasteleriaandroid.repository

import com.jonesys.proyectopasteleriaandroid.model.Producto
import com.jonesys.proyectopasteleriaandroid.remote.RetrofitInstanceProducto

class ProductoRepository {

    private val apiServiceProducto = RetrofitInstanceProducto.api

    suspend fun recuperarProductos(): List<Producto> {
        return try {
            val response = apiServiceProducto.getProducto()
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun grabarProducto(p: Producto): Boolean {
        return try {
            val response = apiServiceProducto.saveProducto(p)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    suspend fun eliminarProducto(id: Int): Boolean {
        return try {
            val response = apiServiceProducto.deleteProducto(id)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    suspend fun updateProducto(producto: Producto): Producto? {
        return try {
            val response = apiServiceProducto.updateProducto(id = producto.id.toInt(), producto = producto)
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            null
        }
    }
}