package aop.fastcampus.part6.chapter01.screen.home

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isGone
import androidx.core.view.isVisible
import aop.fastcampus.part6.chapter01.R
import aop.fastcampus.part6.chapter01.data.entity.locaion.LocationLatLngEntity
import aop.fastcampus.part6.chapter01.data.entity.locaion.MapSearchInfoEntity
import aop.fastcampus.part6.chapter01.databinding.FragmentHomeBinding
import aop.fastcampus.part6.chapter01.screen.base.BaseFragment
import aop.fastcampus.part6.chapter01.screen.home.HomeViewModel.Companion.MY_LOCATION_KEY
import aop.fastcampus.part6.chapter01.screen.home.restaurant.RestaurantCategory
import aop.fastcampus.part6.chapter01.screen.home.restaurant.RestaurantListFragment
import aop.fastcampus.part6.chapter01.screen.home.restaurant.RestautantFilterOrder
import aop.fastcampus.part6.chapter01.screen.mylocation.MyLocationActivity
import aop.fastcampus.part6.chapter01.screen.order.OrderMenuActivity
import aop.fastcampus.part6.chapter01.widget.adapter.RestaurantListFragmentPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    companion object {

        val locationPermissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        const val TAG = "MainFragment"
    }

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override val viewModel by viewModel<HomeViewModel>()

    private lateinit var locationManager: LocationManager

    private lateinit var myLocationListener: MyLocationListener

    private lateinit var viewPagerAdapter: RestaurantListFragmentPagerAdapter

    private val changeLocationLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.getParcelableExtra<MapSearchInfoEntity>(MY_LOCATION_KEY)?.let { myLocationInfo ->
                viewModel.loadReverseGeoInformation(myLocationInfo.locationLatLng)
            }
        }
    }

    private val locationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val responsePermissions = permissions.entries.filter {
                it.key == Manifest.permission.ACCESS_FINE_LOCATION
                    || it.key == Manifest.permission.ACCESS_COARSE_LOCATION
            }
            if (responsePermissions.filter { it.value == true }.size == locationPermissions.size) {
                setMyLocationListener()
            } else {
                with(binding.locationTitleTextView) {
                    text = getString(R.string.please_request_location_permission)
                    setOnClickListener {
                        getMyLocation()
                    }
                }
                Toast.makeText(requireContext(), getString(R.string.can_not_assigned_permission), Toast.LENGTH_SHORT).show()
            }
        }

    override fun initViews() = with(binding) {
        locationTitleTextView.setOnClickListener {
            viewModel.getMapSearchInfo()?.let { mapInfo ->
                changeLocationLauncher.launch(
                    MyLocationActivity.newIntent(
                        requireContext(), mapInfo
                    )
                )
            }
        }
        filterChipGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.chipDefault -> {
                    chipInitialize.isGone = true
                    changeRestaurantFilterOrder(RestautantFilterOrder.DEFAULT)
                }
                R.id.chipInitialize -> {
                    chipDefault.isChecked = true
                }
                R.id.chipDeliveryTip -> {
                    chipInitialize.isVisible = true
                    changeRestaurantFilterOrder(RestautantFilterOrder.LOW_DELIVERY_TIP)
                }
                R.id.chipFastDelivery -> {
                    chipInitialize.isVisible = true
                    changeRestaurantFilterOrder(RestautantFilterOrder.FAST_DELIVERY)
                }
                R.id.chipTopRate -> {
                    chipInitialize.isVisible = true
                    changeRestaurantFilterOrder(RestautantFilterOrder.TOP_RATE)
                }
            }
        }
    }

    private fun changeRestaurantFilterOrder(order: RestautantFilterOrder) {
        viewPagerAdapter.fragmentList.forEach {
            it.viewModel.setRestaurantFilterOrder(order)
        }
    }

    private fun getMyLocation() {
        if (::locationManager.isInitialized.not()) {
            locationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        }
        val isGpsEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (isGpsEnable) {
            locationPermissionLauncher.launch(locationPermissions)
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

    override fun observeData() {
        viewModel.mainStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is HomeState.Uninitialized -> {
                    getMyLocation()
                }
                is HomeState.Loading -> {
                    binding.locationLoading.isVisible = true
                    binding.locationTitleTextView.text = getString(R.string.loading)
                }
                is HomeState.Success -> {
                    binding.locationLoading.isGone = true
                    binding.locationTitleTextView.text = it.mapSearchInfoEntity.fullAdress
                    initViewPager(it.mapSearchInfoEntity.locationLatLng)
                    if (it.isLocationSame.not()) {
                        Toast.makeText(requireContext(), "위치가 맞는지 확인해주세요!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        viewModel.foodMenuBasketLiveData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.basketButtonContainer.isVisible = true
                binding.basketCountTextView.text = getString(R.string.basket_count, it.size)
                binding.basketButton.setOnClickListener {
                    startActivity(
                        OrderMenuActivity.newIntent(requireActivity())
                    )
                }
            } else {
                binding.basketButtonContainer.isGone = true
                binding.basketButton.setOnClickListener(null)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.checkMyBasket()
    }

    private fun initViewPager(locationLatLng: LocationLatLngEntity) = with(binding) {
        filterChipGroup.isVisible = true
        val restaurantCategories = RestaurantCategory.values()
        if (::viewPagerAdapter.isInitialized.not()) {
            val restaurantListFragmentList = restaurantCategories.map {
                RestaurantListFragment.newInstance(it, locationLatLng)
            }
            viewPagerAdapter = RestaurantListFragmentPagerAdapter(
                this@HomeFragment,
                restaurantListFragmentList,
                locationLatLng
            )
            viewPager.adapter = viewPagerAdapter
        }
        if (locationLatLng != viewPagerAdapter.locationLatLng) {
            viewPagerAdapter.fragmentList.forEach {
                it.viewModel.setLocationLatLng(locationLatLng)
            }
        }
        viewPager.offscreenPageLimit = restaurantCategories.size
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
