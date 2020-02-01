package com.basicdeb.easypos.Data.repositories

import androidx.lifecycle.LiveData
import com.basicdeb.easypos.Data.firebase.IRepo
import com.basicdeb.easypos.ui.listado.Producto
import com.basicdeb.easypos.vo.Resource

class UseCaseImpl(private val repo: IRepo): IUseCase{

    override suspend fun getProductos(): Resource<MutableList<Producto>> = repo.getProductosRepo()

}