package aop.fastcampus.part6.chapter01.data.repository.restaurant.review

import aop.fastcampus.part6.chapter01.data.entity.restaurant.RestaurantReviewEntity


interface RestaurantReviewRepository {

    suspend fun getReviews(restaurantTitle: String): List<RestaurantReviewEntity>

}
