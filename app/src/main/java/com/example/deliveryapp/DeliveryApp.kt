package com.example.deliveryapp

import android.app.Application
import com.example.deliveryapp.utils.ObjectBox

class DeliveryApp: Application() {


    override fun onCreate() {
        super.onCreate()
        ObjectBox.init(this)
    }
}