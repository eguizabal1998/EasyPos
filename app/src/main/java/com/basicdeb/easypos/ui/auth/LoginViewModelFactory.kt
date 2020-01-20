package com.basicdeb.easypos.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.basicdeb.easypos.Data.repositories.UserRepository
import javax.inject.Provider

class LoginViewModelFactory (private val userRepository: UserRepository): ViewModelProvider.Factory {

    
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}