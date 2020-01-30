package com.basicdeb.easypos.Data.repositories

import com.basicdeb.easypos.Data.firebase.IRepo
import com.basicdeb.easypos.vo.Resource

class UseCaseImpl(private val repo: IRepo): IUseCase{

    override suspend fun getProductos(): Resource<String> = repo.getProductosRepo()

}