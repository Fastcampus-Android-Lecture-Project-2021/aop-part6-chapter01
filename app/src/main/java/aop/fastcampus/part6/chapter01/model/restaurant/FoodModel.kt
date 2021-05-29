package aop.fastcampus.part6.chapter01.model.restaurant

import aop.fastcampus.part6.chapter01.data.entity.restaurant.RestaurantFoodEntity
import aop.fastcampus.part6.chapter01.model.CellType
import aop.fastcampus.part6.chapter01.model.Model

data class FoodModel(
    override val id: Long,
    override val type: CellType = CellType.FOOD_CELL,
    val title: String,
    val description: String,
    val price: Int,
    val imageUrl: String,
    val restaurantId: Long
) : Model(id, type) {

    fun toEntity(basketCount: Int) = RestaurantFoodEntity(
        "$title$basketCount", title, description, price, imageUrl, restaurantId
    )

}
