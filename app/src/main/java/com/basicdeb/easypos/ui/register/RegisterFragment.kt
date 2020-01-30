package com.basicdeb.easypos.ui.register

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.basicdeb.easypos.Data.firebase.FirebaseSource
import com.basicdeb.easypos.Data.repositories.UserRepository

import com.basicdeb.easypos.R
import com.basicdeb.easypos.databinding.RegisterFragmentBinding
import com.basicdeb.easypos.ui.auth.AuthListener

class RegisterFragment : Fragment(), AuthListener {

    lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : RegisterFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.register_fragment,container,false
        )

        val factory = RegisterViewModelFactory(
            UserRepository(FirebaseSource())
        )

        registerViewModel = ViewModelProviders.of(this, factory).get(RegisterViewModel::class.java)

        binding.registerFragmment = registerViewModel

        registerViewModel.authListener = this

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
        registerViewModel.dettachJob()
    }


}
