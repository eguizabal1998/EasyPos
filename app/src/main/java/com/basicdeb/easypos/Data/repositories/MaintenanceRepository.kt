package com.basicdeb.easypos.Data.repositories

import com.basicdeb.easypos.Data.firebase.FireBaseMaintenance


class MaintenanceRepository (private val firestore: FireBaseMaintenance){


    suspend fun create(nombre:String, precio:Float,
                       inventario:Boolean, cantidad:Int){

        val producto = hashMapOf(
            "nombre" to nombre,
            "precio" to precio,
            "inventario"  to inventario,
            "cantidad" to cantidad
        )

        firestore.create(producto)
    }

    suspend fun get(collection: String){
        firestore.get(collection)
    }

}