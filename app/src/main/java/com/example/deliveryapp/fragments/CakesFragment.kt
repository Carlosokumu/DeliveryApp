package com.example.deliveryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
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
        val adapter = CakesAdapter()
        adapter.setCakes(getCakes())
        _binding?.cakesrecyclerView?.adapter = CakesAdapter()



       return  _binding?.root
    }


    fun getCakes() = listOf<Cake>(

        Cake("Strawberry"),
        Cake("Strawberry"),Cake("Strawberry")
    )

}