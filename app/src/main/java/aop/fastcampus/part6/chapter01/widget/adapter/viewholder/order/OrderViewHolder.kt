package aop.fastcampus.part6.chapter01.widget.adapter.viewholder.order

import aop.fastcampus.part6.chapter01.R
import aop.fastcampus.part6.chapter01.databinding.ViewholderOrderBinding
import aop.fastcampus.part6.chapter01.model.order.OrderModel
import aop.fastcampus.part6.chapter01.screen.base.BaseViewModel
import aop.fastcampus.part6.chapter01.util.provider.ResourcesProvider
import aop.fastcampus.part6.chapter01.widget.adapter.listener.AdapterListener
import aop.fastcampus.part6.chapter01.widget.adapter.viewholder.ModelViewHolder

class OrderViewHolder(
    private val binding: ViewholderOrderBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider
): ModelViewHolder<OrderModel>(binding, viewModel, resourcesProvider) {

    override fun reset() = Unit

    override fun bindData(model: OrderModel) {
        super.bindData(model)
        with(binding) {
            orderTitleText.text = resourcesProvider.getString(R.string.order_history_title, model.orderId)

            val foodMenuList = model.foodMenuList

            orderContentText.text = foodMenuList.toString()
            orderTotalPriceText.text = resourcesProvider.getString(R.string.price, foodMenuList.map { it.price }.reduce { total, price -> total + price })
        }
    }

    override fun bindViews(model: OrderModel, adapterListener: AdapterListener) = Unit

}
