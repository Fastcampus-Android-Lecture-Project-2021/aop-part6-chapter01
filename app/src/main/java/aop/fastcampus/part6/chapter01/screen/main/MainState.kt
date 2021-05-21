package aop.fastcampus.part6.chapter01.screen.main

import aop.fastcampus.part6.chapter01.data.entity.locaion.MapSearchInfoEntity

sealed class MainState {

    object Uninitialized: MainState()

    object Loading: MainState()

    data class Success(
        val mapSearchInfoEntity: MapSearchInfoEntity
    ): MainState()

}
