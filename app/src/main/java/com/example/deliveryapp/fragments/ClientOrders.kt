package com.example.deliveryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deliveryapp.OrderInfo
import com.example.deliveryapp.R
import com.example.deliveryapp.adapters.CakesAdapter
import com.example.deliveryapp.adapters.OdersAdapter
import com.example.deliveryapp.databinding.FragmentCakesBinding
import com.example.deliveryapp.databinding.FragmentClientOrdersBinding


class ClientOrders : Fragment() {

    private var _binding: FragmentClientOrdersBinding?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClientOrdersBinding.inflate(layoutInflater, container, false)
        if (getDummyList().isEmpty()){
            _binding?.empty?.visibility= View.VISIBLE
        }
        else {
            val adapter = OdersAdapter(getDummyList())
            _binding?.recyclerOrders?.adapter =  adapter
        }


        return  _binding?.root


    }





    fun getDummyList() =
         listOf(
            OrderInfo("Queen Cake","Ezra Mochiemo","100"),
            OrderInfo("Queen Cake","Ezra Mochiemo","100"),
            OrderInfo("Queen Cake","Ezra Mochiemo","100"),
        )



}