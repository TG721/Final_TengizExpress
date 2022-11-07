package com.tengizMKCorp.tengizExpress.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.tengizMKCorp.tengizExpress.firebase.Firebase
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {
    private val _signInResponse =
        MutableStateFlow<ResponseState<FirebaseUser>>(ResponseState.Loading())
    val signInResponse get() = _signInResponse.asStateFlow()


    fun login(email: String, password: String) {
        Firebase.signIn(email, password).addOnSuccessListener {
            _signInResponse.tryEmit(ResponseState.Success(it.user!!))
        }.addOnFailureListener {
            _signInResponse.tryEmit(ResponseState.Error(it.message))
        }
    }


}