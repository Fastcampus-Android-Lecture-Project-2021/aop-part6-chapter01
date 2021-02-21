package aop.fastcampus.part6.chapter01.screen.restaurant

import androidx.annotation.StringRes
import aop.fastcampus.part6.chapter01.R

enum class RestaurantCategory(@StringRes val categoryNameId: Int) {
    ALL(R.string.all),
    KOREAN_FOOD(R.string.korean_food),
    DUMPLING_FOOD(R.string.dumpling_food),
    CAFE_DESSERT(R.string.cafe_dessert),
    JAPANESE_FOOD(R.string.japanese_food),
    CHINESE_FOOD(R.string.chinese_food),
    ASIAN_EUROPE_FOOD(R.string.asian_europe_food),
    FAST_FOOD(R.string.fast_food),
    MIDNIGHT_FOOD(R.string.midnight_food),
    CHICKEN_PIZZA(R.string.chicken_pizza)
}
