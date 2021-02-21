package aop.fastcampus.part6.chapter01.util.mapper

import android.view.LayoutInflater
import android.view.ViewGroup
import aop.fastcampus.part6.chapter01.data.CellType
import aop.fastcampus.part6.chapter01.databinding.ViewholderRestaurantBinding
import aop.fastcampus.part6.chapter01.model.Model
import aop.fastcampus.part6.chapter01.screen.restaurant.ModelListViewModel
import aop.fastcampus.part6.chapter01.util.provider.ResourcesProvider
import aop.fastcampus.part6.chapter01.widget.adapter.viewholder.ModelViewHolder
import aop.fastcampus.part6.chapter01.widget.adapter.viewholder.restaurant.RestaurantViewHolder

internal object ModelViewHolderMapper {

    @Suppress("UNCHECKED_CAST")
    fun <M: Model> map(
        parent: ViewGroup,
        type: CellType,
        viewModel: ModelListViewModel,
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
        }

        return viewHolder as ModelViewHolder<M>
    }

}
