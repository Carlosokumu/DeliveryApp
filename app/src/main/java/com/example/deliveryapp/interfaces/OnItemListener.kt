package com.example.deliveryapp.interfaces

import com.example.deliveryapp.models.OrderInfo

interface OnItemListener {



    fun clientOrder(item: OrderInfo)
}