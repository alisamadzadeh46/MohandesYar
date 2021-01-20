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
import com.example.mohandesipezeshki.databinding.FragmentchangepasswordBinding
import com.example.mohandesipezeshki.databinding.FragmentcodeforgetBinding
import com.example.mohandesipezeshki.repository.Repository
import com.example.mohandesipezeshki.utils.Farsinumber
import com.example.mohandesipezeshki.view.dialog.dialogmsg
import com.example.mohandesipezeshki.viewmodel.ViewModelChangePassword
import com.example.mohandesipezeshki.viewmodel.ViewModelCheckCodeFotget
import kotlinx.android.synthetic.main.fragmentcode.*

class FragmentChangePassword : Fragment() {
    private var binding: FragmentchangepasswordBinding? = null
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentchangepasswordBinding.inflate(inflater, container, false)
        navController = Navigation.findNavController(requireActivity(), R.id.fragment)
        val viewmodel = ViewModelProvider(this).get(ViewModelChangePassword::class.java)
        viewmodel.phone = arguments?.getString("phone")!!
        binding!!.data = viewmodel
        viewmodel.mutableLiveData.observe(requireActivity(), Observer {
            if (it.status == "ok") {
                navController.navigate(R.id.action_fragmentChangePassword_to_fragmentProfile)
            } else {
                val da = dialogmsg(requireContext(), "کد وارد شده اشتباه است")
                da.show()
            }


        })
        return binding!!.root
    }
}