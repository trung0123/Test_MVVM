package jilnesta.com.testmvvm.utils

import android.util.Log
import timber.log.Timber

class ReleaseTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.ERROR || priority == Log.WARN) {
//            YourCrashLibrary.log(priority, tag, message);
            return
        }
    }
}