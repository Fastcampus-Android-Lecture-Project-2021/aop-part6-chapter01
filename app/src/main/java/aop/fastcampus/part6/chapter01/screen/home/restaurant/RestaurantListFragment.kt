package aop.fastcampus.part6.chapter01.screen.home.restaurant

import android.os.Bundle
import aop.fastcampus.part6.chapter01.data.entity.locaion.LocationLatLngEntity
import aop.fastcampus.part6.chapter01.databinding.FragmentListBinding
import aop.fastcampus.part6.chapter01.model.restaurant.RestaurantModel
import aop.fastcampus.part6.chapter01.screen.base.BaseFragment
import aop.fastcampus.part6.chapter01.screen.home.restaurant.detail.RestaurantDetailActivity
import aop.fastcampus.part6.chapter01.widget.adapter.ModelRecyclerAdapter
import aop.fastcampus.part6.chapter01.widget.adapter.listener.restaurant.RestaurantListListener
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RestaurantListFragment : BaseFragment<RestaurantListViewModel, FragmentListBinding>() {

    override fun getViewBinding(): FragmentListBinding = FragmentListBinding.inflate(layoutInflater)

    private val restaurantCategory by lazy { arguments?.getSerializable(RESTAURANT_CATEGORY_KEY) as RestaurantCategory }
    private val locationLatLngEntity by lazy<LocationLatLngEntity> { arguments?.getParcelable(LOCATION_KEY)!! }

    override val viewModel by viewModel<RestaurantListViewModel> { parametersOf(restaurantCategory, locationLatLngEntity) }

    private val adapter by lazy {
        ModelRecyclerAdapter<RestaurantModel, RestaurantListViewModel>(listOf(), viewModel, adapterListener = object : RestaurantListListener {
            override fun onClickItem(model: RestaurantModel) {
                startActivity(
                    RestaurantDetailActivity.newIntent(requireContext(), model.toEntity())
                )
            }
        })
    }

    override fun initViews() = with(binding) {
        recyclerVIew.adapter = adapter
    }

    override fun observeData() {
        viewModel.restaurantListLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    companion object {
        const val RESTAURANT_KEY = "Restaurant"
        const val RESTAURANT_CATEGORY_KEY = "restaurantCategory"
        const val LOCATION_KEY = "location"

        fun newInstance(restaurantCategory: RestaurantCategory, locationLatLng: LocationLatLngEntity): RestaurantListFragment {
            val bundle = Bundle().apply {
                putSerializable(RESTAURANT_CATEGORY_KEY, restaurantCategory)
                putParcelable(LOCATION_KEY, locationLatLng)
            }

            return RestaurantListFragment().apply {
                arguments = bundle
            }
        }

    }
}
