package com.tengizMKCorp.tengizExpress.ui.element

import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.tengizMKCorp.tengizExpress.databinding.FragmentRegisterBinding
import com.tengizMKCorp.tengizExpress.extensions.hideKeyboard
import com.tengizMKCorp.tengizExpress.extensions.isFieldEmpty
import com.tengizMKCorp.tengizExpress.extensions.isValidEmail
import com.tengizMKCorp.tengizExpress.extensions.notGoodPass
import com.tengizMKCorp.tengizExpress.ui.element.common.BaseFragment
import com.tengizMKCorp.tengizExpress.ui.viewmodel.RegisterViewModel
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    private val viewModel: RegisterViewModel by viewModels()
    override fun setup() {
        binding.apply {
            ibtnNext.setOnClickListener {
                it.hideKeyboard()
                //validate data
                if (checkFields()) {
                    val email = tilEmail.editText?.text.toString()
                    val password = tilPassword.editText?.text.toString()
                    firebaseSignUp(email, password)
                }

            }
            tvSignIn.setOnClickListener {
                it.hideKeyboard()
                goToLogInFra()
            }
            ibtnBack.setOnClickListener {
                it.hideKeyboard()
                findNavController().popBackStack()
            }
            tilPassword.editText?.addTextChangedListener {
                tilPassword.notGoodPass()
            }
            tilEmail.editText?.addTextChangedListener {
                tilEmail.isValidEmail()
            }
        }
    }

    private fun firebaseSignUp(email: String, password: String) {
        //show progress bar
        binding.progressBar.visibility = View.VISIBLE
        //create account
        viewModel.signupUser(email, password)
        viewModel
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.signUnResponse.collect {
                    when (it) {
                        is ResponseState.Success<*> -> {
                            binding.progressBar.visibility = View.GONE
                            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToAccountFragment())
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
        binding.apply {
            return when {
                tilEmail.isFieldEmpty() || tilPassword.isFieldEmpty() || tilRepeatPassword.isFieldEmpty() -> false
                tilPassword.notGoodPass() -> false
                !tilEmail.isValidEmail() -> false
                tilPassword.editText?.text.toString() != tilRepeatPassword.editText?.text.toString() -> {
                    Toast.makeText(requireContext(), "passwords should match", Toast.LENGTH_LONG)
                        .show()
                    false
                }
                else -> {
                    //data is validated, continue signup
                    //get data
                    true
                }
            }
        }
    }

    override fun listeners() {
        binding.tvSignIn.setOnClickListener {
            goToLogInFra()
        }
    }

    private fun goToLogInFra() {
        findNavController().popBackStack()
    }
}