package aop.fastcampus.part6.chapter01.screen.main.restaurant.detail.menu

import android.os.Bundle
import aop.fastcampus.part6.chapter01.data.entity.restaurant.RestaurantFoodEntity
import aop.fastcampus.part6.chapter01.databinding.FragmentListBinding
import aop.fastcampus.part6.chapter01.model.restaurant.FoodModel
import aop.fastcampus.part6.chapter01.screen.base.BaseFragment
import aop.fastcampus.part6.chapter01.widget.adapter.ModelRecyclerAdapter
import aop.fastcampus.part6.chapter01.widget.adapter.listener.restaurant.FoodMenuListListener
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RestaurantMenuListFragment : BaseFragment<RestaurantMenuListViewModel, FragmentListBinding>() {

    override fun getViewBinding(): FragmentListBinding = FragmentListBinding.inflate(layoutInflater)

    private val restaurantFoodList by lazy { arguments?.getParcelableArrayList<RestaurantFoodEntity>(FOOD_LIST_KEY)!! }

    override val viewModel by viewModel<RestaurantMenuListViewModel> { parametersOf(restaurantFoodList) }

    private val adapter by lazy {
        ModelRecyclerAdapter<FoodModel, RestaurantMenuListViewModel>(listOf(), viewModel, adapterListener = object : FoodMenuListListener {
            override fun onClickItem(model: FoodModel) {

            }
        })
    }

    override fun initViews() = with(binding) {
        recyclerVIew.adapter = adapter
    }

    override fun observeData() {
        viewModel.restaurantMenuListLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    companion object {
        const val FOOD_LIST_KEY = "foodList"

        fun newInstance(foodList: ArrayList<RestaurantFoodEntity>): RestaurantMenuListFragment {
            val bundle = Bundle().apply {
                putParcelableArrayList(FOOD_LIST_KEY, foodList)
            }

            return RestaurantMenuListFragment().apply {
                arguments = bundle
            }
        }

    }
}
