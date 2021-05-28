package aop.fastcampus.part6.chapter01.util.mapper

import android.view.LayoutInflater
import android.view.ViewGroup
import aop.fastcampus.part6.chapter01.databinding.ViewholderFoodMenuBinding
import aop.fastcampus.part6.chapter01.model.CellType
import aop.fastcampus.part6.chapter01.databinding.ViewholderRestaurantBinding
import aop.fastcampus.part6.chapter01.model.Model
import aop.fastcampus.part6.chapter01.screen.base.BaseViewModel
import aop.fastcampus.part6.chapter01.util.provider.ResourcesProvider
import aop.fastcampus.part6.chapter01.widget.adapter.viewholder.ModelViewHolder
import aop.fastcampus.part6.chapter01.widget.adapter.viewholder.food.FoodMenuViewHolder
import aop.fastcampus.part6.chapter01.widget.adapter.viewholder.restaurant.RestaurantViewHolder

object ModelViewHolderMapper {

    @Suppress("UNCHECKED_CAST")
    fun <M: Model> map(
        parent: ViewGroup,
        type: CellType,
        viewModel: BaseViewModel,
        resourcesProvider: ResourcesProvider
    ): ModelViewHolder<M> {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = when (type) {
            CellType.RESTAURANT_CELL ->
                RestaurantViewHolder(
                    ViewholderRestaurantBinding.inflate(inflater, parent, false),
                    viewModel,
                    resourcesProvider
                )
            CellType.FOOD_CELL ->
                FoodMenuViewHolder(
                    ViewholderFoodMenuBinding.inflate(inflater, parent, false),
                    viewModel,
                    resourcesProvider
                )
        }

        return viewHolder as ModelViewHolder<M>
    }

}
