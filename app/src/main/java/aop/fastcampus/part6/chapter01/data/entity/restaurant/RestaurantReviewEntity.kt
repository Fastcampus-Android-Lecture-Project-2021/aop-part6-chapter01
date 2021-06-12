package aop.fastcampus.part6.chapter01.data.entity.restaurant

import android.net.Uri
import aop.fastcampus.part6.chapter01.data.entity.Entity

data class RestaurantReviewEntity(
    override val id: Long,
    val title: String,
    val description: String,
    val grade: Int,
    val images: List<Uri>? = null
): Entity
