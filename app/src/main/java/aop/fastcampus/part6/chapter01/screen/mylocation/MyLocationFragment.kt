package aop.fastcampus.part6.chapter01.screen.mylocation

import android.os.Handler
import android.os.Looper
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import aop.fastcampus.part6.chapter01.R
import aop.fastcampus.part6.chapter01.data.entity.locaion.LocationLatLngEntity
import aop.fastcampus.part6.chapter01.databinding.FragmentMyLocationBinding
import aop.fastcampus.part6.chapter01.screen.base.BaseFragment
import aop.fastcampus.part6.chapter01.screen.main.MainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import org.koin.android.viewmodel.ext.android.viewModel

class MyLocationFragment: BaseFragment<MyLocationViewModel, FragmentMyLocationBinding>(), OnMapReadyCallback {

    override val viewModel by viewModel<MyLocationViewModel>()

    override fun getViewBinding() = FragmentMyLocationBinding.inflate(layoutInflater)

    companion object {
        const val CAMERA_ZOOM_LEVEL = 17f

        const val LOCATION_CHANGE_REQUEST_KEY = "LOCATION_CHANGE_REQUEST"
    }

    private lateinit var map: GoogleMap

    private var isMapInitialized: Boolean = false
    private var isChangingLocation: Boolean = false

    override fun initViews() = with(binding) {
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        confirmButton.setOnClickListener {
            viewModel.confirmSelectLocation()
        }
        setupGoogleMap()
    }

    private fun setupGoogleMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        this.map = map ?: return
        viewModel.fetchData()
    }

    override fun observeData() {
        super.observeData()
        viewModel.myLocationStateLiveData.observe(viewLifecycleOwner) {
            when(it) {
                is MyLocationState.Loading -> {
                    handleLoadingState()
                }
                is MyLocationState.Success -> {
                    if (::map.isInitialized) {
                        handleSuccessState(it)
                    }
                }
                is MyLocationState.Confirm -> {
                    setResultAndNavigateUp(
                        LOCATION_CHANGE_REQUEST_KEY,
                        bundleOf(
                            MainViewModel.MY_LOCATION_KEY to it.mapSearchInfoEntity
                        )
                    )
                }
            }
        }
    }

    private fun handleLoadingState() = with(binding) {
        locationLoading.isVisible = true
        locationTitleTextView.text = getString(R.string.loading)
    }

    private fun handleSuccessState(state: MyLocationState.Success) = with(binding) {
        val mapSearchInfo = state.mapSearchInfoEntity
        locationLoading.isGone = true
        locationTitleTextView.text = mapSearchInfo.fullAdress
        if (isMapInitialized.not()) {
            map.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        mapSearchInfo.locationLatLng.latitude,
                        mapSearchInfo.locationLatLng.longitude
                    ), CAMERA_ZOOM_LEVEL
                )
            )

            map.setOnCameraIdleListener {
                if (isChangingLocation.not()) {
                    isChangingLocation = true
                    Handler(Looper.getMainLooper()).postDelayed({
                        val cameraLatLng = map.cameraPosition.target
                        viewModel.changeLocationInfo(
                            LocationLatLngEntity(
                                cameraLatLng.latitude,
                                cameraLatLng.longitude
                            )
                        )
                        isChangingLocation = false
                    }, 1000)
                }
            }
            isMapInitialized = true
        }
    }


}
