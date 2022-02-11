package com.example.mombasa.geofence

import android.location.Location

abstract  class LocationResult {

    abstract fun getLocation(location: Location?)
}