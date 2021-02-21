package aop.fastcampus.part6.chapter01.data.entity

import aop.fastcampus.part6.chapter01.screen.restaurant.RestaurantCategory

data class RestaurantEntity(
    override val id: Long,
    val restaurantCategorys: List<RestaurantCategory>,
    val restaurantTitle: String,
    val restaurantImageUrl: String,
    val grade: Float,
    val reviewCount: Int,
    val keywords: List<String>,
    val deliveryTimeRange: Pair<Int, Int>,
    val deliveryTipRange: Pair<Int, Int>
): Entity
