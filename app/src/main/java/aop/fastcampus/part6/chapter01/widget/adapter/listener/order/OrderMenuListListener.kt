package aop.fastcampus.part6.chapter01.widget.adapter.listener.order

import aop.fastcampus.part6.chapter01.model.restaurant.FoodModel
import aop.fastcampus.part6.chapter01.widget.adapter.listener.AdapterListener

interface OrderMenuListListener: AdapterListener {

    fun onRemoveItem(model: FoodModel)

}
