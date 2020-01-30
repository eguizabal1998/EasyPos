package com.basicdeb.easypos.ui.modificar

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation

import com.basicdeb.easypos.R
import com.basicdeb.easypos.databinding.ModificarFragmentBinding

class ModificarFragment : Fragment() {

    private lateinit var viewModel: ModificarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ModificarFragmentBinding = DataBindingUtil.inflate(
            inflater,R.layout.modificar_fragment,container,false)

        binding.setLifecycleOwner(this)

        viewModel = ViewModelProviders.of(this).get(ModificarViewModel::class.java)

        binding.modificarViewModel = viewModel

        binding.btnModificarBuscar.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_modificarFragment_to_listadoFragment)
        }

        return binding.root
    }


}
