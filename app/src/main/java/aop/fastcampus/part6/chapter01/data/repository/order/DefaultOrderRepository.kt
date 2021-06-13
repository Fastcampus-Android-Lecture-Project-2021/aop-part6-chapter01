package aop.fastcampus.part6.chapter01.data.repository.order

import aop.fastcampus.part6.chapter01.data.entity.order.OrderEntity
import aop.fastcampus.part6.chapter01.data.entity.restaurant.RestaurantFoodEntity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class DefaultOrderRepository(
    private val ioDispatcher: CoroutineDispatcher,
    private val firestore: FirebaseFirestore
) : OrderRepository {

    override suspend fun orderMenu(
        userId: String,
        restaurantId: Long,
        foodMenuList: List<RestaurantFoodEntity>,
        restaurantTitle: String
    ): Result = withContext(ioDispatcher) {
        val result: Result
        val orderMenuData = hashMapOf(
            "restaurantId" to restaurantId,
            "userId" to userId,
            "orderMenuList" to foodMenuList,
            "restaurantTitle" to restaurantTitle
        )
        result = try {
            firestore
                .collection("order")
                .add(orderMenuData)
            Result.Success<Any>()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e)
        }
        return@withContext result
    }

    override suspend fun getAllOrderMenus(userId: String): Result = withContext(ioDispatcher) {
        return@withContext try {
            val result: QuerySnapshot = firestore
                .collection("order")
                .whereEqualTo("userId", userId)
                .get()
                .await()
            Result.Success(result.documents.map {
                OrderEntity(
                    id = it.id,
                    userId = it.get("userId") as String,
                    restaurantId = it.get("restaurantId") as Long,
                    foodMenuList = (it.get("orderMenuList") as ArrayList<Map<String, Any>>).map { food ->
                        RestaurantFoodEntity(
                            id = food["id"] as String,
                            title = food["title"] as String,
                            description = food["description"] as String,
                            price = (food["price"] as Long).toInt(),
                            imageUrl = food["imageUrl"] as String,
                            restaurantId = food["restaurantId"] as Long,
                            restaurantTitle = food["restaurantTitle"] as String
                        )
                    },
                    restaurantTitle = it.get("restaurantTitle") as String
                )
            })
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e)
        }
    }

    sealed class Result {

        data class Success<T>(
            val data: T? = null
        ): Result()

        data class Error(
            val e: Throwable
        ): Result()

    }

}
