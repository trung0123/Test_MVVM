package jilnesta.com.testmvvm

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import jilnesta.com.testmvvm.utils.DebugTree
import jilnesta.com.testmvvm.utils.ReleaseTree
import timber.log.Timber

@HiltAndroidApp
open class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }

}
