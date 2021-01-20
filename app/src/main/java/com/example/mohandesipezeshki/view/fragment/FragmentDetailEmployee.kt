package com.example.mohandesipezeshki.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mohandesipezeshki.R
import com.example.mohandesipezeshki.databinding.FragmentdetailemployeeBinding
import com.example.mohandesipezeshki.model.ModelEmployee
import com.example.mohandesipezeshki.model.Post
import com.example.mohandesipezeshki.model.Slider
import com.example.mohandesipezeshki.view.adapter.AdapterViewPager
import com.example.mohandesipezeshki.viewmodel.ViewModelEmployeeDetails
import kotlinx.android.synthetic.main.fragmentdetailemployee.*
import kotlinx.coroutines.Job

class FragmentDetailEmployee : Fragment() {

    lateinit var binding: FragmentdetailemployeeBinding
    lateinit var job: Job
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.fragmentdetailemployee, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewmodel = ViewModelProvider(this).get(ViewModelEmployeeDetails::class.java)
        viewmodel.ListDetails(arguments?.getString("id")!!)
        viewmodel.mutableLiveData.observe(requireActivity(), Observer {
            ViewPager(it.slider)
            val post = Post(
                it.post[0].name,
                it.post[0].model,
                it.post[0].keshvarsazande,
                it.post[0].tozihat,
                it.post[0].rahandazi,
                it.post[0].kalibrasion
            )
            binding.postdetials = post

        })
    }

    fun ViewPager(list: List<Slider>) {
        val adapter = AdapterViewPager(list)
        viewpager.adapter = adapter
        viewpager.pageMargin = 20
        viewpager.clipToPadding = false
        viewpager.setPadding(45, 8, 10, 8)
        viewpager.currentItem = 1

    }
}