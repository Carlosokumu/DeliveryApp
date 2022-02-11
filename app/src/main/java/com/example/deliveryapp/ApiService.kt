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


    @FormUrlEncoded
    @POST("/orders/orders")
    fun placeOrder(
        @Field("name") name: String,
        @Field("orderedBy") orderedBy: String,
        @Field("price")   price: String,
        @Field("longitude") latitude: String,
        @Field("latitude") longitude: String
    ): Call<Success>



   @GET("/orders")
    fun getOrders(): Call<List<OrderInfo>>
}


