package aop.fastcampus.part6.chapter01.screen.my

import android.net.Uri
import androidx.annotation.StringRes
import aop.fastcampus.part6.chapter01.model.order.OrderModel

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
            val orderList: List<OrderModel>
        ): Success()

        object NotRegistered: Success()

    }

    data class Error(
        @StringRes val messageId: Int,
        val e: Throwable
    ): MyState()

}
