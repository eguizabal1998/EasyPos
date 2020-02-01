package com.basicdeb.easypos.ui.listado

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.basicdeb.easypos.R
import kotlinx.android.synthetic.main.product_row.view.*

class MainAdapter(private val context: Context, var clickListener: onItemClickListener): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var dataList = mutableListOf<Producto>()

    fun setListData(data:MutableList<Producto>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate( R.layout.product_row,parent,false)

        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if(dataList.size > 0){
            dataList.size
        }else{
            0
        }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val producto = dataList[position]
        holder.bindView(producto, clickListener)
    }

    inner class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindView(producto: Producto, action:onItemClickListener){
            itemView.tv_Product_nombre.text = producto.nombre
            itemView.tv_Product_precio.text = producto.precio.toString()
            itemView.tv_Product_cantidad.text = producto.cantidad.toString()

            itemView.setOnClickListener {
                action.onItemClick(producto, adapterPosition)
            }
        }

    }

    interface onItemClickListener{
        fun onItemClick(item: Producto, position: Int)
    }

}