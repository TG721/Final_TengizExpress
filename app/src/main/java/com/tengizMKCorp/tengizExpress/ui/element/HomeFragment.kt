package com.tengizMKCorp.tengizExpress.ui.element

import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.tengizMKCorp.tengizExpress.R
import com.tengizMKCorp.tengizExpress.databinding.FragmentHomeBinding
import com.tengizMKCorp.tengizExpress.ui.element.adapter.HomeItemAdapter
import com.tengizMKCorp.tengizExpress.ui.element.common.BaseFragment
import com.tengizMKCorp.tengizExpress.ui.element.model.HomeItemList


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private lateinit var homeItemAdapter: HomeItemAdapter
    override fun setup() {
        setupHomeItemRecycler()
        setupImageSlider()
    }

    private fun setupImageSlider() {
        val imageSlider = binding.imageSlider
        val imageArrayList = arrayListOf<SlideModel>(SlideModel(R.drawable.pic_1, null, null),
            SlideModel(R.drawable.pic_2, null, null),
            SlideModel(R.drawable.pic_3, null, null),
            SlideModel(R.drawable.pic_4, null, null),
        )
            imageSlider.setImageList(imageArrayList, ScaleTypes.CENTER_CROP)
    }

    private fun setupHomeItemRecycler() {
        homeItemAdapter = HomeItemAdapter()
        val homeItemRecycler = binding.homeItemRV
        homeItemRecycler.adapter = homeItemAdapter
        homeItemRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        homeItemAdapter.submitList(HomeItemList)
    }
}