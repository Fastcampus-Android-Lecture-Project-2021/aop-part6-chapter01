package aop.fastcampus.part6.chapter01.screen.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import aop.fastcampus.part6.chapter01.data.entity.locaion.LocationLatLngEntity
import aop.fastcampus.part6.chapter01.data.entity.locaion.MapSearchInfoEntity
import aop.fastcampus.part6.chapter01.data.repository.map.MapRepository
import aop.fastcampus.part6.chapter01.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel(
    private val mapRepository: MapRepository
) : BaseViewModel() {

    val mainStateLiveData = MutableLiveData<MainState>(MainState.Uninitialized)

    fun loadReverseGeoInformation(
        locationLatLngEntity: LocationLatLngEntity
    ) = viewModelScope.launch {
        mainStateLiveData.value = MainState.Loading
        val addressInfo = mapRepository.getReverseGeoInformation(locationLatLngEntity)
        addressInfo?.let { info ->
            mainStateLiveData.value = MainState.Success(
                MapSearchInfoEntity(
                    fullAdress = info.fullAddress ?: "주소 정보 없음",
                    name = info.buildingName ?: "빌딩정보 없음",
                    locationLatLng = locationLatLngEntity
                )
            )
        } ?: kotlin.run {

        }
    }

}
