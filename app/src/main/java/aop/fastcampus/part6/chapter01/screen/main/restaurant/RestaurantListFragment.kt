package aop.fastcampus.part6.chapter01.screen.main.restaurant

import android.os.Bundle
import android.view.View
import aop.fastcampus.part6.chapter01.data.entity.locaion.LocationLatLngEntity
import aop.fastcampus.part6.chapter01.databinding.FragmentRestaurantListBinding
import aop.fastcampus.part6.chapter01.model.restaurant.RestaurantModel
import aop.fastcampus.part6.chapter01.screen.base.BaseFragment
import aop.fastcampus.part6.chapter01.widget.adapter.ModelRecyclerAdapter
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RestaurantListFragment : BaseFragment<RestaurantListViewModel, FragmentRestaurantListBinding>() {

    override fun getViewBinding(): FragmentRestaurantListBinding = FragmentRestaurantListBinding.inflate(layoutInflater)

    private val restaurantCategory by lazy { arguments?.getSerializable(RESTAURANT_CATEGORY_KEY) as RestaurantCategory }
    private val locationLatLngEntity by lazy<LocationLatLngEntity> { arguments?.getParcelable(LOCATION_KEY)!! }

    override val viewModel by inject<RestaurantListViewModel> { parametersOf(restaurantCategory, locationLatLngEntity) }

    private val adapter by lazy { ModelRecyclerAdapter<RestaurantModel, RestaurantListViewModel>(listOf(), viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeData()
    }

    private fun initViews() = with(binding) {
        restaurantRecyclerVIew.adapter = adapter
    }

    override fun observeData() {
        viewModel.restaurantListLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    companion object {
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
