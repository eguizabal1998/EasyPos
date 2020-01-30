package com.basicdeb.easypos.ui.listado

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.basicdeb.easypos.Data.firebase.FireBaseMaintenance
import com.basicdeb.easypos.Data.firebase.RepoImpl
import com.basicdeb.easypos.Data.repositories.IUseCase
import com.basicdeb.easypos.Data.repositories.MaintenanceRepository
import com.basicdeb.easypos.Data.repositories.UseCaseImpl

import com.basicdeb.easypos.R
import com.basicdeb.easypos.databinding.ListadoFragmentBinding
import com.basicdeb.easypos.ui.agregar_producto.AgregarProductoViewModelFactory
import com.basicdeb.easypos.vo.Resource

class ListadoFragment : Fragment() {

    companion object {
        fun newInstance() = ListadoFragment()
    }

    private lateinit var viewModel: ListadoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: ListadoFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.listado_fragment,container,false)

        viewModel = ViewModelProvider(this, ListadoViewModelFactory(UseCaseImpl(RepoImpl()))).get(ListadoViewModel::class.java)

        binding.listadoViewModel = viewModel

        observeData()

        return binding.root
    }

    private fun observeData(){
        viewModel.infodb.observe(this.viewLifecycleOwner, Observer {result ->
            when(result){
                is Resource.Loading ->{
                    Log.i("eventos","loading")
                }
                is Resource.Success ->{
                    Log.i("eventos",result.data.toString())
                }
                is Resource.Failure ->{
                    Log.i("eventos","Falla")
                }
            }
        })
    }


}
