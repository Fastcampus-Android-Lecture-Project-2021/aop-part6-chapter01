package aop.fastcampus.part6.chapter01.data.repository.order

import aop.fastcampus.part6.chapter01.data.entity.restaurant.RestaurantFoodEntity

interface OrderRepository {

    suspend fun orderMenu(userId: String, restaurantId: Long, foodMenuList: List<RestaurantFoodEntity>): DefaultOrderRepository.Result

}
