package com.tengizMKCorp.tengizExpress

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tengizMKCorp.tengizExpress.databinding.FragmentCartBinding
import com.tengizMKCorp.tengizExpress.databinding.FragmentCategoriesBinding
import com.tengizMKCorp.tengizExpress.ui.element.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate) {
    override fun setup() {

    }
}