package com.basicdeb.easypos.ui.menu

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation

import com.basicdeb.easypos.R
import com.basicdeb.easypos.databinding.MenuFragmentBinding

class MenuFragment : Fragment() {

    private lateinit var viewModel: MenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: MenuFragmentBinding = DataBindingUtil.inflate(
            inflater,R.layout.menu_fragment,container,false)

        binding.setLifecycleOwner(this)

        viewModel = ViewModelProviders.of(this).get(MenuViewModel::class.java)

        binding.menuViewModel = viewModel

        binding.btnMenuDbase.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_menuFragment_to_menudbFragment)
        }

        return binding.root
    }


}
