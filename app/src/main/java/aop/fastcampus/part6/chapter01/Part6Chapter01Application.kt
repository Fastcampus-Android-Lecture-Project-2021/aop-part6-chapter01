package aop.fastcampus.part6.chapter01

import android.app.Application
import android.content.Context

class Part6Chapter01Application: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
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
