package aop.fastcampus.part6.chapter01.data.repository.order

import aop.fastcampus.part6.chapter01.data.entity.restaurant.RestaurantFoodEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultOrderRepository(
    private val ioDispatcher: CoroutineDispatcher,
    private val firestore: FirebaseFirestore
) : OrderRepository {

    override suspend fun orderMenu(
        userId: String,
        restaurantId: Long,
        foodMenuList: List<RestaurantFoodEntity>
    ): Result = withContext(ioDispatcher) {
        val result: Result
        val orderMenuData = hashMapOf(
            "restaurantId" to restaurantId,
            "userId" to userId,
            "orderMenuList" to foodMenuList
        )
        result = try {
            firestore
                .collection("order")
                .add(orderMenuData)
            Result.Success
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e)
        }
        return@withContext result
    }

    sealed class Result {

        object Success: Result()

        data class Error(
            val e: Throwable
        ): Result()

    }

}
