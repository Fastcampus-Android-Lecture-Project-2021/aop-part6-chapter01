package aop.fastcampus.part6.chapter01.di

import aop.fastcampus.part6.chapter01.data.entity.locaion.LocationLatLngEntity
import aop.fastcampus.part6.chapter01.data.entity.locaion.MapSearchInfoEntity
import aop.fastcampus.part6.chapter01.data.entity.restaurant.RestaurantEntity
import aop.fastcampus.part6.chapter01.data.entity.restaurant.RestaurantFoodEntity
import aop.fastcampus.part6.chapter01.data.preference.AppPreferenceManager
import aop.fastcampus.part6.chapter01.data.repository.map.DefaultMapRepository
import aop.fastcampus.part6.chapter01.data.repository.map.MapRepository
import aop.fastcampus.part6.chapter01.data.repository.order.DefaultOrderRepository
import aop.fastcampus.part6.chapter01.data.repository.order.OrderRepository
import aop.fastcampus.part6.chapter01.data.repository.restaurant.DefaultRestaurantRepository
import aop.fastcampus.part6.chapter01.data.repository.restaurant.RestaurantRepository
import aop.fastcampus.part6.chapter01.data.repository.restaurant.food.DefaultRestaurantFoodRepository
import aop.fastcampus.part6.chapter01.data.repository.restaurant.food.RestaurantFoodRepository
import aop.fastcampus.part6.chapter01.data.repository.restaurant.review.DefaultRestaurantReviewRepository
import aop.fastcampus.part6.chapter01.data.repository.restaurant.review.RestaurantReviewRepository
import aop.fastcampus.part6.chapter01.data.repository.user.DefaultUserRepository
import aop.fastcampus.part6.chapter01.data.repository.user.UserRepository
import aop.fastcampus.part6.chapter01.screen.MainViewModel
import aop.fastcampus.part6.chapter01.screen.home.HomeViewModel
import aop.fastcampus.part6.chapter01.screen.home.restaurant.RestaurantCategory
import aop.fastcampus.part6.chapter01.screen.home.restaurant.RestaurantListViewModel
import aop.fastcampus.part6.chapter01.screen.home.restaurant.detail.RestaurantDetailViewModel
import aop.fastcampus.part6.chapter01.screen.home.restaurant.detail.menu.RestaurantMenuListViewModel
import aop.fastcampus.part6.chapter01.screen.home.restaurant.detail.review.RestaurantReviewListViewModel
import aop.fastcampus.part6.chapter01.screen.like.RestaurantLikeListViewModel
import aop.fastcampus.part6.chapter01.screen.my.MyViewModel
import aop.fastcampus.part6.chapter01.screen.mylocation.MyLocationViewModel
import aop.fastcampus.part6.chapter01.screen.order.OrderMenuListViewModel
import aop.fastcampus.part6.chapter01.screen.review.gallery.GalleryPhotoRepository
import aop.fastcampus.part6.chapter01.screen.review.gallery.GalleryViewModel
import aop.fastcampus.part6.chapter01.util.event.MenuChangeEventBus
import aop.fastcampus.part6.chapter01.util.provider.DefaultResourcesProvider
import aop.fastcampus.part6.chapter01.util.provider.ResourcesProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    viewModel { MainViewModel() }
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { RestaurantLikeListViewModel(get()) }
    viewModel { MyViewModel(get(), get(), get()) }

    factory { (restaurantCategory: RestaurantCategory, locationLatLngEntity: LocationLatLngEntity) ->
        RestaurantListViewModel(restaurantCategory, locationLatLngEntity, get())
    }

    viewModel { (mapSearchInfoEntity: MapSearchInfoEntity) ->
        MyLocationViewModel(mapSearchInfoEntity, get(), get())
    }

    viewModel { (restaurantEntity: RestaurantEntity) -> RestaurantDetailViewModel(restaurantEntity, get(), get()) }

    viewModel { (restaurantId: Long, restaurantFoodList: List<RestaurantFoodEntity>) ->
        RestaurantMenuListViewModel(restaurantId, restaurantFoodList, get())
    }

    viewModel { (restaurantTitle: String) -> RestaurantReviewListViewModel(restaurantTitle, get()) }

    viewModel { OrderMenuListViewModel(get(), get(), get()) }

    viewModel { GalleryViewModel(get()) }

    single<MapRepository> { DefaultMapRepository(get(), get()) }
    single<RestaurantRepository> { DefaultRestaurantRepository(get(), get(), get()) }
    single<UserRepository> { DefaultUserRepository(get(), get(), get()) }
    single<RestaurantFoodRepository> { DefaultRestaurantFoodRepository(get(), get(), get()) }
    single<OrderRepository> { DefaultOrderRepository(get(), get()) }
    single<RestaurantReviewRepository> { DefaultRestaurantReviewRepository(get(), get()) }
    single { GalleryPhotoRepository(androidApplication()) }

    single { provideGsonConverterFactory() }
    single { buildOkHttpClient() }

    single(named("map")) { provideMapRetrofit(get(), get()) }
    single(named("food")) { provideFoodRetrofit(get(), get()) }

    single { provideMapApiService(get(qualifier = named("map"))) }
    single { provideFoodApiService(get(qualifier = named("food"))) }

    single { provideDB(androidApplication()) }
    single { provideLocationDao(get()) }
    single { provideFoodMenuBasketDao(get()) }
    single { provideRestaurantDao(get()) }

    single<ResourcesProvider> { DefaultResourcesProvider(androidApplication()) }
    single { AppPreferenceManager(androidContext()) }

    single { MenuChangeEventBus() }

    single { Dispatchers.IO }
    single { Dispatchers.Main }

    single { Firebase.firestore }
    single { Firebase.storage }
    single { FirebaseAuth.getInstance() }

}
