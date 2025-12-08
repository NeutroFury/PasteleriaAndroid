package com.jonesys.proyectopasteleriaandroid.remote

import com.jonesys.proyectopasteleriaandroid.model.Usuario
import retrofit2.Response
import retrofit2.http.*

interface ApiServiceUsuarios {

    @GET("api/users/all")
    suspend fun getUsuario(): Response<List<Usuario>>

    @POST("api/users/save")
    suspend fun saveUsuario(@Body usuario: Usuario): Response<Usuario>

    @DELETE("api/users/delete/{id}")
    suspend fun deleteUsuario(@Path("id") id: Long): Response<Unit>

    @GET("api/users/find/{id}")
    suspend fun findUsuario(@Path("id") id: Long): Response<Usuario>

    @PUT("api/users/update/{id}")
    suspend fun updateUsuario(@Path("id") id: Long, @Body usuario: Usuario): Response<Usuario>
}