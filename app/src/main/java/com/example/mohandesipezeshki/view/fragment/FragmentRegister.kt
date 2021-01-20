package com.example.mohandesipezeshki.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.mohandesipezeshki.R
import com.example.mohandesipezeshki.databinding.FragmentregisterBinding
import com.example.mohandesipezeshki.model.ModelStatus
import com.example.mohandesipezeshki.repository.Repository
import com.example.mohandesipezeshki.view.dialog.dialogmsg
import com.example.mohandesipezeshki.viewmodel.ViewModelRegister
import kotlinx.android.synthetic.main.fragmentcode.*
import kotlinx.android.synthetic.main.fragmentregister.*

class FragmentRegister: Fragment() {
    private var binding: FragmentregisterBinding? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentregisterBinding.inflate(inflater, container, false)
        navController= Navigation.findNavController(requireActivity(), R.id.fragment)
        val viewmodel = ViewModelProvider(this).get(ViewModelRegister::class.java)
        binding!!.data = viewmodel
        viewmodel.mutableLiveData.observe(requireActivity(), Observer {
            if (it.status == "ok") {
                val bundle = Bundle()
                bundle.putString("phone", phone?.text.toString())
                navController.navigate(R.id.action_fragmentRegister_to_fragmentCode,bundle)

            }
            else{
                val da = dialogmsg(requireContext(), "این شماره موجود است لطفا وارد شوید")
                da.show()
            }
        })
        viewmodel.Check.observe(requireActivity(), Observer {
            navController.navigate(R.id.action_fragmentRegister_to_fragmentLogin)
        })
        viewmodel.forgetpassword.observe(requireActivity(), Observer {
            navController.navigate(R.id.action_fragmentRegister_to_fragmentForgotPassword)
        })

        val shard = requireActivity().getSharedPreferences("info", 0)
        val token = shard.getString("token", null)
        token?.let {
            navController.navigate(R.id.action_fragmentRegister_to_fragmentProfile)
        }

        return binding!!.root
    }

}