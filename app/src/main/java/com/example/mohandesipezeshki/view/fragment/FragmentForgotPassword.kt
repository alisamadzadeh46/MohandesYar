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
import com.example.mohandesipezeshki.databinding.FragmentcodeBinding
import com.example.mohandesipezeshki.databinding.FragmentforgetpasswordBinding
import com.example.mohandesipezeshki.databinding.FragmentprofileBinding
import com.example.mohandesipezeshki.model.ModelUserInfo
import com.example.mohandesipezeshki.repository.Repository
import com.example.mohandesipezeshki.utils.Farsinumber
import com.example.mohandesipezeshki.view.dialog.dialogmsg
import com.example.mohandesipezeshki.viewmodel.ViewModelCheckCode
import com.example.mohandesipezeshki.viewmodel.ViewModelCheckPhone
import com.example.mohandesipezeshki.viewmodel.ViewModelProfile
import kotlinx.android.synthetic.main.fragmentforgetpassword.*
import kotlinx.android.synthetic.main.fragmentregister.*
import kotlinx.android.synthetic.main.fragmentforgetpassword.phone

class FragmentForgotPassword :Fragment(){
    private var binding: FragmentforgetpasswordBinding? = null
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentforgetpasswordBinding.inflate(inflater, container, false)
        navController = Navigation.findNavController(requireActivity(), R.id.fragment)
        val viewmodel = ViewModelProvider(this).get(ViewModelCheckPhone::class.java)
        binding!!.data = viewmodel
        viewmodel.mutableLiveData.observe(requireActivity(), Observer {
            if (it.status == "ok") {
                val bundle = Bundle()
                bundle.putString("phone", phone?.text.toString())
                navController.navigate(R.id.action_fragmentForgotPassword_to_fragmentCodeForgot,bundle)

            }
            else{
                val da = dialogmsg(requireContext(), "شماره وارد شده موجود نیست لطفا شماره صحیح وارد کنید")
                da.show()
            }

        })


        return binding!!.root

    }
}