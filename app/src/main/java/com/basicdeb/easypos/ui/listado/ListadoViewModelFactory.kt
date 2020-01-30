package com.basicdeb.easypos.ui.listado

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.basicdeb.easypos.Data.repositories.IUseCase

class ListadoViewModelFactory(private val useCase: IUseCase): ViewModelProvider.Factory {

    @SuppressWarnings("Unchecked")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IUseCase::class.java).newInstance(useCase)
    }
}