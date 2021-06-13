package aop.fastcampus.part6.chapter01.data.response.restaurant

import aop.fastcampus.part6.chapter01.data.entity.restaurant.RestaurantFoodEntity

data class RestaurantFoodResponse(
    val id: String,
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: String
) {

    fun toEntity(restaurantId: Long, restaurantTitle: String) = RestaurantFoodEntity(
        id = id,
        title = title,
        description = description,
        price = price.toDouble().toInt(),
        imageUrl = imageUrl,
        restaurantId = restaurantId,
        restaurantTitle = restaurantTitle
    )

}
