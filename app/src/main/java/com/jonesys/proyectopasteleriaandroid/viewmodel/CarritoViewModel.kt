package com.jonesys.proyectopasteleriaandroid.viewmodel

import com.jonesys.proyectopasteleriaandroid.model.Carrito
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonesys.proyectopasteleriaandroid.model.CarritoItem
import com.jonesys.proyectopasteleriaandroid.model.Producto
import com.jonesys.proyectopasteleriaandroid.model.Usuario
import com.jonesys.proyectopasteleriaandroid.repository.CarritoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CarritoViewModel : ViewModel() {

    private val repository = CarritoRepository()

    private val _carritoItems = MutableStateFlow<List<CarritoItem>>(emptyList())
    val carritoItems: StateFlow<List<CarritoItem>> = _carritoItems

    private var carritoId: Long? = null

    fun cargarCarrito(usuarioId: Long) {
        viewModelScope.launch {
            try {
                val carritoResponse = repository.getCarritoByUsuario(usuarioId)
                if (carritoResponse.isSuccessful) {
                    val carrito: Carrito? = carritoResponse.body()
                    if (carrito != null && carrito.id != null) {
                        carritoId = carrito.id
                        cargarItems(carrito.id)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun cargarItems(carritoId: Long) {
        android.util.Log.d("CarritoViewModel", "Cargando items del carrito $carritoId")
        val itemsResponse = repository.getItemsByCarrito(carritoId)
        android.util.Log.d("CarritoViewModel", "Response items: ${itemsResponse.isSuccessful}, code: ${itemsResponse.code()}")
        if (itemsResponse.isSuccessful) {
            _carritoItems.value = itemsResponse.body() ?: emptyList()
            android.util.Log.d("CarritoViewModel", "Items cargados: ${_carritoItems.value.size}")
        } else {
            android.util.Log.e("CarritoViewModel", "Error cargando items: ${itemsResponse.errorBody()?.string()}")
        }
    }

    fun actualizarCantidad(itemId: Long, nuevaCantidad: Int) {
        viewModelScope.launch {
            val item = _carritoItems.value.find { it.id == itemId }
            item?.let {
                val itemActualizado = CarritoItem(
                    id = it.id,
                    carrito = it.carrito,
                    producto = it.producto,
                    cantidad = nuevaCantidad
                )
                val response = repository.updateCarritoItem(itemId, itemActualizado)
                if (response.isSuccessful) {
                    carritoId?.let { id -> cargarItems(id) }
                }
            }
        }
    }

    fun eliminarItem(itemId: Long) {
        viewModelScope.launch {
            val response = repository.deleteCarritoItem(itemId)
            if (response.isSuccessful) {
                carritoId?.let { id -> cargarItems(id) }
            }
        }
    }

    fun agregarProductoAlCarrito(usuarioId: Long, producto: Producto, cantidad: Int) {
        viewModelScope.launch {
            try {
                android.util.Log.d("CarritoViewModel", "Iniciando agregar producto al carrito. UserId: $usuarioId, Producto: ${producto.nombre}")

                var carrito: Carrito? = null
                val carritoResponse = repository.getCarritoByUsuario(usuarioId)

                android.util.Log.d("CarritoViewModel", "Response carrito: ${carritoResponse.isSuccessful}, code: ${carritoResponse.code()}")

                if (carritoResponse.isSuccessful && carritoResponse.body() != null) {
                    carrito = carritoResponse.body()
                    android.util.Log.d("CarritoViewModel", "Carrito encontrado: ${carrito?.id}")
                } else {
                    android.util.Log.d("CarritoViewModel", "Creando nuevo carrito para usuario: $usuarioId")
                    val nuevoCarrito = Carrito(
                        usuario = Usuario(
                            id = usuarioId,
                            nombreUsuario = "",
                            contrasena = "",
                            rol = "",
                            nombre = ""
                        )
                    )
                    val crearResponse = repository.saveCarrito(nuevoCarrito)
                    android.util.Log.d("CarritoViewModel", "Response crear carrito: ${crearResponse.isSuccessful}, code: ${crearResponse.code()}")

                    if (crearResponse.isSuccessful) {
                        carrito = crearResponse.body()
                        android.util.Log.d("CarritoViewModel", "Carrito creado: ${carrito?.id}")
                    } else {
                        android.util.Log.e("CarritoViewModel", "Error creando carrito: ${crearResponse.errorBody()?.string()}")
                    }
                }

                carrito?.let { carritoActual ->
                    carritoId = carritoActual.id

                    if (_carritoItems.value.isEmpty()) {
                        carritoActual.id?.let { id -> cargarItems(id) }
                    }

                    val itemExistente = _carritoItems.value.find {
                        it.producto?.id == producto.id
                    }

                    if (itemExistente != null) {

                        android.util.Log.d("CarritoViewModel", "Producto ya existe, actualizando cantidad")
                        val nuevaCantidad = (itemExistente.cantidad ?: 0) + cantidad
                        itemExistente.id?.let { itemId ->
                            actualizarCantidad(itemId, nuevaCantidad)
                        }
                    } else {
                        android.util.Log.d("CarritoViewModel", "Agregando nuevo item al carrito ${carritoActual.id}")
                        val nuevoItem = CarritoItem(
                            carrito = carritoActual,
                            producto = producto,
                            cantidad = cantidad
                        )

                        val itemResponse = repository.saveCarritoItem(nuevoItem)
                        android.util.Log.d("CarritoViewModel", "Response item: ${itemResponse.isSuccessful}, code: ${itemResponse.code()}")

                        if (itemResponse.isSuccessful) {
                            android.util.Log.d("CarritoViewModel", "Item agregado exitosamente")
                            carritoActual.id?.let { id -> cargarItems(id) }
                        } else {
                            android.util.Log.e("CarritoViewModel", "Error agregando item: ${itemResponse.errorBody()?.string()}")
                        }
                    }
                } ?: run {
                    android.util.Log.e("CarritoViewModel", "Carrito es null, no se puede agregar item")
                }
            } catch (e: Exception) {
                android.util.Log.e("CarritoViewModel", "Exception: ${e.message}", e)
                e.printStackTrace()
            }
        }
    }
}