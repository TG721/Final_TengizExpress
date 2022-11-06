package com.tengizMKCorp.tengizExpress.ui.element

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tengizMKCorp.tengizExpress.data.local.source.product.NonDetailedProductDataBaseModel
import com.tengizMKCorp.tengizExpress.databinding.FragmentDetailedProductBinding
import com.tengizMKCorp.tengizExpress.ui.element.common.BaseFragment
import com.tengizMKCorp.tengizExpress.ui.element.model.convertNonDetailedProductInfoToCartModel
import com.tengizMKCorp.tengizExpress.ui.viewmodel.DetailedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailedProductFragment :
    BaseFragment<FragmentDetailedProductBinding>(FragmentDetailedProductBinding::inflate) {
    private val args by navArgs<DetailedProductFragmentArgs>()
    private val viewModel: DetailedViewModel by viewModels()
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
        viewModel.readAllDataFromLastViewedTableUseCase()
        lifecycleScope.launch {
            viewModel.products.collect { it ->
                val lastViewedItemsSize = it.size
                if (lastViewedItemsSize == 3) {
                    viewModel.deleteProductFromLastViewedTable(it.elementAt(0))
                }
            }
        }
            viewModel.addProductToLastViewedTable((NonDetailedProductDataBaseModel(args.product.id,
                args.product.originalPrice,
                args.product.discountedPrice,
                args.product.discountPercentage,
                args.product.productName,
                args.product.productPicture))
            )

    }

    override fun listeners() {
        binding.buttonAddToChart.setOnClickListener {
            viewModel.addProductToCart(convertNonDetailedProductInfoToCartModel(args.product))
            Toast.makeText(requireContext(), "Added to cart", Toast.LENGTH_LONG).show()
        }
    }
}