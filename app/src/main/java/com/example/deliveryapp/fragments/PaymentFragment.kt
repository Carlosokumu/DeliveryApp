package com.example.deliveryapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.deliveryapp.OrderInfo
import com.example.deliveryapp.R
import com.example.deliveryapp.models.UserInfo
import com.example.deliveryapp.utils.ObjectBox
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import java.net.URISyntaxException

class PaymentFragment: BottomSheetDialogFragment() {

    lateinit var mSocket: Socket
    val gson: Gson = Gson()

   companion object{
       lateinit var orderInfo: OrderInfo
       fun newInstance(orderInfo: OrderInfo): PaymentFragment {
            this.orderInfo = orderInfo
           return PaymentFragment()
       }
   }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            mSocket = IO.socket("https://chatsocketcarlos.herokuapp.com")

        } catch (e: URISyntaxException) {

        }
        mSocket.connect()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.payment_fragment, container, false)
       view.findViewById<TextView>(R.id.type).text = orderInfo.name
        view.findViewById<TextView>(R.id.amount).text = orderInfo.price






        var onOrder = Emitter.Listener {
            val userInfo= gson.fromJson(it[0].toString(),UserInfo::class.java)
             Log.d("Data",userInfo.name)
        }

        mSocket.on("newOrder",onOrder)

        view.findViewById<TextView>(R.id.btnplaceOder).setOnClickListener {
            val userBox = ObjectBox.store.boxFor(OrderInfo::class.java)
            userBox.put(orderInfo)
            val data =  UserInfo(orderInfo.name,orderInfo.name,"me")
            val jsonData = gson.toJson(data)
            mSocket.emit("onOrder", jsonData)

        }
        return view
    }
}