package com.tengizMKCorp.tengizExpress.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.tengizMKCorp.tengizExpress.firebase.Firebase
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File


class AccountViewModel : ViewModel() {
    fun getUserInfo(): FirebaseUser? {
        return Firebase.getCurrentUser
    }
    fun signOut(){
        Firebase.signOut()
    }
    private val _userImageResponse =
        MutableStateFlow<ResponseState<Unit>>(ResponseState.Loading())
    val userImageResponse get() = _userImageResponse.asStateFlow()

    fun getUserImage(file: File) {
        Firebase.getUserImage(file).addOnSuccessListener {
            _userImageResponse.tryEmit(ResponseState.Success(Unit))
        }.addOnFailureListener {
            _userImageResponse.tryEmit(ResponseState.Error(it.message))
        }
    }
    fun updateUserInfo( imageUri: Uri?) {
        Firebase.updateUserInfo( imageUri)
    }


}