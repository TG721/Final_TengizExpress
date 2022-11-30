package com.tengizMKCorp.tengizExpress.ui.element

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.snackbar.Snackbar
import com.tengizMKCorp.tengizExpress.R
import com.tengizMKCorp.tengizExpress.databinding.FragmentAccountBinding
import com.tengizMKCorp.tengizExpress.firebase.Firebase
import com.tengizMKCorp.tengizExpress.ui.element.common.BaseFragment
import com.tengizMKCorp.tengizExpress.ui.viewmodel.AccountViewModel
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import kotlinx.coroutines.launch
import java.io.File

class AccountFragment : BaseFragment<FragmentAccountBinding>(FragmentAccountBinding::inflate) {
    private val viewModel: AccountViewModel by viewModels()
    private var imageUri: Uri? = null
    override fun setup() {
        binding.buttonLogOut.text = "Logout"
        setupTextMessage()
        setupImage()
    }

    private fun setupImage() {
        //get image
        val localFile = File.createTempFile("tempFile", "jpg")
        viewModel.getUserImage(localFile)
        lifecycleScope.launch {
            viewModel.userImageResponse.collect {
                when (it) {
                    is ResponseState.Success<*> -> {
                        handleUserImagePart(localFile)
                    }
                    is ResponseState.Error -> {
//                            handleError(Throwable("you have no profile picture uploaded"))

                    }
                    else -> {}
                }
            }
        }
    }
    private fun handleUserImagePart(localFile: File?) {
        binding.apply {
            if(localFile!=null) {
                val bitmap: Bitmap =
                    BitmapFactory.decodeFile(localFile.absolutePath)
                imageUser.setImageBitmap(bitmap)
            }
        }
    }

    private fun setupTextMessage() {
        val currentUser = viewModel.getUserInfo()
        if (viewModel.getUserInfo() == null) {
            binding.message.text = getString(R.string.you_are_not_logged_in)
            binding.btnCamera.visibility = View.GONE
            binding.imageUser.visibility = View.GONE
            binding.buttonLogOut.text = "Log in"
        } else {
            binding.btnCamera.visibility = View.VISIBLE
            binding.imageUser.visibility = View.VISIBLE
            binding.message.text = "You are signed in with ${currentUser!!.email}"
        }
    }


    override fun listeners() {
        binding.buttonLogOut.setOnClickListener {
            viewModel.signOut()
            goToLogIn()
        }
        binding.btnCamera.setOnClickListener {
            ImagePicker.Companion.with(this@AccountFragment)
                .crop(150f, 150f)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }
            viewModel.updateUserInfo(
                imageUri
            )
        }
    }
    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            when (resultCode) {
                Activity.RESULT_OK -> {
                    //Image Uri will not be null for RESULT_OK
                    imageUri = data?.data!!


                    Glide.with(this@AccountFragment)
                        .load(imageUri)
                        .into((binding.imageUser) as ImageView)

                }
                ImagePicker.RESULT_ERROR -> {
                    Snackbar.make(binding.rootLayout, "image error", Snackbar.LENGTH_LONG).show();
                }
                else -> {
                    Snackbar.make(binding.rootLayout, "Task Cancelled", Snackbar.LENGTH_LONG).show();

                }
            }
        }
    private fun goToLogIn(){
        val action = AccountFragmentDirections.actionAccountFragmentToLoginFragment()
        binding.rootLayout.findNavController().navigate(action)
    }

}