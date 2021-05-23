package aop.fastcampus.part6.chapter01.data.entity.locaion

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MapSearchInfoEntity(
    val fullAdress: String,
    val name: String,
    val locationLatLng: LocationLatLngEntity
): Parcelable
