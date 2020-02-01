package com.basicdeb.easypos.ui.listado

data class Producto(val nombre: String = "Producto",
                    val precio: Float = 0.00f,
                    val inventario: Boolean = false,
                    val cantidad: Int = 0)