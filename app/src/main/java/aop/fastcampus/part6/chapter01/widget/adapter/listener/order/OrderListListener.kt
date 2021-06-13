package aop.fastcampus.part6.chapter01.widget.adapter.listener.order

import aop.fastcampus.part6.chapter01.widget.adapter.listener.AdapterListener

interface OrderListListener: AdapterListener {

    fun writeRestaurantReview(orderId: String, restaurantTitle: String)

}
