package com.basicdeb.easypos.ui.listado

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.basicdeb.easypos.Data.firebase.RepoImpl
import com.basicdeb.easypos.Data.repositories.UseCaseImpl

import com.basicdeb.easypos.R
import com.basicdeb.easypos.databinding.ListadoFragmentBinding
import com.basicdeb.easypos.vo.Resource

class ListadoFragment : Fragment(), MainAdapter.onItemClickListener{

    companion object {
        fun newInstance() = ListadoFragment()
    }

    private lateinit var viewModel: ListadoViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var binding: ListadoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(
            inflater, R.layout.listado_fragment,container,false)

        viewModel = ViewModelProvider(this, ListadoViewModelFactory(UseCaseImpl(RepoImpl()))).get(ListadoViewModel::class.java)

        binding.listadoViewModel = viewModel

        observeData()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = MainAdapter(this.context!!,this)

        val RecyclerView = binding.recyclerViewList

        RecyclerView.layoutManager = LinearLayoutManager(this.context)
        RecyclerView.adapter = adapter
    }

        private fun observeData(){
        viewModel.productos.observe(this.viewLifecycleOwner, Observer {result ->
            when(result){
                is Resource.Loading ->{
                    Log.i("eventos","loading")
                    binding.progressBarListado.visibility = View.VISIBLE
                }
                is Resource.Success ->{
                    binding.progressBarListado.visibility = View.GONE
                    adapter.setListData(result.data)
                    adapter.notifyDataSetChanged()
                }
                is Resource.Failure ->{
                    Log.i("eventos","Falla")
                }
            }
        })
    }

    override fun onItemClick(item: Producto, position: Int) {
        Toast.makeText(this.context,item.nombre,Toast.LENGTH_SHORT).show()
        this.findNavController().navigate(ListadoFragmentDirections.actionListadoFragmentToModificarFragment(item.nombre,item.precio,item.cantidad))
    }


}
