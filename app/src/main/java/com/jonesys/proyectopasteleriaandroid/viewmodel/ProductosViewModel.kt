package com.jonesys.proyectopasteleriaandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonesys.proyectopasteleriaandroid.model.Producto
import com.jonesys.proyectopasteleriaandroid.repository.ProductoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductosViewModel(
    private val repository: ProductoRepository = ProductoRepository()
) : ViewModel() {

    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos

    fun cargarProductos() {
        viewModelScope.launch {
            val lista = try {
                repository.recuperarProductos()
            } catch (e: Exception) {
                emptyList<Producto>()
            }
            _productos.value = lista
        }
    }

    fun crearProducto(p: Producto, onDone: (Boolean) -> Unit = {}) {
        viewModelScope.launch {
            val ok = try { repository.grabarProducto(p) } catch (e: Exception) { false }
            onDone(ok)
            if (ok) cargarProductos()
        }
    }

    fun borrarProducto(id: Int, onDone: (Boolean) -> Unit = {}) {
        viewModelScope.launch {
            val ok = try { repository.eliminarProducto(id) } catch (e: Exception) { false }
            onDone(ok)
            if (ok) cargarProductos()
        }
    }

    fun actualizarProducto(p: Producto, onDone: (Boolean) -> Unit = {}) {
        viewModelScope.launch {
            val updated = try { repository.updateProducto(p) } catch (e: Exception) { null }
            onDone(updated != null)
            if (updated != null) cargarProductos()
        }
    }
}