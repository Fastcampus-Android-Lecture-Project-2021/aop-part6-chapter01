package aop.fastcampus.part6.chapter01.screen.main.restaurant.detail

import android.content.Context
import android.content.Intent
import aop.fastcampus.part6.chapter01.data.entity.restaurant.RestaurantEntity
import aop.fastcampus.part6.chapter01.databinding.FragmentRestaurantDetailBinding
import aop.fastcampus.part6.chapter01.screen.base.BaseActivity
import aop.fastcampus.part6.chapter01.screen.main.restaurant.RestaurantListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RestaurantDetailActivity: BaseActivity<RestaurantDetailViewModel, FragmentRestaurantDetailBinding>() {

    override val viewModel by viewModel<RestaurantDetailViewModel>()

    override fun getViewBinding(): FragmentRestaurantDetailBinding = FragmentRestaurantDetailBinding.inflate(layoutInflater)

    companion object {
        fun newIntent(context: Context, restaurantEntity: RestaurantEntity) = Intent(context, RestaurantDetailActivity::class.java).apply {
            putExtra(RestaurantListViewModel.RESTAURANT_KEY, restaurantEntity)
        }
    }

    override fun observeData() {
    }

}
