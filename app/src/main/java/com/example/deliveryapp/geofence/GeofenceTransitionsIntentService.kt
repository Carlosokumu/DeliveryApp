package com.example.deliveryapp.geofence

import android.app.Service
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.util.Log
import androidx.core.app.JobIntentService
import com.example.deliveryapp.DeliveryApp
import com.example.deliveryapp.Reminder
import com.example.deliveryapp.ReminderNotification
import com.example.mombasa.geofence.GeofenceErrorMessages
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent

class GeofenceTransitionsIntentService: JobIntentService() {


    companion object {
        private const val LOG_TAG = "GeoTrIntentService"
        private const val JOB_ID = 573



        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(
                context,
                GeofenceTransitionsIntentService::class.java, JOB_ID,
                intent)
        }
    }


    override fun onHandleWork(intent: Intent) {

        val geofencingEvent = GeofencingEvent.fromIntent(intent)
        if (geofencingEvent.hasError()) {
            val errorMessage = GeofenceErrorMessages.getErrorString(this,
                geofencingEvent.errorCode)
            Log.e(LOG_TAG, errorMessage)
            return
        }
        handleEvent(geofencingEvent)

    }



    private fun getFirstReminder(triggeringGeofences: List<Geofence>): Reminder? {
        val firstGeofence = triggeringGeofences[0]
        return (application as DeliveryApp).getRepo().get(firstGeofence.requestId)
    }











    private fun handleEvent(event: GeofencingEvent) {
        if (event.geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
            val reminder = getFirstReminder(event.triggeringGeofences)
            val message = reminder?.message
            val latLng = reminder?.latLng
            if (message != null && latLng != null) {
                ReminderNotification.notify(this, message,"You Oder Has Arrived")
                sendMessage("0719439259")

            }
        }
    }


    private fun sendMessage(mobile: String){
        try{
            val smgr = SmsManager.getDefault();
            smgr.sendTextMessage(mobile,null,"Your Order has Arrived",null,null);

        }
        catch (e: Exception){
            Log.d("Exception",e.toString())
            //Toast.makeText(MainActivity.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
        }
    }




}