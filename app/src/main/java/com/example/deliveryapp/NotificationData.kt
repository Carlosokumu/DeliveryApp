package com.example.deliveryapp

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id


@Entity
data class NotificationData(


    @Id
    var id: Long = 0,
    val whatOrdered:  String,
    val message: String)
{
}