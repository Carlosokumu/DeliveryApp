package com.example.deliveryapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deliveryapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PaymentFragment: BottomSheetDialogFragment() {


   companion object{
       fun newInstance(): PaymentFragment {
           return PaymentFragment()
       }
   }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.payment_fragment, container, false)
    }
}