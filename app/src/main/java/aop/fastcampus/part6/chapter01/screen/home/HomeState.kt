package aop.fastcampus.part6.chapter01.screen.home

import androidx.annotation.StringRes
import aop.fastcampus.part6.chapter01.data.entity.locaion.MapSearchInfoEntity
import aop.fastcampus.part6.chapter01.data.entity.restaurant.RestaurantFoodEntity

sealed class HomeState {

    object Uninitialized: HomeState()

    object Loading: HomeState()

    data class Success(
        val mapSearchInfoEntity: MapSearchInfoEntity,
        val isLocationSame: Boolean,
        val foodMenuListInBasket: List<RestaurantFoodEntity>? = null
    ): HomeState()

    data class Error(
        @StringRes val messageId: Int
    ): HomeState()

}
