package aop.fastcampus.part6.chapter01.screen.main

import aop.fastcampus.part6.chapter01.data.entity.locaion.MapSearchInfoEntity
import aop.fastcampus.part6.chapter01.data.entity.restaurant.RestaurantFoodEntity

sealed class MainState {

    object Uninitialized: MainState()

    object Loading: MainState()

    data class Success(
        val mapSearchInfoEntity: MapSearchInfoEntity,
        val isLocationSame: Boolean,
        val foodMenuListInBasket: List<RestaurantFoodEntity>? = null
    ): MainState()

}
