package com.basicdeb.easypos.Data.repositories

import androidx.lifecycle.LiveData
import com.basicdeb.easypos.ui.listado.Producto
import com.basicdeb.easypos.vo.Resource

interface IUseCase {

    suspend fun getProductos(): Resource<MutableList<Producto>>
}