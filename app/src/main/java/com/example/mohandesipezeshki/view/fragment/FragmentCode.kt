package com.example.mohandesipezeshki.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.mohandesipezeshki.R
import com.example.mohandesipezeshki.databinding.FragmentcodeBinding
import com.example.mohandesipezeshki.databinding.FragmentregisterBinding
import com.example.mohandesipezeshki.model.ModelStatus
import com.example.mohandesipezeshki.repository.Api
import com.example.mohandesipezeshki.repository.HandelRequest
import com.example.mohandesipezeshki.repository.MainThered
import com.example.mohandesipezeshki.repository.Repository
import com.example.mohandesipezeshki.utils.Farsinumber
import com.example.mohandesipezeshki.view.dialog.dialogmsg
import com.example.mohandesipezeshki.viewmodel.ViewModelCheckCode
import com.example.mohandesipezeshki.viewmodel.ViewModelRegister
import com.sdsmdg.tastytoast.TastyToast
import kotlinx.android.synthetic.main.fragmentcode.*
import kotlinx.android.synthetic.main.fragmentcode.button
import kotlinx.android.synthetic.main.fragmentregister.*
import kotlinx.coroutines.Job

class FragmentCode : Fragment() {
    private var binding: FragmentcodeBinding? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentcodeBinding.inflate(inflater, container, false)
        navController = Navigation.findNavController(requireActivity(), R.id.fragment)
        val viewmodel = ViewModelProvider(this).get(ViewModelCheckCode::class.java)
        binding!!.showtext.text =
            Farsinumber.farsinumber("  کد تایید به  شماره  " + arguments?.getString("phone")!! + " ارسال شد   ")
        viewmodel.phone = arguments?.getString("phone")!!
        binding!!.data = viewmodel
        viewmodel.mutableLiveData.observe(requireActivity(), Observer {
            if (it.status == "ok") {
                Repository.shardwrite(requireContext(),it.token)
                navController.navigate(R.id.action_fragmentCode_to_fragmentProfile)
                TastyToast.makeText(
                    requireContext(),
                    "خوش امدید",
                    TastyToast.LENGTH_LONG,
                    TastyToast.SUCCESS
                )
            } else {
                val da = dialogmsg(requireContext(), "کد وارد شده اشتباه است")
                da.show()
            }


        })
        return binding!!.root
    }
}