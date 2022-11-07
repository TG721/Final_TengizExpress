package com.tengizMKCorp.tengizExpress.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.tengizMKCorp.tengizExpress.firebase.Firebase
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel : ViewModel() {
    private val _signUpResponse =
        MutableStateFlow<ResponseState<FirebaseUser>>(ResponseState.Loading())
    val signUnResponse get() = _signUpResponse.asStateFlow()

    fun signupUser(email: String, password: String) {
        Firebase.signUp(email, password).addOnSuccessListener {
            _signUpResponse.tryEmit(ResponseState.Success(it.user!!))
        }.addOnFailureListener {
            _signUpResponse.tryEmit(ResponseState.Error(it.message))
        }
    }
}