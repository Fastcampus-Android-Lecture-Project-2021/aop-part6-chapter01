package aop.fastcampus.part6.chapter01.screen.home.restaurant.detail.menu

import aop.fastcampus.part6.chapter01.model.restaurant.FoodModel

sealed class RestaurantMenuListState {

    object Uninitialized: RestaurantMenuListState()

    object Loading: RestaurantMenuListState()

    data class Success(
        val restaurantFoodModelList: List<FoodModel>? = null
    ): RestaurantMenuListState()

}
