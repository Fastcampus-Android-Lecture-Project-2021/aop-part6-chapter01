package aop.fastcampus.part6.chapter01.screen.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import aop.fastcampus.part6.chapter01.data.repository.restaurant.DefaultRestaurantRepository
import aop.fastcampus.part6.chapter01.data.repository.restaurant.RestaurantRepository
import aop.fastcampus.part6.chapter01.model.restaurant.RestaurantModel

class RestaurantListViewModel: ModelListViewModel() {

    private val defaultRestaurantRepository: RestaurantRepository = DefaultRestaurantRepository()

    private var _restaurantListLiveData = MutableLiveData<List<RestaurantModel>>()
    val restaurantListLiveData: LiveData<List<RestaurantModel>>
        get() = _restaurantListLiveData

    fun getRestaurantList(restaurantCategory: RestaurantCategory) {

        val restaurantList = defaultRestaurantRepository.getList(restaurantCategory)
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
