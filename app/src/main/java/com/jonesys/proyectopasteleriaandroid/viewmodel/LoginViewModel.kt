package com.jonesys.proyectopasteleriaandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import android.util.Patterns
import com.jonesys.proyectopasteleriaandroid.model.FormularioLogin
import com.jonesys.proyectopasteleriaandroid.repository.UsuarioRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel(){
    private val formuLogin = MutableStateFlow(FormularioLogin())
    private val usuarioRepository = UsuarioRepository()

    val FormData: StateFlow<FormularioLogin> = formuLogin.asStateFlow()

    fun actualizarEmail(email: String){
        formuLogin.value = formuLogin.value.copy(
            email = email
        )
    }
    
    fun actualizarPassword(pass: String){
        formuLogin.value = formuLogin.value.copy(
            password = pass
        )
    }
    
    private fun validarEmail(email: String): Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    
    private fun errorNull(){
        formuLogin.value = formuLogin.value.copy(
            error = null,
        )
    }

    private fun mensajeError(mensaje: String){
        formuLogin.value = formuLogin.value.copy(
            error = mensaje,
            isLogin = false
        )
    }
    
    fun Login(){
        viewModelScope.launch {
            val f = formuLogin.value

            // Validaciones de formato
            if(f.email.length<6){
                mensajeError("El email debe ser mayor de 6 caracteres")
                return@launch
            }

            if(!validarEmail(f.email)){
                mensajeError("No tiene formato de email")
                return@launch
            }

            if(f.password.length<6){
                mensajeError("La contraseña debe tener al menos 6 caracteres")
                return@launch
            }

            // Obtener todos los usuarios y validar credenciales
            try {
                val usuarios = usuarioRepository.recuperarUsuarios()

                // Verificar que se obtuvieron usuarios de la BD
                if (usuarios.isEmpty()) {
                    mensajeError("No se pudo conectar con la base de datos")
                    return@launch
                }

                // Buscar usuario por email/nombreUsuario y contraseña
                val usuarioEncontrado = usuarios.find { usuario ->
                    usuario.nombreUsuario == f.email && usuario.contrasena == f.password
                }

                if (usuarioEncontrado != null) {
                    // Login exitoso
                    formuLogin.value = formuLogin.value.copy(
                        isLogin = true,
                        error = null,
                        nombre = usuarioEncontrado.nombre
                    )
                } else {
                    // Credenciales incorrectas
                    mensajeError("Email o contraseña incorrectos")
                }
            } catch (e: Exception) {
                mensajeError("Error al conectar con el servidor")
            }
        }
    }
}