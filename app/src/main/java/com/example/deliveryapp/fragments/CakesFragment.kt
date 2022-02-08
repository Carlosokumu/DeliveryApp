package com.example.deliveryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.deliveryapp.R
import com.example.deliveryapp.adapters.Cake
import com.example.deliveryapp.adapters.CakesAdapter
import com.example.deliveryapp.databinding.FragmentCakesBinding


class CakesFragment() : Fragment() {

    private var _binding: FragmentCakesBinding?= null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCakesBinding.inflate(layoutInflater, container, false)
        val adapter = CakesAdapter(getCakes())

        _binding?.cakesrecyclerView?.adapter = adapter
        _binding?.cakesrecyclerView?.layoutManager = GridLayoutManager(requireContext(),2)




       return  _binding?.root
    }


    fun getCakes() = listOf<Cake>(

        Cake("Strawberry"),
        Cake("Strawberry"),Cake("Strawberry")
    )

}