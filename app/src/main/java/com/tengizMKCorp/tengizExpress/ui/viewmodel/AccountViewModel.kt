package com.tengizMKCorp.tengizExpress.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.tengizMKCorp.tengizExpress.firebase.Firebase


class AccountViewModel : ViewModel() {
    fun getUserInfo(): FirebaseUser? {
        return Firebase.getCurrentUser
    }
    fun signOut(){
        Firebase.signOut()
    }


}