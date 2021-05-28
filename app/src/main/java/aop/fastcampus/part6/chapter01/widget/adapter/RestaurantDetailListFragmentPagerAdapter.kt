package aop.fastcampus.part6.chapter01.widget.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import aop.fastcampus.part6.chapter01.data.entity.locaion.LocationLatLngEntity
import aop.fastcampus.part6.chapter01.screen.main.restaurant.RestaurantListFragment

class RestaurantDetailListFragmentPagerAdapter : FragmentStateAdapter {

    private val fragmentList: List<Fragment>

    constructor(
        activity: FragmentActivity,
        fragmentList: List<Fragment>
    ): super(activity) {
        this.fragmentList = fragmentList
    }

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}
