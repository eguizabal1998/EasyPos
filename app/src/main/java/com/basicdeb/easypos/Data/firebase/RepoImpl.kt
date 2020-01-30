package com.basicdeb.easypos.Data.firebase

import android.util.Log
import com.basicdeb.easypos.vo.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class RepoImpl: IRepo {

    private val firebaseFirestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    var datos = "hola"

    override suspend fun getProductosRepo(): Resource<String> {
         val resultData = firebaseFirestore.collection("productos").get().await()

        for(document in resultData){
            Log.i("datos","${document.id} => ${document.data}")
        }

        datos = resultData.documents.toString()

        return Resource.Success(datos)
    }
}

//
//db.collection("cities")
//.get()
//.addOnSuccessListener { result ->
//    for (document in result) {
//        Log.d(TAG, "${document.id} => ${document.data}")
//    }
//}
//.addOnFailureListener { exception ->
//    Log.d(TAG, "Error getting documents: ", exception)
//}
