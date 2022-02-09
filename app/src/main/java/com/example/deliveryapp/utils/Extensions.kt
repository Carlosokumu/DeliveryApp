package com.example.deliveryapp.utils

import com.example.deliveryapp.OrderInfo
import com.example.deliveryapp.models.CakeModel

object Extensions {

    fun CakeModel.asOderInfo(loggedUser: String) =
        OrderInfo(name = this.name,orderedBy = loggedUser,price = this.price)

}