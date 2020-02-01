package com.basicdeb.easypos.ui.listado

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.basicdeb.easypos.Data.repositories.IUseCase
import com.basicdeb.easypos.vo.Resource
import kotlinx.coroutines.Dispatchers

class ListadoViewModel(useCase: IUseCase) : ViewModel() {

    val productos = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {
            val lista = useCase.getProductos()
            emit(lista)
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }

}
