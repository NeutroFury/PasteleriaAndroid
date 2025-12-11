package com.jonesys.proyectopasteleriaandroid.repository

import com.jonesys.proyectopasteleriaandroid.model.Usuario
import com.jonesys.proyectopasteleriaandroid.remote.ApiServiceUsuarios
import com.jonesys.proyectopasteleriaandroid.remote.RetrofitInstanceUsuario
import retrofit2.Response

class UsuarioRepositoryTest(private val apiServiceUsuario: ApiServiceUsuarios) {



    suspend fun recuperarUsuarios(): List<Usuario> {
        return try {
            val response = apiServiceUsuario.getUsuario()
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun grabarUsuario(u: Usuario): Boolean {
        return try {
            val response = apiServiceUsuario.saveUsuario(u)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    suspend fun eliminarUsuario(id: Long): Boolean {
        return try {
            val response = apiServiceUsuario.deleteUsuario(id)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    suspend fun updateUsuario(usuario: Usuario): Usuario? {
        return try {
            val id = usuario.id ?: return null
            val response = apiServiceUsuario.updateUsuario(id = id, usuario = usuario)
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            null
        }
    }

    suspend fun login(email: String, password: String): Response<Usuario>? {
        val response = RetrofitInstanceUsuario.api.getUsuario()

        if (response.isSuccessful) {
            val usuarios = response.body()
            val usuario = usuarios?.find {
                it.nombreUsuario == email && it.contrasena == password
            }

            return if (usuario != null) {
                Response.success(usuario)
            } else {
                null
            }
        }
        return null
    }

    suspend fun register(usuario: Usuario): Response<Usuario> {
        return RetrofitInstanceUsuario.api.saveUsuario(usuario)
    }

}