package com.dukwantv

import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.ktx.messaging

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Firebase.messaging.subscribeToTopic("campaign")
            .addOnCompleteListener{task ->
                var msg = "subkreb"
                if (!task.isSuccessful){
                    msg = "subkref pailed"
                }
                Log.d("TAG", msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }
//        val refreshToken = FirebaseInstanceIdInternal.getInstance().token
//        val refreshToken = FirebaseMessaging.getInstance().token
//        val TAG = FirebaseMessagingService().get
//        Log.i("TAG", "INI TOKEN: $token")
//        Log.e("refreshToken: ", refreshToken)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        showNotification(p0.notification?.title.toString(), p0.notification?.body.toString())
    }

    fun showNotification(title: String, message: String){
        val builder = NotificationCompat.Builder(this, "kimdookwan")
            .setContentTitle(title)
            .setContentText(message)

        val manager = NotificationManagerCompat.from(this)
        manager.notify(222, builder.build())
    }
}
