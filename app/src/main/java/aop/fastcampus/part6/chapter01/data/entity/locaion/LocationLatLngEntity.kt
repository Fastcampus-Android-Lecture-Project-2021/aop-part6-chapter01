package aop.fastcampus.part6.chapter01.data.entity.locaion

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationLatLngEntity(
    val latitude: Double,
    val longitude: Double
): Parcelable

