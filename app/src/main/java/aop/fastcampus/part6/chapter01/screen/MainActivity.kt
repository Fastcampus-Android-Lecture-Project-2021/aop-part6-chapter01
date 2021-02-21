package aop.fastcampus.part6.chapter01.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import aop.fastcampus.part6.chapter01.databinding.ActivityMainBinding
import aop.fastcampus.part6.chapter01.screen.restaurant.RestaurantCategory
import aop.fastcampus.part6.chapter01.screen.restaurant.RestaurantListFragment
import aop.fastcampus.part6.chapter01.widget.adapter.RestaurantListFragmentPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initViewPager()
    }

    private fun initViews() = with(binding) {

    }

    private fun initViewPager() = with(binding) {
        val restaurantListFragmentList = RestaurantCategory.values().map {
            RestaurantListFragment.newInstance(it)
        }

        viewPager.adapter =
            RestaurantListFragmentPagerAdapter(
                this@MainActivity,
                restaurantListFragmentList
            )
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setText(RestaurantCategory.values()[position].categoryNameId)
        }.attach()
    }


}
