package aop.fastcampus.part6.chapter01.data.repository.user

import aop.fastcampus.part6.chapter01.data.entity.locaion.LocationLatLngEntity

interface UserRepository {

    suspend fun getUserLocation(): LocationLatLngEntity?

    suspend fun insertUserLocation(locationLatLngEntity: LocationLatLngEntity)

}
