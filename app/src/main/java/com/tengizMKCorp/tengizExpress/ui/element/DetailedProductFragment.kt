package com.tengizMKCorp.tengizExpress.ui.element

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.tengizMKCorp.tengizExpress.databinding.FragmentDetailedProductBinding
import com.tengizMKCorp.tengizExpress.ui.element.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailedProductFragment :
    BaseFragment<FragmentDetailedProductBinding>(FragmentDetailedProductBinding::inflate) {
    private val args by navArgs<DetailedProductFragmentArgs>()
    override fun setup() {
    }

    override fun observers() {
    }

}