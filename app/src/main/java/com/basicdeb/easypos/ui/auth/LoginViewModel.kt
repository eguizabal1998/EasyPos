package com.basicdeb.easypos.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.basicdeb.easypos.Data.firebase.FireBaseException
import com.basicdeb.easypos.Data.repositories.UserRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginViewModel (private val repository: UserRepository) : ViewModel(),CoroutineScope {

    private var viewModelJob = Job()

    private val job = viewModelJob
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job



    //email and password for the input
    var email: String? = null
    var password: String? = null

    private var _navigateToMenu = MutableLiveData<Boolean>()

    val navigatToMenu: LiveData<Boolean>
        get() = _navigateToMenu

    fun doneNavigateToMenu(){
        _navigateToMenu.value = false
    }

    //auth listener
    var authListener: AuthListener? = null


    val user: String = repository.currentUser()

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
                _navigateToMenu.value = true
            }catch (e:FireBaseException){
                Log.i("login","catch")
                authListener?.onFailure(e.message.toString())
            }
        }


    }



    fun goToSignup(view: View) {
    }

    fun goToLogin(view: View) {

    }

    fun dettachJob() {
        coroutineContext.cancel()
    }


}
