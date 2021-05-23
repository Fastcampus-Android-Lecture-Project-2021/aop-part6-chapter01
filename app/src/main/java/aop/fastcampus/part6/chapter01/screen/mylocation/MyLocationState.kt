package aop.fastcampus.part6.chapter01.screen.mylocation

import aop.fastcampus.part6.chapter01.data.entity.locaion.MapSearchInfoEntity

sealed class MyLocationState {

    object Uninitialized: MyLocationState()

    object Loading: MyLocationState()

    data class Success(
        val mapSearchInfoEntity: MapSearchInfoEntity
    ): MyLocationState()

    data class Confirm(
        val mapSearchInfoEntity: MapSearchInfoEntity
    ): MyLocationState()

}
