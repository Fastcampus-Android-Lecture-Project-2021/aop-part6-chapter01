package aop.fastcampus.part6.chapter01

import android.app.Application
import android.content.Context
import aop.fastcampus.part6.chapter01.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Part6Chapter01Application: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@Part6Chapter01Application)
            modules(appModule)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        appContext = null
    }

    companion object {

        var appContext: Context? = null
            private set

    }

}
