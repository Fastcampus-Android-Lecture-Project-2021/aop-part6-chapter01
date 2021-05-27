package aop.fastcampus.part6.chapter01.data.repository.restaurant.food

import aop.fastcampus.part6.chapter01.data.entity.restaurant.RestaurantFoodEntity

interface RestaurantFoodRepository {

    suspend fun getFoods(restaurantId: Long): List<RestaurantFoodEntity>

}
