package com.example.deliveryapp

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {


    @GET("/deliverer")
     fun getDeliverers(): Call<List<DelivererInfo>>


    @FormUrlEncoded
    @POST("/deliverer/register")
    fun registerDeliverer(
        @Field("firstName") firstName: String,
        @Field("secondName") email: String,
        @Field("phoneNumber") phoneNumber: String
    ): Call<Success>
}


