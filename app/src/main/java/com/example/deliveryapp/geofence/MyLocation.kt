package com.example.mombasa.geofence

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import java.util.*


class MyLocation(val context: Context, val result: LocationResult) {

    var timer1: Timer? = null
    var lm: LocationManager? = null
    var locationResult: LocationResult? = null
    var gps_enabled = false
    var network_enabled = false


    @SuppressLint("MissingPermission")
    fun getLocations(): Boolean {
        locationResult = result
        if (lm == null) {
            lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        }
        try {
            gps_enabled = lm!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
        } catch (ex: Exception) {
        }
        try {
            network_enabled = lm!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        } catch (ex: Exception) {
        }
        if (!gps_enabled && !network_enabled) return false

        if (gps_enabled){
            lm?.requestLocationUpdates(LocationManager.GPS_PROVIDER,(0).toLong(), 0f, locationListenerGps)
        }
        if (network_enabled) {
            lm?.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                (0).toLong(),
                0f,
                locationListenerNetwork
            )
        }
        timer1 = Timer()
        timer1!!.schedule(GetLastLocation(), 20000)
        return true

    }

    var locationListenerGps: LocationListener = object : LocationListener {




        override fun onLocationChanged(location: Location) {
            timer1!!.cancel()
            locationResult?.getLocation(location)
            lm?.removeUpdates(this)
            lm?.removeUpdates(locationListenerNetwork)
        }


    }

    var locationListenerNetwork: LocationListener = object : LocationListener {



        override fun onLocationChanged(location: Location) {
            timer1!!.cancel()
            locationResult?.getLocation(location)
            lm?.removeUpdates(this)
            lm?.removeUpdates(locationListenerGps)
        }


    }


    inner class GetLastLocation : TimerTask() {
        @SuppressLint("MissingPermission")
        override fun run() {
            lm?.removeUpdates(locationListenerGps)
            lm?.removeUpdates(locationListenerNetwork)
            var net_loc: Location? = null
            var gps_loc: Location? = null
            if (gps_enabled) gps_loc = lm?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (network_enabled) net_loc = lm?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

            //if there are both values use the latest one
            if (gps_loc != null && net_loc != null) {
                if (gps_loc.time > net_loc.time) locationResult?.getLocation(gps_loc) else locationResult?.getLocation(
                    net_loc
                )
                return
            }
            if (gps_loc != null) {
                locationResult?.getLocation(gps_loc)
                return
            }
            if (net_loc != null) {
                locationResult?.getLocation(net_loc)
                return
            }
            locationResult?.getLocation(null)
        }
    }
}