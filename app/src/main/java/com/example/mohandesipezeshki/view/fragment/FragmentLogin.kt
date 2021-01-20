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
import com.example.mohandesipezeshki.databinding.FragmentloginBinding
import com.example.mohandesipezeshki.databinding.FragmentregisterBinding
import com.example.mohandesipezeshki.repository.Repository
import com.example.mohandesipezeshki.view.dialog.dialogmsg
import com.example.mohandesipezeshki.viewmodel.ViewModelLogin
import com.example.mohandesipezeshki.viewmodel.ViewModelRegister
import kotlinx.android.synthetic.main.fragmentregister.*

class FragmentLogin : Fragment() {
    private var binding: FragmentloginBinding? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentloginBinding.inflate(inflater, container, false)
        navController = Navigation.findNavController(requireActivity(), R.id.fragment)
        val viewmodel = ViewModelProvider(this).get(ViewModelLogin::class.java)
        binding!!.data = viewmodel
        viewmodel.mutableLiveData.observe(requireActivity(), Observer {
            if (it.status == "ok") {
                Repository.shardwrite(requireContext(), it.token)
                navController.navigate(R.id.action_fragmentLogin_to_fragmentProfile)

            } else {
                val da = dialogmsg(requireContext(), "رمز عبور یا شماره موبایل اشتباه است")
                da.show()
            }
        })
        viewmodel.GetRegister.observe(requireActivity(), Observer {
            navController.navigate(R.id.action_fragmentLogin_to_fragmentRegister)
        })
        viewmodel.forgetpassword.observe(requireActivity(), Observer {
            navController.navigate(R.id.action_fragmentLogin_to_fragmentForgotPassword)
        })
        val shard = requireActivity().getSharedPreferences("info", 0)
        val token = shard.getString("token", null)
        token?.let {
            navController.navigate(R.id.action_fragmentLogin_to_fragmentProfile)
        }

        return binding!!.root
    }

}