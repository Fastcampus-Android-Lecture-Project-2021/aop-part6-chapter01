package aop.fastcampus.part6.chapter01.screen.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isGone
import androidx.core.view.isVisible
import aop.fastcampus.part6.chapter01.R
import aop.fastcampus.part6.chapter01.data.entity.locaion.LocationLatLngEntity
import aop.fastcampus.part6.chapter01.databinding.FragmentMainBinding
import aop.fastcampus.part6.chapter01.screen.base.BaseFragment
import aop.fastcampus.part6.chapter01.screen.main.restaurant.RestaurantCategory
import aop.fastcampus.part6.chapter01.screen.main.restaurant.RestaurantListFragment
import aop.fastcampus.part6.chapter01.widget.adapter.RestaurantListFragmentPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.inject

class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {

    companion object {
        const val SEARCH_RESULT_EXTRA_KEY = "SearchResult"

        val locationPermissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

    override fun getViewBinding(): FragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)

    override val viewModel by inject<MainViewModel>()

    private lateinit var locationManager: LocationManager

    private lateinit var myLocationListener: MyLocationListener

    private val locationPermissionLauncer =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val responsePermissions = permissions.entries.filter {
                it.key == Manifest.permission.ACCESS_FINE_LOCATION
                    || it.key == Manifest.permission.ACCESS_COARSE_LOCATION
            }
            if (responsePermissions.filter { it.value == true }.size == locationPermissions.size) {
                setMyLocationListener()
            } else {
                Toast.makeText(requireContext(), "권한을 받지 못했습니다.", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        getMyLocation()
    }

    private fun initViews() = with(binding) {

    }

    private fun getMyLocation() {
        if (::locationManager.isInitialized.not()) {
            locationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        }
        val isGpsEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (isGpsEnable) {
            locationPermissionLauncer.launch(locationPermissions)
        }
    }

    @SuppressLint("MissingPermission")
    private fun setMyLocationListener() {
        val minTime: Long = 1500
        val minDistance = 100f
        if (::myLocationListener.isInitialized.not()) {
            myLocationListener = MyLocationListener()
        }
        with(locationManager) {
            requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                minTime, minDistance, myLocationListener
            )
            requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                minTime, minDistance, myLocationListener
            )
        }
    }

    override fun observeData() = viewModel.mainStateLiveData.observe(this) {
        when (it) {
            is MainState.Uninitialized -> {

            }
            is MainState.Loading -> {
                binding.locationLoading.isVisible = true
                binding.locationTitleTextView.text = getString(R.string.loading)
            }
            is MainState.Success -> {
                binding.locationLoading.isGone = true
                binding.locationTitleTextView.text = it.mapSearchInfoEntity.fullAdress
                initViewPager(it.mapSearchInfoEntity.locationLatLng)
            }
        }
    }

    private fun initViewPager(locationLatLng: LocationLatLngEntity) = with(binding) {
        val restaurantListFragmentList = RestaurantCategory.values().map {
            RestaurantListFragment.newInstance(it, locationLatLng)
        }

        viewPager.adapter = RestaurantListFragmentPagerAdapter(
            this@MainFragment,
            restaurantListFragmentList
        )
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setText(RestaurantCategory.values()[position].categoryNameId)
        }.attach()
    }

    private fun removeLocationListener() {
        if (::locationManager.isInitialized && ::myLocationListener.isInitialized) {
            locationManager.removeUpdates(myLocationListener)
        }
    }

    inner class MyLocationListener : LocationListener {

        override fun onLocationChanged(location: Location) {
            viewModel.loadReverseGeoInformation(
                LocationLatLngEntity(
                    location.latitude,
                    location.longitude
                )
            )
            removeLocationListener()
        }

    }

}
