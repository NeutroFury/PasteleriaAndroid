package com.jonesys.proyectopasteleriaandroid.remote

import com.jonesys.proyectopasteleriaandroid.model.Carrito
import com.jonesys.proyectopasteleriaandroid.model.CarritoItem
import retrofit2.Response
import retrofit2.http.*

interface ApiServiceCarrito {

    @POST("api/carritos/save")
    suspend fun saveCarrito(@Body carrito: Carrito): Response<Carrito>

    @GET("api/carritos/all")
    suspend fun getAllCarritos(): Response<List<Carrito>>

    @DELETE("api/carritos/delete/{id}")
    suspend fun deleteCarrito(@Path("id") id: Long): Response<Unit>

    @GET("api/carritos/find/{id}")
    suspend fun findCarrito(@Path("id") id: Long): Response<Carrito>

    @GET("api/carritos/usuario/{usuarioId}")
    suspend fun getCarritoByUsuarioId(@Path("usuarioId") usuarioId: Long): Response<Carrito>

    @PUT("api/carritos/update/{id}")
    suspend fun updateCarrito(@Path("id") id: Long, @Body carrito: Carrito): Response<Carrito>

    @POST("api/carrito-items/save")
    suspend fun saveCarritoItem(@Body item: CarritoItem): Response<CarritoItem>

    @GET("api/carrito-items/all")
    suspend fun getAllCarritoItems(): Response<List<CarritoItem>>

    @GET("api/carrito-items/carrito/{carritoId}")
    suspend fun getItemsByCarritoId(@Path("carritoId") carritoId: Long): Response<List<CarritoItem>>

    @DELETE("api/carrito-items/delete/{id}")
    suspend fun deleteCarritoItem(@Path("id") id: Long): Response<Unit>

    @DELETE("api/carrito-items/carrito/{carritoId}/clear")
    suspend fun clearCarrito(@Path("carritoId") carritoId: Long): Response<Unit>

    @GET("api/carrito-items/find/{id}")
    suspend fun findCarritoItem(@Path("id") id: Long): Response<CarritoItem>

    @PUT("api/carrito-items/update/{id}")
    suspend fun updateCarritoItem(@Path("id") id: Long, @Body item: CarritoItem): Response<CarritoItem>
}