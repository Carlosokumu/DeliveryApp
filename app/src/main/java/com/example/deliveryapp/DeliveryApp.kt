package com.example.deliveryapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import com.example.deliveryapp.utils.ObjectBox
import com.example.deliveryapp.utils.Settings
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

class DeliveryApp: Application() {

    private lateinit var repo: RemainderRepo

    lateinit var mSocket: Socket



    init {
        try {
            mSocket = IO.socket("https://chatsocketcarlos.herokuapp.com")

        } catch (e: URISyntaxException) {
            Log.d("Reason",e.reason)
        }
        mSocket.connect()
    }
    override fun onCreate() {
        super.onCreate()
        ObjectBox.init(this)
        createNotificationChannel()
        repo = RemainderRepo(this)
        Settings.init(this)



    }


    fun getSocket() = mSocket



    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.app_name)
            val descriptionText = getString(R.string.app_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(ReminderNotification.REMINDER_CHANNEL, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


    fun getRepo() = repo
}