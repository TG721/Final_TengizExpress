package com.tengizMKCorp.tengizExpress.ui.element

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tengizMKCorp.tengizExpress.databinding.FragmentDetailedProductBinding
import com.tengizMKCorp.tengizExpress.ui.element.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailedProductFragment :
    BaseFragment<FragmentDetailedProductBinding>(FragmentDetailedProductBinding::inflate) {
    private val args by navArgs<DetailedProductFragmentArgs>()
    override fun setup() {
        binding.apply {
            productName.text = args.product.productName
            productPriceOriginal.text = args.product.originalPrice.toString()
            productPriceDiscounted.text = args.product.discountedPrice.toString()
            percentSaleText.text = "-" + args.product.discountPercentage.toString() + "%"
            Glide.with(ProductImage)
                .load(args.product.productPicture)
                .into(ProductImage)
        }
    }

    override fun observers() {
    }

}