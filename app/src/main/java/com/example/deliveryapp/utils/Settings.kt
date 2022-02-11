package com.example.deliveryapp.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


object Settings {
        private var defaultPreferences: SharedPreferences?=null
        private const val SHARED_PREFERENCE_NAME: String = "DeliveryApp"

        /*
           This will be used to collect data in key Value pairs from our sheets(Haha(*~ *))
         */
        var dataPreferences: SharedPreferences?=null
        /*
            This will be used to initialize the the preferences
         */
        fun init(context: Context){
            defaultPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            dataPreferences =context.getSharedPreferences(SHARED_PREFERENCE_NAME,0)
        }



    fun loggedAsAdmin(isAdmin: Boolean) {
        dataPreferences?.edit()?.putBoolean("ADMIN",isAdmin)?.apply()
    }


    fun loggedasCustomer(isAdmin: Boolean) {
        dataPreferences?.edit()?.putBoolean("CUSTOMER",isAdmin)?.apply()
    }


    fun loggedAsDeliverer(deliverer: Boolean) {
        dataPreferences?.edit()?.putBoolean("DELIVERER",deliverer)?.apply()
    }



    fun setUserName(userName: String) {
        dataPreferences?.edit()?.putString("USERNAME",userName)?.apply()
    }





    fun isAdmin() = dataPreferences?.getBoolean("ADMIN",false)

    fun isDeliverer() = dataPreferences?.getBoolean("DELIVERER",false)


    fun isCustomer() = dataPreferences?.getBoolean("CUSTOMER",false)
    fun getUserName() = dataPreferences?.getString("USERNAME","Rider")
}