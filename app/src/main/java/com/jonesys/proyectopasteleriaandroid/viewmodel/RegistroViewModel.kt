package com.jonesys.proyectopasteleriaandroid.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.jonesys.proyectopasteleriaandroid.model.FormularioRegistro
import com.jonesys.proyectopasteleriaandroid.model.UsuarioErrores

class RegistroViewModel : ViewModel() {
    private val _usuario = MutableStateFlow(FormularioRegistro(aceptarTerminos = false,telefono = ""))

    val usuario: StateFlow<FormularioRegistro> = _usuario

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

    fun onChangeConfirmar(confirmar: String) {
        _usuario.update {
            it.copy(
                confirmarPassword = confirmar,
                error = it.error.copy(confirmarPassword = null),
            )
        }
    }

    // Limpia también el error de aceptaTerminos al cambiar el checkbox
    fun onChangeAceptarTerminos(valor: Boolean) {
        _usuario.update {
            it.copy(
                aceptarTerminos = valor,
                error = it.error.copy(aceptaTerminos = null)
            )
        }
    }

    fun validar(): Boolean {
        val f = _usuario.value
        val errores = UsuarioErrores(
            nombre = if (f.nombre.isBlank()) "Nombre vacío" else null,
            email = if (f.email.isBlank() || !f.email.contains("@")) "Correo inválido" else null,
            password = if (f.password.isBlank()) "Contraseña vacía" else null,
            confirmarPassword = when {
                f.confirmarPassword.isBlank() -> "Confirmación vacía"
                f.confirmarPassword != f.password -> "Las contraseñas no coinciden"
                else -> null
            },
            aceptaTerminos = if (!f.aceptarTerminos) "Debe aceptar términos" else null
        )
        _usuario.update { it.copy(error = errores) }

        return errores.run {
            nombre == null &&
                    email == null &&
                    password == null &&
                    confirmarPassword == null &&
                    aceptaTerminos == null
        }
    }
}