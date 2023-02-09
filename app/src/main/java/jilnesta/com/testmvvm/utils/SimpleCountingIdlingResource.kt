package jilnesta.com.testmvvm.utils

import androidx.test.espresso.IdlingResource
import java.lang.IllegalStateException
import java.util.concurrent.atomic.AtomicInteger

class SimpleCountingIdlingResource(private val resourceName: String) : IdlingResource {

    private val counter = AtomicInteger(0)

    // written from main thread, read from any thread.
    @Volatile
    private var resourceCallback: IdlingResource.ResourceCallback? = null


    override fun getName() = resourceName

    override fun isIdleNow() = counter.get() == 0

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.resourceCallback = callback
    }

    fun increment() {
        counter.getAndIncrement()
    }

    fun decrement() {
        val counterVal = counter.decrementAndGet()
        if(counterVal == 0) {
            resourceCallback?.onTransitionToIdle()
        } else if(counterVal < 0) {
            throw IllegalStateException("Counter has been corrupted!")
        }
    }

}