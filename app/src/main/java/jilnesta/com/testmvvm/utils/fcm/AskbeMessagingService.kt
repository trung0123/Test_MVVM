package jilnesta.com.testmvvm.utils.fcm

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class AskbeMessagingService : FirebaseMessagingService() {

    companion object{
        const val TAG = "AskbeMessagingService"
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        sendNotification(message)
       Timber.tag(TAG).d("onMessageReceived: ")
    }

    private fun sendNotification(message: RemoteMessage) {
       /* Utils.sendNotification(this, message)*/
    }

    override fun onNewToken(token: String) {
        Timber.tag(TAG).d("Refreshed token: $token")
    }
}