package com.basicdeb.easypos.Data.repositories

import com.basicdeb.easypos.vo.Resource

interface IUseCase {

    suspend fun getProductos(): Resource<String>
}