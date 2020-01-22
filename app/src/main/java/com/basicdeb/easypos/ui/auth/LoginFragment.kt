package com.basicdeb.easypos.ui.auth

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.basicdeb.easypos.Data.firebase.FirebaseSource
import com.basicdeb.easypos.Data.repositories.UserRepository

import com.basicdeb.easypos.R
import com.basicdeb.easypos.databinding.LoginFragmentBinding

class LoginFragment : Fragment(), AuthListener {

    lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: LoginFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.login_fragment, container, false)

        binding.setLifecycleOwner(this)

        val factory = LoginViewModelFactory(
            UserRepository(FirebaseSource())
        )

        loginViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)

        binding.loginViewModel = loginViewModel

        loginViewModel.authListener = this

        binding.btnLoginRegistro.setOnClickListener {
            Log.i("login","click")
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root
    }

    override fun onStarted() {
        Log.i("login","onStarted")
    }

    override fun onSuccess() {
        Log.i("login","onSucces")
    }

    override fun onFailure(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        loginViewModel.dettachJob()
    }
}
