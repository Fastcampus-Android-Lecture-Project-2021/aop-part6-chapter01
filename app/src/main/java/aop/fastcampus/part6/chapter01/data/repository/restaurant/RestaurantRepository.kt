package aop.fastcampus.part6.chapter01.data.repository.restaurant

import aop.fastcampus.part6.chapter01.data.entity.locaion.LocationLatLngEntity
import aop.fastcampus.part6.chapter01.data.entity.restaurant.RestaurantEntity
import aop.fastcampus.part6.chapter01.screen.home.restaurant.RestaurantCategory

interface RestaurantRepository {

    suspend fun getList(
        restaurantCategory: RestaurantCategory,
        locationLatLngEntity: LocationLatLngEntity
    ): List<RestaurantEntity>

}
