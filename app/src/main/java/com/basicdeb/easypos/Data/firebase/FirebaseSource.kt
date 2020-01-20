package com.basicdeb.easypos.Data.firebase

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FirebaseSource {

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }


    suspend fun login(email: String, password: String) : Unit = suspendCancellableCoroutine{continuation ->
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    continuation.resume(Unit)
                }
                else {
                    continuation.resumeWithException(FireBaseException(it.exception.toString()))
                }
        }
    }

    fun register(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    //emitter.onComplete()
                }
                else {
                    //emitter.onError(it.exception!!)
                }
            }
        }


    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser
}