package com.example.mohandesipezeshki.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.mohandesipezeshki.databinding.ViewpagerBinding
import com.example.mohandesipezeshki.model.Slider

class AdapterViewPager(val list:List<Slider>):PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return  view== `object`
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = ViewpagerBinding.inflate(LayoutInflater.from(container.context),container,false)
        binding.data= list[position].image
        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getPageWidth(position: Int): Float {
        return 0.95f
    }
}