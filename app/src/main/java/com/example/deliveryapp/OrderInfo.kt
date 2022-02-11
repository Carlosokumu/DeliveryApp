package com.example.deliveryapp

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class OrderInfo(


     @Id
     var id: Long = 0,
    var name: String,
    var orderedBy: String,
    var price: String,
     var latitude: String?,
     var longitude: String?,


) {
}