package aop.fastcampus.part6.chapter01.model.restaurant

import aop.fastcampus.part6.chapter01.data.entity.restaurant.RestaurantEntity
import aop.fastcampus.part6.chapter01.model.CellType
import aop.fastcampus.part6.chapter01.model.Model
import aop.fastcampus.part6.chapter01.screen.home.restaurant.RestaurantCategory

data class RestaurantModel(
    override val id: Long,
    override val type: CellType = CellType.RESTAURANT_CELL,
    val restaurantInfoId: Long,
    val restaurantCategory: RestaurantCategory,
    val restaurantTitle: String,
    val restaurantImageUrl: String,
    val grade: Float,
    val reviewCount: Int,
    val deliveryTimeRange: Pair<Int, Int>,
    val deliveryTipRange: Pair<Int, Int>,
    val restaurantTelNumber: String?,
) : Model(id, type) {

    fun toEntity() = RestaurantEntity(
        id,
        restaurantInfoId,
        restaurantCategory,
        restaurantTitle,
        restaurantImageUrl,
        grade,
        reviewCount,
        deliveryTimeRange,
        deliveryTipRange,
        restaurantTelNumber
    )

}
