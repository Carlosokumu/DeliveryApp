package com.example.deliveryapp

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import com.google.android.gms.maps.model.LatLng
import java.util.*


object ReminderNotification {
    const val REMINDER_CHANNEL="reminders"
    const val REMINDER_ID=R.string.default_notification_channel_id
    fun notify(context: Context, titleText: String, message: String){
        val shareIntent=PendingIntent.getActivity(context,0, Intent.createChooser(Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT,message),"SHARE"),PendingIntent.FLAG_UPDATE_CURRENT)
        val intent=Intent(Intent(context,AdminSection::class.java))
        //It ensures that once a user launches the pending intent from the notification,a backStack is maintained
        intent.putExtra("EXTRAS","Am Deep Linked")
        val pendingIntent= TaskStackBuilder.create(context).addNextIntentWithParentStack(intent).getPendingIntent(0,
            PendingIntent.FLAG_UPDATE_CURRENT)
        val builder=NotificationCompat.Builder(context, REMINDER_CHANNEL)
            //Set Defaults for the notification ie..Light,Sound,Vibration
            .setDefaults(Notification.DEFAULT_ALL)
            .setSmallIcon(R.drawable.orders)
            .setContentTitle(titleText)
            .setContentText(message)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setTicker(titleText)
            .setAutoCancel(true)

        val m= NotificationManagerCompat.from(context)
        m.notify(REMINDER_ID,builder.build())

    }
}


data class Reminder(val id: String = UUID.randomUUID().toString(), var latLng: LatLng?, var radius: Double?, var message: String?) {
}