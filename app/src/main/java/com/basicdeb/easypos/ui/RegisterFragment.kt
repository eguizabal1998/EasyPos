package com.basicdeb.easypos.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import com.basicdeb.easypos.R
import com.basicdeb.easypos.databinding.RegisterFragmentBinding

class RegisterFragment : Fragment() {

    lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : RegisterFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.register_fragment,container,false
        )

        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)

        binding.registerFragmment = registerViewModel

        return binding.root
    }

}
