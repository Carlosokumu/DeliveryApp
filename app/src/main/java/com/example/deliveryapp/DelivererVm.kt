package com.example.deliveryapp

import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DelivererVm(): ViewModel() {

   private var apiService: ApiService

 init {
     val gson = GsonBuilder()
         .serializeNulls()
         .create()
     apiService  = Retrofit.Builder()
         .baseUrl(Constants.BASE_URL2)
         .addConverterFactory(GsonConverterFactory.create(gson))
         .build().create(ApiService::class.java)
 }



    fun registerDeliverer(firstName: String,secondName: String,phoneNumber: String) = apiService.registerDeliverer(firstName,secondName,phoneNumber)



}