package com.example.deliveryapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.deliveryapp.network.ApiService
import com.example.deliveryapp.utils.Constants
import com.google.gson.GsonBuilder
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



    fun getDelivers() = apiService.getDeliverers()



    fun getOrders() = apiService.getOrders()

    fun placeOder(name: String,orderBy: String,price: String,longitude: String,latitude: String) = apiService.placeOrder(name = name,orderedBy = orderBy,price = price,longitude = longitude,latitude = latitude)


}