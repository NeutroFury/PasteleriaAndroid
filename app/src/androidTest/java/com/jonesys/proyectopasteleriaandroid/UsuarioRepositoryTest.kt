package com.jonesys.proyectopasteleriaandroid

import com.jonesys.proyectopasteleriaandroid.model.Usuario
import com.jonesys.proyectopasteleriaandroid.remote.ApiServiceUsuarios
import com.jonesys.proyectopasteleriaandroid.repository.UsuarioRepositoryTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import retrofit2.Response
import org.junit.*
import org.junit.Assert.*

class UsuarioRepositoryTest {

    private val mockApiServiceUsuarios = mockk<ApiServiceUsuarios>()
    private val repository = UsuarioRepositoryTest(mockApiServiceUsuarios)

    @Test
    fun obtener_usuarios_getUsuario()= runTest{
        val UsuariosTest = listOf(
            Usuario(
                nombreUsuario = "user1@gmail.com",
                contrasena = "pass1",
                rol = "CLIENTE",
                nombre = "U1"
            ),
            Usuario(
                nombreUsuario = "user2gmail.com",
                contrasena = "pass2",
                rol = "CLIENTE",
                nombre = "U2"
            )
        )

        val mockkResponse = Response.success(UsuariosTest)

        coEvery { mockApiServiceUsuarios.getUsuario() } returns mockkResponse

        val resultado = repository.recuperarUsuarios()

        assertEquals(resultado, UsuariosTest)
    }
}