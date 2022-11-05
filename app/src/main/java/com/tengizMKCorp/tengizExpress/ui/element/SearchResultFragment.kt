package com.tengizMKCorp.tengizExpress.ui.element

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.tengizMKCorp.tengizExpress.databinding.FragmentSearchBinding
import com.tengizMKCorp.tengizExpress.databinding.FragmentSearchResultBinding
import com.tengizMKCorp.tengizExpress.ui.element.common.BaseFragment


class SearchResultFragment  : BaseFragment<FragmentSearchResultBinding>(FragmentSearchResultBinding::inflate) {
    private val args by navArgs<SearchResultFragmentArgs>()
    override fun setup() {
        binding.resultTitle.text = args.inputName
    }

}