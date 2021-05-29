package aop.fastcampus.part6.chapter01.widget.adapter.viewholder.food

import aop.fastcampus.part6.chapter01.R
import aop.fastcampus.part6.chapter01.databinding.ViewholderFoodMenuBinding
import aop.fastcampus.part6.chapter01.extensions.clear
import aop.fastcampus.part6.chapter01.extensions.load
import aop.fastcampus.part6.chapter01.model.restaurant.FoodModel
import aop.fastcampus.part6.chapter01.screen.base.BaseViewModel
import aop.fastcampus.part6.chapter01.util.provider.ResourcesProvider
import aop.fastcampus.part6.chapter01.widget.adapter.listener.AdapterListener
import aop.fastcampus.part6.chapter01.widget.adapter.listener.restaurant.FoodMenuListListener
import aop.fastcampus.part6.chapter01.widget.adapter.viewholder.ModelViewHolder
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class FoodMenuViewHolder(
    private val binding: ViewholderFoodMenuBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider
): ModelViewHolder<FoodModel>(binding, viewModel, resourcesProvider) {

    override fun reset() = with(binding) {
        foodImage.clear()
    }

    override fun bindData(model: FoodModel) {
        super.bindData(model)
        with(binding) {
            foodImage.load(model.imageUrl, 24f, CenterCrop())
            foodTitleText.text = model.title
            foodDescriptionText.text = model.description
            priceText.text = resourcesProvider.getString(R.string.price, model.price)
        }
    }

    override fun bindViews(model: FoodModel, adapterListener: AdapterListener) {
        if (adapterListener is FoodMenuListListener) {
            binding.root.setOnClickListener {
                adapterListener.onClickItem(model)
            }
        }
    }

}
