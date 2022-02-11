package com.example.deliveryapp

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id




@Entity
data  class ClientInfo(
    @Id
    var id: Long = 0,
    var longitude: String,
    var latitude: String,
    var clientName: String,
    var phoneNumber: String
    )

