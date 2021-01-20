package com.example.mohandesipezeshki.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mohandesipezeshki.R
import com.example.mohandesipezeshki.databinding.FragmentdaftarcheBinding
import com.example.mohandesipezeshki.view.adapter.AdapterDaftarche
import com.example.mohandesipezeshki.viewmodel.ViewModelDaftarche
import kotlinx.android.synthetic.main.fragmentdaftarche.*

class FragmentDaftarche: Fragment() , AdapterDaftarche.Clickitems {
    private var binding: FragmentdaftarcheBinding? = null
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding ?: run {
            binding = FragmentdaftarcheBinding.inflate(inflater, container, false)
            var viewmodel = ViewModelProvider(this).get(ViewModelDaftarche::class.java)
            viewmodel.ListDaftarche()
            viewmodel.mutableLiveData.observe(requireActivity(), Observer { itpost ->
                recyclerView.also {
                    it.layoutManager = LinearLayoutManager(requireActivity())
                    val adapter = AdapterDaftarche(itpost, this)
                    it.adapter = adapter

                }
            })
        }
        
        return binding!!.root


    }

    override fun itemid(idcategory: String, category: String) {
        val bundle = Bundle()
        bundle.putString("idcategory", idcategory)
        bundle.putString("category", category)
        Navigation.findNavController(recyclerView)
            .navigate(R.id.action_fragmentDaftarche_to_fragmentEmployee, bundle)
    }


}