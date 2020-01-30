
package com.basicdeb.easypos.ui.agregar_producto

import androidx.lifecycle.ViewModel
import com.basicdeb.easypos.Data.firebase.FireBaseException
import com.basicdeb.easypos.Data.repositories.MaintenanceRepository
import com.basicdeb.easypos.ui.auth.AuthListener
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AgregarProductoViewModel (private val maintenanceRepository: MaintenanceRepository) : ViewModel(), CoroutineScope {

    private var viewModelJob = Job()

    private val job = viewModelJob
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    var nombre: String? = null
    var precio: String? = null
    var inventario: Boolean? = null
    var cantidad: String? = null


    //auth listener
    var authListener: AuthListener? = null

    fun crearProducto(){
        if(nombre.isNullOrEmpty() || precio.isNullOrEmpty() || cantidad.isNullOrEmpty()){
            authListener?.onFailure("Complete Los campos")
            return
        }

        launch {
            try {
                maintenanceRepository.create(nombre!!, precio!!.toFloat(),inventario!!,cantidad!!.toInt())
//                nombre = ""
//                precio = ""
//                inventario = false
//                cantidad = ""
                authListener?.onSuccess()
            }catch (e:FireBaseException){
                authListener?.onFailure(e.toString())
            }
        }

    }

    fun dettachJob() {
        coroutineContext.cancel()
    }

}
