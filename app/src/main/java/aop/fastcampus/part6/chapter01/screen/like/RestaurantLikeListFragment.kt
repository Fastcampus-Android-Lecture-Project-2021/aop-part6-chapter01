package aop.fastcampus.part6.chapter01.screen.like

import androidx.core.view.isGone
import androidx.core.view.isVisible
import aop.fastcampus.part6.chapter01.databinding.FragmentRestaurantLikeListBinding
import aop.fastcampus.part6.chapter01.model.restaurant.RestaurantModel
import aop.fastcampus.part6.chapter01.screen.base.BaseFragment
import aop.fastcampus.part6.chapter01.screen.home.restaurant.detail.RestaurantDetailActivity
import aop.fastcampus.part6.chapter01.widget.adapter.ModelRecyclerAdapter
import aop.fastcampus.part6.chapter01.widget.adapter.listener.restaurant.RestaurantLikeListListener
import org.koin.android.viewmodel.ext.android.viewModel

class RestaurantLikeListFragment: BaseFragment<RestaurantLikeListViewModel, FragmentRestaurantLikeListBinding>() {

    override fun getViewBinding(): FragmentRestaurantLikeListBinding = FragmentRestaurantLikeListBinding.inflate(layoutInflater)

    override val viewModel by viewModel<RestaurantLikeListViewModel>()

    private var isFirstShown = false

    private val adapter by lazy {
        ModelRecyclerAdapter<RestaurantModel, RestaurantLikeListViewModel>(listOf(), viewModel, adapterListener = object : RestaurantLikeListListener {

            override fun onDislikeItem(model: RestaurantModel) {
                viewModel.dislikeRestaurant(model.toEntity())
            }

            override fun onClickItem(model: RestaurantModel) {
                startActivity(
                    RestaurantDetailActivity.newIntent(requireContext(), model.toEntity())
                )
            }
        })
    }

    override fun onResume() {
        super.onResume()
        if (isFirstShown.not()) {
            isFirstShown = true
        } else {
            viewModel.fetchData()
        }
    }

    override fun initViews() = with(binding) {
        recyclerView.adapter = adapter
    }

    override fun observeData() {
        viewModel.restaurantListLiveData.observe(viewLifecycleOwner) {
            checkListEmpty(it)
        }
    }

    private fun checkListEmpty(restaurantList: List<RestaurantModel>) {
        val isEmpty = restaurantList.isEmpty()
        binding.recyclerView.isGone = isEmpty
        binding.emptyResultTextView.isVisible = isEmpty
        if (isEmpty.not()) {
            adapter.submitList(restaurantList)
        }
    }

    companion object {

        fun newInstance() = RestaurantLikeListFragment()

        const val TAG = "likeFragment"

    }

}
