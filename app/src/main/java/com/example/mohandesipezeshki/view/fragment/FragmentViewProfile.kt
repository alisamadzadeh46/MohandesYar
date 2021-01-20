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
import com.example.mohandesipezeshki.R
import com.example.mohandesipezeshki.databinding.FragmentprofileBinding
import com.example.mohandesipezeshki.databinding.FragmentviewprofileBinding
import com.example.mohandesipezeshki.model.ModelUserInfo
import com.example.mohandesipezeshki.repository.Repository
import com.example.mohandesipezeshki.utils.Farsinumber
import com.example.mohandesipezeshki.viewmodel.ViewModelProfile
import kotlinx.android.synthetic.main.fragmentprofile.*

class FragmentViewProfile: Fragment() {
    private var binding: FragmentviewprofileBinding? = null
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentviewprofileBinding.inflate(inflater, container, false)
        navController = Navigation.findNavController(requireActivity(), R.id.fragment)
        val viewmodel = ViewModelProvider(this).get(ViewModelProfile::class.java)
        val token= Repository.shardread(requireContext())
        viewmodel.Profile(token)
        viewmodel.mutableuserinfo.observe(requireActivity(), Observer {
            val model = ModelUserInfo(it[0].phone)
            binding!!.phonetext.text=Farsinumber.farsinumber(model.phone)

            binding!!.data=model

        })


        return binding!!.root


    }
}