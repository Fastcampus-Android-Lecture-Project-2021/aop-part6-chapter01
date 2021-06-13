package aop.fastcampus.part6.chapter01.data.entity.order

import aop.fastcampus.part6.chapter01.data.entity.restaurant.RestaurantFoodEntity

data class OrderEntity(
    val id: String,
    val userId: String,
    val restaurantId: Long,
    val foodMenuList: List<RestaurantFoodEntity>,
    val restaurantTitle: String
)
