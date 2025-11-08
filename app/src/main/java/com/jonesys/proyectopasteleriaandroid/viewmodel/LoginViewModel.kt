package com.jonesys.proyectopasteleriaandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import android.util.Patterns
import com.jonesys.proyectopasteleriaandroid.model.FormularioLogin
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel(){
    private val formuLogin = MutableStateFlow(FormularioLogin())
    
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
            if(f.email.length<6){
                mensajeError("El email debe ser mayor de 6 caracteres")
                return@launch
            }else{
                errorNull()
            }
            if(!validarEmail(f.email)){
                mensajeError("No tiene formato de email")
                return@launch
            }else{
                errorNull()
            }
            if(f.password.length<6){
                mensajeError("La contraseña debe tener al menos 6 caracteres")
                return@launch
            }else{
                errorNull()
            }
            formuLogin.value = formuLogin.value.copy(
                isLogin = true,
                error = null
            )
        }
    }
}