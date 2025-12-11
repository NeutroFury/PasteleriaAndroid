package com.jonesys.proyectopasteleriaandroid

import androidx.compose.ui.test.junit4.createComposeRule
import com.jonesys.proyectopasteleriaandroid.viewmodel.ProductosViewModel
import com.jonesys.proyectopasteleriaandroid.remote.ApiServiceProductos
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import com.jonesys.proyectopasteleriaandroid.model.Producto
import com.jonesys.proyectopasteleriaandroid.repository.ProductoRepositoryTest

import io.mockk.coEvery
import org.junit.Assert.*
import okhttp3.Response
import retrofit2.http.POST

class ProductoTest {
    private val mockApiService = mockk<ApiServiceProductos>()
    private val repository = ProductoRepositoryTest(mockApiService)

    @Test
    fun recuperar_productos()= runTest  {
        val repoFake = listOf(
            Producto(1, "PMT01", "Pastel de chocolate", "Deliciosa torta de chocolate con capas de ganache y un toque de avellanas. Personalizable con mensajes especiales", 15.000,3,"pastel1.png","DISPONIBLE",0,1),
            Producto(2, "PMT02", "Tarta de fresa", "Una mezcla de frutas frescas y crema chantilly sobre un suave bizcocho de vainilla, ideal para celebraciones.", 10.000,5,"pastel2.png","DISPONIBLE",0,1)
            )
        val mockResponse = retrofit2.Response.success(repoFake)

        coEvery { mockApiService.getProducto() } returns mockResponse

        val resultado = repository.recuperarProductos()
        assertEquals(resultado, repoFake)

    }

}