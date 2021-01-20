package com.example.mohandesipezeshki.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mohandesipezeshki.R
import com.example.mohandesipezeshki.databinding.ItemdaftarcheBinding
import com.example.mohandesipezeshki.databinding.ItemstajhizatBinding
import com.example.mohandesipezeshki.model.ModelDaftarche
import com.example.mohandesipezeshki.model.ModelEmployee

class AdapterEmployee (private val listemployee:List<ModelEmployee>, private val itemsclick:Clickitems):
    RecyclerView.Adapter<AdapterEmployee.viewholder>() {
    class viewholder(val binding: ItemstajhizatBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.itemstajhizat,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return listemployee.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val data = listemployee[position]
        holder.binding.data=data
        holder.itemView.setOnClickListener {
            itemsclick.itemid(data.idemployee)
        }
    }
    interface Clickitems{
        fun itemid(id:String)
    }
}