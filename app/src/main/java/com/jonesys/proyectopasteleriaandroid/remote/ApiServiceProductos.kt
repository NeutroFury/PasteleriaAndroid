package com.jonesys.proyectopasteleriaandroid.remote

import com.jonesys.proyectopasteleriaandroid.model.Producto
import retrofit2.Response
import retrofit2.http.*

interface ApiServiceProductos {

    @GET("api/productos/all")
    suspend fun getProducto(): Response<List<Producto>>

    @POST("api/productos/save")
    suspend fun saveProducto(@Body persona: Producto): Response<Producto>

    @DELETE("api/productos/delete/{id}")
    suspend fun deleteProducto(@Path("id") id: Int): Response<Unit>

    @GET("api/productos/find/{id}")
    suspend fun findProducto(@Path("id") id:Int): Response<Producto>

    @PUT("api/productos/update/{id}")
    suspend fun updateProducto(@Path("id") id: Int,@Body producto: Producto): Response<Producto>
}
