package aop.fastcampus.part6.chapter01.screen.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import aop.fastcampus.part6.chapter01.databinding.FragmentRestaurantListBinding
import aop.fastcampus.part6.chapter01.model.restaurant.RestaurantModel
import aop.fastcampus.part6.chapter01.widget.adapter.ModelRecyclerAdapter

class RestaurantListFragment : Fragment() {

    private var binding: FragmentRestaurantListBinding? = null
    private val _binding get() = binding!!

    private val viewModel = RestaurantListViewModel()
    private val restaurantCategory by lazy { arguments?.getSerializable(RESTAURANT_CATEGORY_KEY) as RestaurantCategory }

    private val adapter by lazy { ModelRecyclerAdapter<RestaurantModel, RestaurantListViewModel>(listOf(), viewModel) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRestaurantListBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getRestaurantList(restaurantCategory)
        initViews()
        observeData()
    }

    private fun initViews() = with(_binding) {
        restaurantRecyclerVIew.adapter = adapter
    }

    private fun observeData() = with(_binding) {
        viewModel.restaurantListLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        const val RESTAURANT_CATEGORY_KEY = "restaurantCategory"

        fun newInstance(restaurantCategory: RestaurantCategory): RestaurantListFragment {
            val bundle = Bundle().apply {
                putSerializable(RESTAURANT_CATEGORY_KEY, restaurantCategory)
            }

            return RestaurantListFragment().apply {
                arguments = bundle
            }
        }

    }
}
