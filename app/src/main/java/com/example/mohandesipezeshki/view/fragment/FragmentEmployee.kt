package com.example.mohandesipezeshki.view.fragment

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
import com.example.mohandesipezeshki.databinding.FragmentemployeeBinding
import com.example.mohandesipezeshki.view.adapter.AdapterDaftarche
import com.example.mohandesipezeshki.view.adapter.AdapterEmployee
import com.example.mohandesipezeshki.viewmodel.ViewModelDaftarche
import com.example.mohandesipezeshki.viewmodel.ViewModelEmployee
import kotlinx.android.synthetic.main.fragmentdaftarche.*
import kotlinx.android.synthetic.main.fragmentemployee.*

class FragmentEmployee: Fragment() , AdapterEmployee.Clickitems {
    private var binding: FragmentemployeeBinding? = null
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding ?: run {
            binding = FragmentemployeeBinding.inflate(inflater, container, false)
            var viewmodel = ViewModelProvider(this).get(ViewModelEmployee::class.java)
            viewmodel.ListDaftarche(arguments?.getString("idcategory")!!)
            binding!!.test.text =arguments?.getString("category")!!
            viewmodel.mutableLiveData.observe(requireActivity(), Observer { item ->
                recycler_tajhizat.also {
                    it.layoutManager = LinearLayoutManager(requireActivity())
                    val adapter = AdapterEmployee(item, this)
                    it.adapter = adapter

                }
            })
        }

        return binding!!.root


    }

    override fun itemid(id: String) {
        val bundle = Bundle()
        bundle.putString("id", id)
        Navigation.findNavController(recycler_tajhizat)
            .navigate(R.id.action_fragmentEmployee_to_fragmentDetailEmployee, bundle)
    }

}