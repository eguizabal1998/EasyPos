package com.basicdeb.easypos.ui.agregar_producto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.basicdeb.easypos.Data.repositories.MaintenanceRepository

class AgregarProductoViewModelFactory (private val maintenanceRepository: MaintenanceRepository): ViewModelProvider.Factory {

    @SuppressWarnings("Unchecked")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AgregarProductoViewModel::class.java)) {
            return AgregarProductoViewModel(
                maintenanceRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}