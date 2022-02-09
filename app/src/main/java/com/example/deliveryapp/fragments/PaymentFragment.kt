package com.example.deliveryapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.deliveryapp.OrderInfo
import com.example.deliveryapp.R
import com.example.deliveryapp.utils.ObjectBox
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PaymentFragment: BottomSheetDialogFragment() {


   companion object{
       lateinit var orderInfo: OrderInfo
       fun newInstance(orderInfo: OrderInfo): PaymentFragment {
            this.orderInfo = orderInfo
           return PaymentFragment()
       }
   }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.payment_fragment, container, false)
       view.findViewById<TextView>(R.id.type).text = orderInfo.name
        view.findViewById<TextView>(R.id.amount).text = orderInfo.price

        view.findViewById<Button>(R.id.btnplaceOder).setOnClickListener {
            val userBox = ObjectBox.store.boxFor(OrderInfo::class.java)
            userBox.put(orderInfo)
        }
        return view
    }
}