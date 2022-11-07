package com.tengizMKCorp.tengizExpress.ui.element

import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.tengizMKCorp.tengizExpress.databinding.FragmentAccountBinding
import com.tengizMKCorp.tengizExpress.firebase.Firebase
import com.tengizMKCorp.tengizExpress.ui.element.common.BaseFragment
import com.tengizMKCorp.tengizExpress.ui.viewmodel.AccountViewModel

class AccountFragment : BaseFragment<FragmentAccountBinding>(FragmentAccountBinding::inflate) {
    private val viewModel: AccountViewModel by viewModels()
    override fun setup() {
        binding.buttonLogOut.text = "Logout"
        setupTextMessage()
    }

    private fun setupTextMessage() {
        val currentUser = viewModel.getUserInfo()
        if (viewModel.getUserInfo() == null) {
            binding.message.text = "You are not logged In"
            binding.buttonLogOut.text = "Log in"
        } else {
            binding.message.text = "You are signed in with ${currentUser!!.email}"
        }
    }


    override fun listeners() {
        binding.buttonLogOut.setOnClickListener {
            Firebase.signOut()
            goToLogIn()
        }
    }
    private fun goToLogIn(){
        val action = AccountFragmentDirections.actionAccountFragmentToLoginFragment()
        binding.rootLayout.findNavController().navigate(action)
    }
}