package com.tengizMKCorp.tengizExpress.ui.element

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.tengizMKCorp.tengizExpress.databinding.FragmentLoginBinding
import com.tengizMKCorp.tengizExpress.extensions.isFieldEmpty
import com.tengizMKCorp.tengizExpress.extensions.isValidEmail
import com.tengizMKCorp.tengizExpress.ui.element.common.BaseFragment
import com.tengizMKCorp.tengizExpress.ui.viewmodel.LoginViewModel
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    private val viewModel: LoginViewModel by viewModels()
    override fun setup() {
        binding.apply {
            ibtnNext.setOnClickListener {
                val email = tilEmail.editText?.text.toString()
                val password = tilPassword.editText?.text.toString()
                //validate data
                if (checkFields()) {
                    firebaseLogin(email, password)
                }
            }
        }
    }

    private fun firebaseLogin(email: String, password: String) {
        //show progress bar
        binding.progressBar.visibility = View.VISIBLE
        viewModel.login(email, password)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.signInResponse.collect {
                    when (it) {
                        is ResponseState.Success<*> -> {
                            binding.progressBar.visibility = View.GONE
                            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToAccountFragment())
                        }
                        is ResponseState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    private fun checkFields(): Boolean {
        return when {
            !binding.tilEmail.isValidEmail() -> false
            binding.tilPassword.isFieldEmpty() -> false
            else -> true
        }
    }

    override fun listeners() {
        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())

        }
        binding.floatingActionButton.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
            binding.rootLayout.findNavController().navigate(action)
        }
    }
}