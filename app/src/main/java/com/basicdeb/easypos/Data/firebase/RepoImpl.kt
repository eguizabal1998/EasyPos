package com.basicdeb.easypos.Data.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import com.basicdeb.easypos.ui.listado.Producto
import com.basicdeb.easypos.vo.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class RepoImpl: IRepo {

    private val firebaseFirestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }


    override suspend fun getProductosRepo(): Resource<MutableList<Producto>> {
         val resultData = firebaseFirestore.collection("productos").get().await()

        val productosList = mutableListOf<Producto>()

        for(document in resultData){
            productosList.add(document.toObject(Producto::class.java))
            Log.i("evento",productosList.toString())
        }

        var datos = productosList

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
