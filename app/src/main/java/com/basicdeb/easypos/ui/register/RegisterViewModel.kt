package com.basicdeb.easypos.ui.register

import android.util.Log
import androidx.lifecycle.ViewModel
import com.basicdeb.easypos.Data.firebase.FireBaseException
import com.basicdeb.easypos.Data.repositories.UserRepository
import com.basicdeb.easypos.ui.auth.AuthListener
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class RegisterViewModel (private val repository: UserRepository) : ViewModel(), CoroutineScope {

    private var viewModelJob = Job()

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    //email and password for singUp
    var emailsingUp: String? = null
    var passwordsingUp: String? = null
    var passwordsingUp2: String? = null
    var empresasingUp: String? = null

    //auth listener
    var authListener: AuthListener? = null


    //Doing same thing with signup
    fun signup() {
        if (emailsingUp.isNullOrEmpty() || passwordsingUp.isNullOrEmpty() || passwordsingUp2.isNullOrEmpty() || empresasingUp.isNullOrEmpty()) {
            authListener?.onFailure("Complete los campos")
            return
        }

        if (passwordsingUp != passwordsingUp2){
            authListener?.onFailure("Las contraseñas no coinciden")
            return
        }

        authListener?.onStarted()

        launch {

            try {
                Log.i("login","Try")
                repository.register(emailsingUp!!, passwordsingUp!!)
                Log.i("login","Exito")
            }catch (e: FireBaseException){
                Log.i("login","catch")
                authListener?.onFailure(e.message.toString())
            }
        }


    }

    fun dettachJob() {
        coroutineContext.cancel()
    }



}
