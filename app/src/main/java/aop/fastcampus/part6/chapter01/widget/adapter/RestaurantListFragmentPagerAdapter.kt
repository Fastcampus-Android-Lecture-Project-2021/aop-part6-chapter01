package aop.fastcampus.part6.chapter01.widget.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import aop.fastcampus.part6.chapter01.data.entity.locaion.LocationLatLngEntity
import aop.fastcampus.part6.chapter01.screen.main.restaurant.RestaurantListFragment

class RestaurantListFragmentPagerAdapter : FragmentStateAdapter {

    val fragmentList: List<RestaurantListFragment>

    val locationLatLng: LocationLatLngEntity

    constructor(
        fragment: Fragment,
        fragmentList: List<RestaurantListFragment>,
        locationLatLng: LocationLatLngEntity
    ): super(fragment) {
        this.fragmentList = fragmentList
        this.locationLatLng = locationLatLng
    }

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}
