package com.jonesys.proyectopasteleriaandroid.repository

import com.jonesys.proyectopasteleriaandroid.model.Usuario
import com.jonesys.proyectopasteleriaandroid.remote.RetrofitInstanceUsuario

class UsuarioRepository {

    private val apiServiceUsuario = RetrofitInstanceUsuario.api

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
}