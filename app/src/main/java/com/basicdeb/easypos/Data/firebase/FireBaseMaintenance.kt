package com.basicdeb.easypos.Data.firebase

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FireBaseMaintenance {

    private val firebaseFirestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    suspend fun create(producto: HashMap<String,Any>):Unit = suspendCancellableCoroutine {continuation->


        firebaseFirestore.collection("productos")
            .add(producto)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    continuation.resume(Unit)
                }else{
                    continuation.resumeWithException(FireBaseException(it.exception.toString()))
                }
            }
    }

    suspend fun get(collection: String):Unit = suspendCancellableCoroutine { continuation ->
        firebaseFirestore.collection(collection)
            .get()
            .addOnCompleteListener {
                if(it.isSuccessful){
                    continuation.resume(Unit)
                }else{
                    continuation.resumeWithException(FireBaseException(it.exception.toString()))
                }
            }
    }
}