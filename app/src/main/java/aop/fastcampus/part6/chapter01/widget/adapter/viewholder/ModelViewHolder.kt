package aop.fastcampus.part6.chapter01.widget.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import aop.fastcampus.part6.chapter01.model.Model
import aop.fastcampus.part6.chapter01.screen.restaurant.ModelListViewModel
import aop.fastcampus.part6.chapter01.util.provider.ResourcesProvider

abstract class ModelViewHolder<M: Model>(
    binding: ViewBinding,
    protected val viewModel: ModelListViewModel,
    protected val resourcesProvider: ResourcesProvider
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun reset()

    open fun bindData(model: M) {
        reset()
    }

    abstract fun bindViews(model: M)

}
