package aop.fastcampus.part6.chapter01.screen.main.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import aop.fastcampus.part6.chapter01.data.entity.locaion.LocationLatLngEntity
import aop.fastcampus.part6.chapter01.data.repository.restaurant.DefaultRestaurantRepository
import aop.fastcampus.part6.chapter01.data.repository.restaurant.RestaurantRepository
import aop.fastcampus.part6.chapter01.model.restaurant.RestaurantModel
import aop.fastcampus.part6.chapter01.screen.base.ModelListViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RestaurantListViewModel(
    private val restaurantCategory: RestaurantCategory,
    private val locationLatLngEntity: LocationLatLngEntity,
    private val restaurantRepository: RestaurantRepository
): ModelListViewModel() {

    private var _restaurantListLiveData = MutableLiveData<List<RestaurantModel>>()
    val restaurantListLiveData: LiveData<List<RestaurantModel>>
        get() = _restaurantListLiveData

    override fun fetchData(): Job = viewModelScope.launch {
        val restaurantList = restaurantRepository.getList(restaurantCategory, locationLatLngEntity)
        _restaurantListLiveData.value = restaurantList.map {
            RestaurantModel(
                id = it.id,
                restaurantCategorys = it.restaurantCategorys,
                restaurantTitle = it.restaurantTitle,
                restaurantImageUrl = it.restaurantImageUrl,
                grade = it.grade,
                reviewCount = it.reviewCount,
                keywords = it.keywords,
                deliveryTimeRange = it.deliveryTimeRange,
                deliveryTipRange = it.deliveryTipRange
            )
        }
    }


}
