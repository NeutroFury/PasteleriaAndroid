package com.jonesys.proyectopasteleriaandroid.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.jonesys.proyectopasteleriaandroid.model.FormularioRegistro
import com.jonesys.proyectopasteleriaandroid.model.UsuarioErrores

class RegistroViewModel : ViewModel() {
    private val _usuario = MutableStateFlow(FormularioRegistro(aceptarTerminos = false))
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
    fun onChangeAceptarTerminos(valor: Boolean){
        _usuario.update { it.copy(aceptarTerminos = valor) }
    }

    fun validar(): Boolean{
        val f = _usuario.value
        val errores = UsuarioErrores(
            nombre = if (f.nombre.isBlank()) "Nombre esta vacio" else null,
            email = if (f.email.isBlank() || !f.email.contains("@")) "Error correo" else null,
            password = if (f.password.isBlank()) "Contraseña vacía" else null,
            confirmarPassword = if (f.confirmarPassword != f.password) "La coinciden las contraseñas" else null,
            aceptaTerminos = if(f.aceptarTerminos==false) "Debe aceptar términos" else null

        )
        _usuario.update {
            it.copy(error = errores)
        }
        if (errores.nombre==null && errores.email==null && errores.password==null
            && errores.aceptaTerminos==null){
            return  true
        } else{
            return  false
        }

    }
}
