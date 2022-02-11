package com.example.deliveryapp

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.example.deliveryapp.geofence.GeofenceBroadcastReceiver
import com.example.mombasa.geofence.GeofenceErrorMessages
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson

class RemainderRepo(val context: Context) {





    companion object {
        private const val PREFS_NAME = "ReminderRepository"
        private const val REMINDERS = "REMINDERS"
    }




    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val gson = Gson()
    private val geofencingClient = LocationServices.getGeofencingClient(context)




    private val geofencePendingIntent: PendingIntent by lazy {
        val intent = Intent(context, GeofenceBroadcastReceiver::class.java)
        PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT)
    }







    /*

             Build a geofence with the given remainder information and return it.

     */

    private fun buildGeofence(reminder: Reminder): Geofence? {
        val latitude = reminder.latLng?.latitude
        val longitude = reminder.latLng?.longitude
        val radius = reminder.radius
        if (latitude != null && longitude != null && radius != null) {
            return Geofence.Builder()
                .setRequestId(reminder.id)
                .setCircularRegion(
                    latitude,
                    longitude,
                    radius.toFloat()
                )
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
                .setExpirationDuration(Geofence.NEVER_EXPIRE).build()

        }
        return null
    }














    /*
       The function basically defines how the Geofence will behave when added.
       Setting setInitialTrigger to 0 means that  the geofence will not be triggered if the user is in the area he/she has set the trigger.
    */

    private fun buildGeofencingRequest(geofence: Geofence): GeofencingRequest {
        return GeofencingRequest.Builder()
            .setInitialTrigger(0)
            .addGeofences(listOf(geofence))
            .build()
    }









    fun add(reminder: Reminder, success: () -> Unit, failure: (error: String) -> Unit) {

        //Build a geofence
        val geofence = buildGeofence(reminder)

        if (geofence != null &&  ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            /*
                      Feed the geofence to the Geofence Client
                      @params:
                      geofenceRequest
                      pendingIntent
             */
            geofencingClient.addGeofences(buildGeofencingRequest(geofence),geofencePendingIntent).addOnCompleteListener {
                //If Everything went well,add it to the File
                saveAll(getAll() + reminder)
                success()

            }.addOnFailureListener {
                //Tap the exception
                failure(GeofenceErrorMessages.getErrorString(context,it))
            }

        }

        val list = getAll() + reminder

        saveAll(list)

        success()

    }

    /*
         Remove a remainder from the list and save
     */

    fun remove(reminder: Reminder, success: () -> Unit,failure: (error: String) -> Unit) {
        val list = getAll() - reminder
        saveAll(list)
        success()

    }




    /*
         Return the list of remainders from the preference file else return an empty list
     */
    fun getAll(): List<Reminder> {
        if (preferences.contains(REMINDERS)) {
            val remindersString = preferences.getString(REMINDERS, null)
            val arrayOfReminders = gson.fromJson(remindersString, Array<Reminder>::class.java)
            if (arrayOfReminders != null) {
                return arrayOfReminders.toList()
            }
        }
        return listOf()

    }

    /*
         Get a remainder based on the id passed
     */

    fun get(requestId: String?) = getAll().firstOrNull {
        it.id == requestId
    }

    /*
          Save a remainder to the preference file
    */

    private fun saveAll(list: List<Reminder>) {
        preferences.edit().putString(REMINDERS, gson.toJson(list)).apply()
    }

    /*
           Get the last remainder or a null
     */
    fun getLast() = getAll().lastOrNull()


}
