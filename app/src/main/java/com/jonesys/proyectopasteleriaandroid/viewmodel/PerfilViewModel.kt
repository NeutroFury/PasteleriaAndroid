package com.jonesys.proyectopasteleriaandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonesys.proyectopasteleriaandroid.model.FormuPerfil
import com.jonesys.proyectopasteleriaandroid.model.PerfilErrores
import com.jonesys.proyectopasteleriaandroid.model.Usuario
import com.jonesys.proyectopasteleriaandroid.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PerfilViewModel: ViewModel() {
    private val _usuario = MutableStateFlow(FormuPerfil())
    val usuario: StateFlow<FormuPerfil> = _usuario

    private val repository = UsuarioRepository()

    private var usuarioId: Long? = null

    fun cargarUsuario(id: Long) {
        usuarioId = id
        viewModelScope.launch {
            val response = repository.recuperarUsuarios()
            val user = response.find { it.id == id }
            user?.let {
                _usuario.update { _ ->
                    FormuPerfil(
                        nombre = it.nombre,
                        nombreUsuario = it.nombreUsuario,
                        contrasena = it.contrasena
                    )
                }
            }
        }
    }

    fun onChangeNombre(nombre: String) {
        _usuario.update {
            it.copy(
                nombre = nombre,
                error = it.error.copy(nombre = null),
            )
        }
    }

    fun onChangeNombreUsuario(nombreUsuario: String) {
        _usuario.update {
            it.copy(
                nombreUsuario = nombreUsuario,
                error = it.error.copy(nombreUsuario = null),
            )
        }
    }

    fun onChangeContrasena(contrasena: String) {
        _usuario.update {
            it.copy(
                contrasena = contrasena,
                error = it.error.copy(contrasena = null),
            )
        }
    }

    fun validar(): Boolean {
        val f = _usuario.value
        val errores = PerfilErrores(
            nombre = if (f.nombre.isBlank()) "Nombre vacío" else null,
            nombreUsuario = if (f.nombreUsuario.isBlank()) "Nombre de usuario vacío" else null,
            contrasena = if (f.contrasena.isBlank()) "Contraseña vacía" else null,
        )
        _usuario.update { it.copy(error = errores) }

        return errores.run {
            nombre == null &&
                    nombreUsuario == null &&
                    contrasena == null
        }
    }

    suspend fun actualizarPerfilConId(userId: Long): Boolean {
        val u = _usuario.value

        return try {

            val response = repository.recuperarUsuarios()
            val usuarioActual = response.find { it.id == userId } ?: return false

            val usuarioActualizado = Usuario(
                id = userId,
                nombre = u.nombre,
                nombreUsuario = u.nombreUsuario,
                contrasena = u.contrasena,
                rol = usuarioActual.rol
            )
            repository.updateUsuario(usuarioActualizado) != null
        } catch (e: Exception) {
            false
        }
    }

}
