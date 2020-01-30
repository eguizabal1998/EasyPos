package com.basicdeb.easypos.ui.agregar_producto

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.basicdeb.easypos.Data.firebase.FireBaseMaintenance
import com.basicdeb.easypos.Data.repositories.MaintenanceRepository

import com.basicdeb.easypos.R
import com.basicdeb.easypos.databinding.AgregarProductoFragmentBinding
import com.basicdeb.easypos.ui.auth.AuthListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.login_fragment.*

class agregarProductoFragment : Fragment(),AuthListener {

    private lateinit var viewModel: AgregarProductoViewModel
    private lateinit var binding: AgregarProductoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,R.layout.agregar_producto_fragment,container,false)

        val factory = AgregarProductoViewModelFactory(
            MaintenanceRepository(FireBaseMaintenance())
        )

        viewModel = ViewModelProviders.of(this,factory).get(AgregarProductoViewModel::class.java)

        binding.setLifecycleOwner(this)

        binding.agregarProductoViewModel = viewModel

        viewModel.authListener = this

        return binding.root
    }

    override fun onStarted() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess() {
        Snackbar.make(activity!!.findViewById(android.R.id.content),"Producto Agregado",Snackbar.LENGTH_SHORT).show()
        Log.i("agregar","snakbar")
        binding.etAgregarNombre.text = null
        binding.etAgregarCantidad.text = null
        binding.etAgregarPrecio.text = null
        binding.switchAgregarInventario.isChecked = false
    }

    override fun onFailure(message: String) {
        Snackbar.make(activity!!.findViewById(android.R.id.content),message,Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dettachJob()
    }

}
