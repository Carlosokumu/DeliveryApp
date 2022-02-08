package com.example.deliveryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.deliveryapp.R
import com.example.deliveryapp.adapters.Cake
import com.example.deliveryapp.adapters.DinnersAdapter
import com.example.deliveryapp.adapters.DrinksAdapter
import com.example.deliveryapp.databinding.FragmentDinnerBinding
import com.example.deliveryapp.databinding.FragmentDrinksBinding


class Drinks : Fragment() {



    private var _binding: FragmentDrinksBinding?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDrinksBinding.inflate(layoutInflater, container, false)


        _binding?.cakesrecyclerView?.adapter =DrinksAdapter(getCakes())
        _binding?.cakesrecyclerView?.layoutManager = GridLayoutManager(requireContext(),2)



        return  _binding?.root
    }




    fun getCakes() = listOf<Cake>(

        Cake("Strawberry"),
        Cake("Strawberry"), Cake("Strawberry")
    )



}