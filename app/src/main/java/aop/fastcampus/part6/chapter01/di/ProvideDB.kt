package aop.fastcampus.part6.chapter01.di

import android.content.Context
import androidx.room.Room
import aop.fastcampus.part6.chapter01.data.db.ApplicationDatabase

fun provideDB(context: Context): ApplicationDatabase =
    Room.databaseBuilder(context, ApplicationDatabase::class.java, ApplicationDatabase.DB_NAME).build()

fun provideLocationDao(database: ApplicationDatabase) = database.LocationDao()

fun provideFoodMenuBasketDao(database: ApplicationDatabase) = database.FoodMenuBasketDao()

fun provideRestaurantDao(database: ApplicationDatabase) = database.RestaurantDao()

