package com.basicdeb.easypos.Data.repositories

import com.basicdeb.easypos.Data.firebase.FirebaseSource

class UserRepository (private val firebase: FirebaseSource){

    suspend fun login(email: String, password: String) {
        firebase.login(email, password)
    }

    fun register(email: String, password: String) {
        firebase.register(email, password)
    }

    fun currentUser() {
        firebase.currentUser()
    }

    fun logout() {
        firebase.logout()
    }
}