package jilnesta.com.testmvvm.utils

import timber.log.Timber

class DebugTree : Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String {
        return String.format(
            "C:%s:%s",
            super.createStackElementTag(element),
            element.lineNumber
        )
    }
}