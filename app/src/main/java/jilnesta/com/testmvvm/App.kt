package jilnesta.com.testmvvm

import android.app.Application
import com.facebook.stetho.Stetho
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import jilnesta.com.testmvvm.utils.DebugTree
import jilnesta.com.testmvvm.utils.ReleaseTree
import timber.log.Timber

@HiltAndroidApp
open class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }

}
