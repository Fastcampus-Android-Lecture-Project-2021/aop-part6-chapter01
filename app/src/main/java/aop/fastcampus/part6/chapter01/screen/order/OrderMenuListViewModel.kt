package aop.fastcampus.part6.chapter01.screen.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import aop.fastcampus.part6.chapter01.data.repository.restaurant.food.RestaurantFoodRepository
import aop.fastcampus.part6.chapter01.model.CellType
import aop.fastcampus.part6.chapter01.model.restaurant.FoodModel
import aop.fastcampus.part6.chapter01.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OrderMenuListViewModel(
    private val restaurantFoodRepository: RestaurantFoodRepository
): BaseViewModel() {

    val orderMenuState = MutableLiveData<OrderMenuState>(OrderMenuState.Uninitialized)

    override fun fetchData(): Job = viewModelScope.launch {
        orderMenuState.value = OrderMenuState.Loading
        val foodMenuList = restaurantFoodRepository.getAllFoodMenuListInBasket()
        orderMenuState.value = OrderMenuState.Success(
            foodMenuList.map {
                FoodModel(
                    id = it.hashCode().toLong(),
                    type = CellType.ORDER_FOOD_CELL,
                    title = it.title,
                    description = it.description,
                    price = it.price,
                    imageUrl = it.imageUrl,
                    restaurantId = it.restaurantId,
                    foodId = it.id
                )
            }
        )
    }

    fun removeOrderMenu(foodModel: FoodModel) = viewModelScope.launch {
        restaurantFoodRepository.removeFoodMenuListInBasket(foodModel.foodId)
        fetchData()
    }

    fun clearOrderMenu() {

    }

}
