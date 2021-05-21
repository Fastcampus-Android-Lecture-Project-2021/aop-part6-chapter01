package aop.fastcampus.part6.chapter01.di

import aop.fastcampus.part6.chapter01.data.entity.locaion.LocationLatLngEntity
import aop.fastcampus.part6.chapter01.data.repository.map.DefaultMapRepository
import aop.fastcampus.part6.chapter01.data.repository.map.MapRepository
import aop.fastcampus.part6.chapter01.data.repository.restaurant.DefaultRestaurantRepository
import aop.fastcampus.part6.chapter01.data.repository.restaurant.RestaurantRepository
import aop.fastcampus.part6.chapter01.screen.main.MainViewModel
import aop.fastcampus.part6.chapter01.screen.main.restaurant.RestaurantCategory
import aop.fastcampus.part6.chapter01.screen.main.restaurant.RestaurantListViewModel
import aop.fastcampus.part6.chapter01.util.provider.DefaultResourcesProvider
import aop.fastcampus.part6.chapter01.util.provider.ResourcesProvider
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { MainViewModel(get()) }

    factory { (restaurantCategory: RestaurantCategory, locationLatLngEntity: LocationLatLngEntity) ->
        RestaurantListViewModel(restaurantCategory, locationLatLngEntity, get())
    }

    single<MapRepository> { DefaultMapRepository(get(), get()) }
    single<RestaurantRepository> { DefaultRestaurantRepository(get(), get(), get()) }

    single { provideGsonConverterFactory() }

    single { buildOkHttpClient() }

    single { provideMapRetrofit(get(), get()) }

    single { provideMapApiService(get()) }

    single<ResourcesProvider> { DefaultResourcesProvider(androidApplication()) }

    single { Dispatchers.IO }
    single { Dispatchers.Main }

}
