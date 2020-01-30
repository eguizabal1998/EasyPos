package com.basicdeb.easypos.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.basicdeb.easypos.Data.repositories.UserRepository

class RegisterViewModelFactory (private val userRepository: UserRepository): ViewModelProvider.Factory {

    @SuppressWarnings("Unchecked")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(
                userRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}