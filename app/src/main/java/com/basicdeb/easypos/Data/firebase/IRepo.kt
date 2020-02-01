package com.basicdeb.easypos.Data.firebase

import androidx.lifecycle.LiveData
import com.basicdeb.easypos.ui.listado.Producto
import com.basicdeb.easypos.vo.Resource

interface IRepo {
    suspend fun getProductosRepo(): Resource<MutableList<Producto>>
}