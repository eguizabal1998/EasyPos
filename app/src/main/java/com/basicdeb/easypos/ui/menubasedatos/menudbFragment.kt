package com.basicdeb.easypos.ui.menubasedatos

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation

import com.basicdeb.easypos.R
import com.basicdeb.easypos.databinding.MenudbFragmentBinding

class menudbFragment : Fragment() {

    private lateinit var viewModel: MenudbViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : MenudbFragmentBinding = DataBindingUtil.inflate(
            inflater,R.layout.menudb_fragment,container,false)

        binding.setLifecycleOwner(this)

        viewModel = ViewModelProviders.of(this).get(MenudbViewModel::class.java)

        binding.menudbViewModel = viewModel

        binding.btnMenudbAgregar.setOnClickListener {
            Navigation.findNavController(it).navigate(menudbFragmentDirections.actionMenudbFragmentToModificarFragment("",0.00f,0))
        }

        binding.btnMenudbModificar.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_menudbFragment_to_modificarFragment)
        }

        return binding.root

    }

}
