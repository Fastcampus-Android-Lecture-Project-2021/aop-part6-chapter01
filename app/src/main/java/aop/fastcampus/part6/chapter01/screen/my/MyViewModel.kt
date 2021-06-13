package aop.fastcampus.part6.chapter01.screen.my

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import aop.fastcampus.part6.chapter01.R
import aop.fastcampus.part6.chapter01.data.entity.order.OrderEntity
import aop.fastcampus.part6.chapter01.data.preference.AppPreferenceManager
import aop.fastcampus.part6.chapter01.data.repository.order.DefaultOrderRepository
import aop.fastcampus.part6.chapter01.data.repository.order.OrderRepository
import aop.fastcampus.part6.chapter01.data.repository.user.UserRepository
import aop.fastcampus.part6.chapter01.model.order.OrderModel
import aop.fastcampus.part6.chapter01.screen.base.BaseViewModel
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyViewModel(
    private val appPreferenceManager: AppPreferenceManager,
    private val userRepository: UserRepository,
    private val orderRepository: OrderRepository
): BaseViewModel() {

    val myStateLiveData = MutableLiveData<MyState>(MyState.Uninitialized)

    override fun fetchData(): Job = viewModelScope.launch {
        myStateLiveData.value = MyState.Loading
        appPreferenceManager.getIdToken()?.let {
            myStateLiveData.value = MyState.Login(it)
        } ?: kotlin.run {
            myStateLiveData.value = MyState.Success.NotRegistered
        }
    }

    fun saveToken(idToken: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            appPreferenceManager.putIdToken(idToken)
            fetchData()
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun setUserInfo(firebaseUser: FirebaseUser?) = viewModelScope.launch {
        firebaseUser?.let { user ->
            when (val orderMenusResult = orderRepository.getAllOrderMenus(user.uid)) {
                is DefaultOrderRepository.Result.Success<*> -> {
                    val orderList: List<OrderEntity> = orderMenusResult.data as List<OrderEntity>
                    Log.e("orders", orderMenusResult.data.toString())
                    myStateLiveData.value = MyState.Success.Registered(
                        user.displayName ?: "익명",
                        user.photoUrl,
                        orderList.map { order ->
                            OrderModel(
                                id = order.hashCode().toLong(),
                                orderId = order.id,
                                userId = order.userId,
                                restaurantId = order.restaurantId,
                                foodMenuList = order.foodMenuList,
                                restaurantTitle = order.restaurantTitle
                            )
                        }
                    )
                }
                is DefaultOrderRepository.Result.Error -> {
                    myStateLiveData.value = MyState.Error(
                        R.string.request_error,
                        orderMenusResult.e
                    )
                }
            }

        } ?: kotlin.run {
            myStateLiveData.value = MyState.Success.NotRegistered
        }
    }

    fun signOut() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            appPreferenceManager.removeIdToken()
        }
        userRepository.deleteALlUserLikedRestaurant()
        fetchData()
    }

}
