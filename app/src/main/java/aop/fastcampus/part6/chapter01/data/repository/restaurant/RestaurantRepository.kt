package aop.fastcampus.part6.chapter01.data.repository.restaurant

import aop.fastcampus.part6.chapter01.data.entity.RestaurantEntity
import aop.fastcampus.part6.chapter01.screen.restaurant.RestaurantCategory

interface RestaurantRepository {

    fun getList(restaurantCategory: RestaurantCategory): List<RestaurantEntity>

}
