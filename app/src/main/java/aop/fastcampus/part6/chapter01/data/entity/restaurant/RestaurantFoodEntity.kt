package aop.fastcampus.part6.chapter01.data.entity.restaurant

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RestaurantFoodEntity(
    val id: Long,
    val title: String,
    val description: String,
    val price: Int,
    val imageUrl: String
): Parcelable
