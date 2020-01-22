package com.basicdeb.easypos.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.basicdeb.easypos.Data.firebase.FireBaseException
import com.basicdeb.easypos.Data.repositories.UserRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginViewModel (private val repository: UserRepository) : ViewModel(),CoroutineScope {

    private var viewModelJob = Job()

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job



    //email and password for the input
    var email: String? = null
    var password: String? = null

    //email and password for singUp
    var emailsingUp: String? = null
    var passwordsingUp: String? = null
    var passwordsingUp2: String? = null
    var empresasingUp: String? = null

    //auth listener
    var authListener: AuthListener? = null


    val user by lazy {
        repository.currentUser()
    }

    //function to perform login
    fun login() {

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Complete los campos")
            Log.i("login","vacio")
            return
        }

        //authentication started

        authListener?.onStarted()

        //calling login from repository to perform the actual authentication

        launch {
            Log.i("login","launch")
            try {
                Log.i("login","Try")
                repository.login(email!!, password!!)
                Log.i("login","Exito")
            }catch (e:FireBaseException){
                Log.i("login","catch")
                authListener?.onFailure(e.message.toString())
            }
        }


    }

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

        repository.register(emailsingUp!!, passwordsingUp!!)

    }

    fun goToSignup(view: View) {
    }

    fun goToLogin(view: View) {

    }

    fun dettachJob() {
        coroutineContext.cancel()
    }


}
