package com.example.deliveryapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deliveryapp.adapters.CakesAdapter
import com.example.deliveryapp.adapters.DeliverersAdapters
import com.example.deliveryapp.adapters.OdersAdapter
import com.example.deliveryapp.databinding.FragmentCakesBinding
import com.example.deliveryapp.databinding.FragmentClientOrdersBinding
import com.example.deliveryapp.databinding.FragmentDeliverersListBinding


class DeliverersList : Fragment() {
    private var _binding: FragmentDeliverersListBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDeliverersListBinding.inflate(layoutInflater, container, false)

        if (getDummyList().isEmpty()){
            _binding?.empty?.visibility= View.VISIBLE
        }
        else {
            val adapter = DeliverersAdapters(getDummyList())
            _binding?.recyclerOrders?.adapter =  adapter
        }
        return _binding?.root
    }



    private fun getDummyList()= listOf(
                  DelivererInfo("Mike Sonko","+254709102"),
                          DelivererInfo("Mike Sonko","+254709102"),
        DelivererInfo("Mike Sonko","+254709102"),
        DelivererInfo("Mike Sonko","+254709102")
            )




}