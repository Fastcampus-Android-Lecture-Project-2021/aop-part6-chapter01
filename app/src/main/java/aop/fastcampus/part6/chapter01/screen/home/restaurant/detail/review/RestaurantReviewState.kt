package aop.fastcampus.part6.chapter01.screen.home.restaurant.detail.review

import aop.fastcampus.part6.chapter01.data.entity.restaurant.RestaurantReviewEntity

sealed class RestaurantReviewState {

    object Uninitialized: RestaurantReviewState()

    object Loading: RestaurantReviewState()

    data class Success(
        val reviewList: List<RestaurantReviewEntity>
    ): RestaurantReviewState()

}
