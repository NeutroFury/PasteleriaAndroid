package com.jonesys.proyectopasteleriaandroid.remote

import com.jonesys.proyectopasteleriaandroid.model.Carrito
import com.jonesys.proyectopasteleriaandroid.model.CarritoItem
import retrofit2.Response
import retrofit2.http.*

interface ApiServiceCarrito {

    // Endpoints de Carrito
    @POST("api/carritos/save")  // ← Cambiar carrito a carritos
    suspend fun saveCarrito(@Body carrito: Carrito): Response<Carrito>

    @GET("api/carritos/all")  // ← Cambiar carrito a carritos
    suspend fun getAllCarritos(): Response<List<Carrito>>

    @DELETE("api/carritos/delete/{id}")  // ← Cambiar carrito a carritos
    suspend fun deleteCarrito(@Path("id") id: Long): Response<Unit>

    @GET("api/carritos/find/{id}")  // ← Cambiar carrito a carritos
    suspend fun findCarrito(@Path("id") id: Long): Response<Carrito>

    @GET("api/carritos/usuario/{usuarioId}")  // ← Cambiar carrito a carritos
    suspend fun getCarritoByUsuarioId(@Path("usuarioId") usuarioId: Long): Response<Carrito>

    @PUT("api/carritos/update/{id}")  // ← Cambiar carrito a carritos
    suspend fun updateCarrito(@Path("id") id: Long, @Body carrito: Carrito): Response<Carrito>

    // Endpoints de CarritoItem
    @POST("api/carrito-items/save")  // ← Cambiar carrito-item a carrito-items
    suspend fun saveCarritoItem(@Body item: CarritoItem): Response<CarritoItem>

    @GET("api/carrito-items/all")  // ← Cambiar carrito-item a carrito-items
    suspend fun getAllCarritoItems(): Response<List<CarritoItem>>

    @GET("api/carrito-items/carrito/{carritoId}")  // ← Cambiar carrito-item a carrito-items
    suspend fun getItemsByCarritoId(@Path("carritoId") carritoId: Long): Response<List<CarritoItem>>

    @DELETE("api/carrito-items/delete/{id}")  // ← Cambiar carrito-item a carrito-items
    suspend fun deleteCarritoItem(@Path("id") id: Long): Response<Unit>

    @DELETE("api/carrito-items/carrito/{carritoId}/clear")  // ← Cambiar carrito-item a carrito-items
    suspend fun clearCarrito(@Path("carritoId") carritoId: Long): Response<Unit>

    @GET("api/carrito-items/find/{id}")  // ← Cambiar carrito-item a carrito-items
    suspend fun findCarritoItem(@Path("id") id: Long): Response<CarritoItem>

    @PUT("api/carrito-items/update/{id}")  // ← Cambiar carrito-item a carrito-items
    suspend fun updateCarritoItem(@Path("id") id: Long, @Body item: CarritoItem): Response<CarritoItem>
}