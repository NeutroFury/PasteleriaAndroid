package com.jonesys.proyectopasteleriaandroid.viewmodel

import androidx.lifecycle.ViewModel
import com.jonesys.proyectopasteleriaandroid.model.FormuPerfil
import com.jonesys.proyectopasteleriaandroid.model.PerfilErrores
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PerfilViewModel: ViewModel() {
    private val _usuario = MutableStateFlow(FormuPerfil())
    val usuario: StateFlow<FormuPerfil> = _usuario

    fun onChangeNombre(nombre: String) {
        _usuario.update {
            it.copy(
                nombre = nombre,
                error = it.error.copy(nombre = null),
            )
        }
    }

    fun onChangeEmail(email: String) {
        _usuario.update {
            it.copy(
                email = email,
                error = it.error.copy(email = null),
            )
        }
    }

    fun onChangePassword(password: String) {
        _usuario.update {
            it.copy(
                password = password,
                error = it.error.copy(password = null),
            )
        }
    }

    fun onChangeTelefono(telefono: String) {
        _usuario.update {
            it.copy(
                telefono = telefono,
                error = it.error.copy(telefono = null),
            )
        }
    }

    fun validar(): Boolean {
        val f = _usuario.value
        val errores = PerfilErrores(
            nombre = if (f.nombre.isBlank()) "Nombre vacío" else null,
            email = if (f.email.isBlank() || !f.email.contains("@")) "Correo inválido" else null,
            password = if (f.password.isBlank()) "Contraseña vacía" else null,
            telefono = if (f.telefono.isBlank()) "Teléfono vacío" else null,
        )
        _usuario.update { it.copy(error = errores) }

        return errores.run {
            nombre == null &&
                    email == null &&
                    password == null &&
                    telefono == null
        }
    }
}
