package com.example.deliveryapp.utils

import android.content.Context
import com.example.deliveryapp.MyObjectBox
import io.objectbox.BoxStore


object ObjectBox {
        lateinit var store: BoxStore
            private set

        fun init(context: Context) {
            store = MyObjectBox.builder()
                .androidContext(context.applicationContext)
                .build()
        }
    }


