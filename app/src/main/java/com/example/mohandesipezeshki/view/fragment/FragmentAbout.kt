package com.example.mohandesipezeshki.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import com.example.mohandesipezeshki.databinding.FragmentabouBinding

class FragmentAbout:Fragment(){
    private var binding: FragmentabouBinding? = null
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
            binding = FragmentabouBinding.inflate(inflater, container, false)


        }

        return binding!!.root

    }



}