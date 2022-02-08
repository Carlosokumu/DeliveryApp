package com.example.deliveryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deliveryapp.R
import com.example.deliveryapp.adapters.Cake
import com.example.deliveryapp.adapters.CakesAdapter
import com.example.deliveryapp.adapters.DinnersAdapter
import com.example.deliveryapp.databinding.FragmentCakesBinding
import com.example.deliveryapp.databinding.FragmentDinnerBinding


class DinnerFragment : Fragment() {

    private var _binding: FragmentDinnerBinding?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDinnerBinding.inflate(layoutInflater, container, false)


        _binding?.cakesrecyclerView?.adapter = DinnersAdapter(getCakes())



        return  _binding?.root
    }


    fun getCakes() = listOf<Cake>(

        Cake("Strawberry"),
        Cake("Strawberry"), Cake("Strawberry")
    )



}