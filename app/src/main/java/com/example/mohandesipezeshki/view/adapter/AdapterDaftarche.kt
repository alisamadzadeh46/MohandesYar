package com.example.mohandesipezeshki.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mohandesipezeshki.R
import com.example.mohandesipezeshki.databinding.ItemdaftarcheBinding
import com.example.mohandesipezeshki.model.ModelDaftarche

class AdapterDaftarche (val listdaftarch:List<ModelDaftarche>,val itemsclick:Clickitems):
    RecyclerView.Adapter<AdapterDaftarche.viewholder>() {
    class viewholder(val binding: ItemdaftarcheBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.itemdaftarche,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return listdaftarch.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        var data = listdaftarch[position]
        holder.binding.data=data
        holder.itemView.setOnClickListener {
            itemsclick.itemid(data.idcategory,data.category)
        }
    }
    interface Clickitems{
        fun itemid(idcategory:String,category:String)
    }
}