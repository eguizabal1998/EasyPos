package com.basicdeb.easypos.Data.firebase

import com.basicdeb.easypos.vo.Resource

interface IRepo {
    suspend fun getProductosRepo(): Resource<String>
}