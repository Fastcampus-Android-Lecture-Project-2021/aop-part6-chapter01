package aop.fastcampus.part6.chapter01.data.repository.restaurant

import aop.fastcampus.part6.chapter01.data.entity.RestaurantEntity
import aop.fastcampus.part6.chapter01.screen.restaurant.RestaurantCategory

class DefaultRestaurantRepository : RestaurantRepository {

    override fun getList(restaurantCategory: RestaurantCategory): List<RestaurantEntity> = when (restaurantCategory) {
        RestaurantCategory.ALL -> {
            listOf(
                RestaurantEntity(
                    id = 0,
                    restaurantCategorys = listOf(RestaurantCategory.ALL, RestaurantCategory.KOREAN_FOOD),
                    restaurantTitle = "마포화로집",
                    restaurantImageUrl = "https://picsum.photos/200",
                    grade = (1 until 5).random() + ((0..10).random() / 10f),
                    reviewCount = (0 until 200).random(),
                    keywords = listOf("블라블라"),
                    deliveryTimeRange = Pair(0, 20),
                    deliveryTipRange = Pair(0, 2000)
                ),
                RestaurantEntity(
                    id = 1,
                    restaurantCategorys = listOf(RestaurantCategory.ALL, RestaurantCategory.KOREAN_FOOD),
                    restaurantTitle = "옛날우동&덮밥",
                    restaurantImageUrl = "https://picsum.photos/200",
                    grade = (1 until 5).random() + ((0..10).random() / 10f),
                    reviewCount = (0 until 200).random(),
                    keywords = listOf("블라블라"),
                    deliveryTimeRange = Pair(0, 20),
                    deliveryTipRange = Pair(0, 2000)
                ),
                RestaurantEntity(
                    id = 2,
                    restaurantCategorys = listOf(RestaurantCategory.ALL, RestaurantCategory.KOREAN_FOOD),
                    restaurantTitle = "마스터석쇠불고기&냉면plus",
                    restaurantImageUrl = "https://picsum.photos/200",
                    grade = (1 until 5).random() + ((0..10).random() / 10f),
                    reviewCount = (0 until 200).random(),
                    keywords = listOf("블라블라"),
                    deliveryTimeRange = Pair(0, 20),
                    deliveryTipRange = Pair(0, 2000)
                ),
                RestaurantEntity(
                    id = 3,
                    restaurantCategorys = listOf(RestaurantCategory.ALL, RestaurantCategory.KOREAN_FOOD),
                    restaurantTitle = "마스터통삼겹",
                    restaurantImageUrl = "https://picsum.photos/200",
                    grade = (1 until 5).random() + ((0..10).random() / 10f),
                    reviewCount = (0 until 200).random(),
                    keywords = listOf("블라블라"),
                    deliveryTimeRange = Pair(0, 20),
                    deliveryTipRange = Pair(0, 2000)
                ),
                RestaurantEntity(
                    id = 4,
                    restaurantCategorys = listOf(RestaurantCategory.ALL, RestaurantCategory.KOREAN_FOOD),
                    restaurantTitle = "창영이 족발&보쌈",
                    restaurantImageUrl = "https://picsum.photos/200",
                    grade = (1 until 5).random() + ((0..10).random() / 10f),
                    reviewCount = (0 until 200).random(),
                    keywords = listOf("블라블라"),
                    deliveryTimeRange = Pair(0, 20),
                    deliveryTipRange = Pair(0, 2000)
                ),
                RestaurantEntity(
                    id = 5,
                    restaurantCategorys = listOf(RestaurantCategory.ALL, RestaurantCategory.KOREAN_FOOD),
                    restaurantTitle = "콩나물국밥&코다리조림 콩심 인천논현점",
                    restaurantImageUrl = "https://picsum.photos/200",
                    grade = (1 until 5).random() + ((0..10).random() / 10f),
                    reviewCount = (0 until 200).random(),
                    keywords = listOf("블라블라"),
                    deliveryTimeRange = Pair(0, 20),
                    deliveryTipRange = Pair(0, 2000)
                ),
                RestaurantEntity(
                    id = 6,
                    restaurantCategorys = listOf(RestaurantCategory.ALL, RestaurantCategory.KOREAN_FOOD),
                    restaurantTitle = "김여사 칼국수&냉면 논현점",
                    restaurantImageUrl = "https://picsum.photos/200",
                    grade = (1 until 5).random() + ((0..10).random() / 10f),
                    reviewCount = (0 until 200).random(),
                    keywords = listOf("블라블라"),
                    deliveryTimeRange = Pair(0, 20),
                    deliveryTipRange = Pair(0, 2000)
                ),
                RestaurantEntity(
                    id = 7,
                    restaurantCategorys = listOf(RestaurantCategory.ALL, RestaurantCategory.KOREAN_FOOD),
                    restaurantTitle = "돈키호테",
                    restaurantImageUrl = "https://picsum.photos/200",
                    grade = (1 until 5).random() + ((0..10).random() / 10f),
                    reviewCount = (0 until 200).random(),
                    keywords = listOf("블라블라"),
                    deliveryTimeRange = Pair(0, 20),
                    deliveryTipRange = Pair(0, 2000)
                ),
            )
        }
        RestaurantCategory.KOREAN_FOOD -> {
            listOf(
                RestaurantEntity(
                    id = 0,
                    restaurantCategorys = listOf(RestaurantCategory.ALL, RestaurantCategory.KOREAN_FOOD),
                    restaurantTitle = "마포화로집",
                    restaurantImageUrl = "https://picsum.photos/200",
                    grade = (1 until 5).random() + ((0..10).random() / 10f),
                    reviewCount = (0 until 200).random(),
                    keywords = listOf("블라블라"),
                    deliveryTimeRange = Pair(0, 20),
                    deliveryTipRange = Pair(0, 2000)
                ),
                RestaurantEntity(
                    id = 1,
                    restaurantCategorys = listOf(RestaurantCategory.ALL, RestaurantCategory.KOREAN_FOOD),
                    restaurantTitle = "옛날우동&덮밥",
                    restaurantImageUrl = "https://picsum.photos/200",
                    grade = (1 until 5).random() + ((0..10).random() / 10f),
                    reviewCount = (0 until 200).random(),
                    keywords = listOf("블라블라"),
                    deliveryTimeRange = Pair(0, 20),
                    deliveryTipRange = Pair(0, 2000)
                ),
                RestaurantEntity(
                    id = 2,
                    restaurantCategorys = listOf(RestaurantCategory.ALL, RestaurantCategory.KOREAN_FOOD),
                    restaurantTitle = "마스터석쇠불고기&냉면plus",
                    restaurantImageUrl = "https://picsum.photos/200",
                    grade = (1 until 5).random() + ((0..10).random() / 10f),
                    reviewCount = (0 until 200).random(),
                    keywords = listOf("블라블라"),
                    deliveryTimeRange = Pair(0, 20),
                    deliveryTipRange = Pair(0, 2000)
                ),
                RestaurantEntity(
                    id = 3,
                    restaurantCategorys = listOf(RestaurantCategory.ALL, RestaurantCategory.KOREAN_FOOD),
                    restaurantTitle = "마스터통삼겹",
                    restaurantImageUrl = "https://picsum.photos/200",
                    grade = (1 until 5).random() + ((0..10).random() / 10f),
                    reviewCount = (0 until 200).random(),
                    keywords = listOf("블라블라"),
                    deliveryTimeRange = Pair(0, 20),
                    deliveryTipRange = Pair(0, 2000)
                ),
                RestaurantEntity(
                    id = 4,
                    restaurantCategorys = listOf(RestaurantCategory.ALL, RestaurantCategory.KOREAN_FOOD),
                    restaurantTitle = "창영이 족발&보쌈",
                    restaurantImageUrl = "https://picsum.photos/200",
                    grade = (1 until 5).random() + ((0..10).random() / 10f),
                    reviewCount = (0 until 200).random(),
                    keywords = listOf("블라블라"),
                    deliveryTimeRange = Pair(0, 20),
                    deliveryTipRange = Pair(0, 2000)
                ),
                RestaurantEntity(
                    id = 5,
                    restaurantCategorys = listOf(RestaurantCategory.ALL, RestaurantCategory.KOREAN_FOOD),
                    restaurantTitle = "콩나물국밥&코다리조림 콩심 인천논현점",
                    restaurantImageUrl = "https://picsum.photos/200",
                    grade = (1 until 5).random() + ((0..10).random() / 10f),
                    reviewCount = (0 until 200).random(),
                    keywords = listOf("블라블라"),
                    deliveryTimeRange = Pair(0, 20),
                    deliveryTipRange = Pair(0, 2000)
                ),
            )
        }
        else -> {
            listOf()
        }
    }

}
