package aop.fastcampus.part6.chapter01.screen.my

import android.net.Uri
import androidx.annotation.StringRes

sealed class MyState {

    object Uninitialized: MyState()

    object Loading: MyState()

    data class Login(
        val idToken: String
    ): MyState()

    sealed class Success: MyState() {

        data class Registered(
            val userName: String,
            val profileImageUri: Uri?,
        ): Success()

        object NotRegistered: Success()

    }

    data class Error(
        @StringRes val messageId: Int
    ): MyState()

}
