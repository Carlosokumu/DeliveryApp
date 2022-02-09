package com.example.deliveryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.deliveryapp.R
import com.example.deliveryapp.adapters.ItemAdapter
import com.example.deliveryapp.databinding.FragmentDrinksBinding
import com.example.deliveryapp.models.ItemsModel


class Drinks : Fragment() {

    private var _binding: FragmentDrinksBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDrinksBinding.inflate(layoutInflater, container, false)



        val recyclerView = _binding!!.cakesrecyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

        val drinks = ArrayList<ItemsModel>()
        drinks.add(ItemsModel("Chrome","ksh950",R.drawable.dinner))
        drinks.add(ItemsModel("JohnWalker","ksh4000",R.drawable.ndrink))
        drinks.add(ItemsModel("General Meakins","ksh700",R.drawable.ndrink))
        drinks.add(ItemsModel("BlueIce","ksh450",R.drawable.ndrink))
        drinks.add(ItemsModel("Fanta","ksh100",R.drawable.ndrink))
        drinks.add(ItemsModel("Sprite","ksh150",R.drawable.cake))
        drinks.add(ItemsModel("Gilbeys","ksh650",R.drawable.cake))
        drinks.add(ItemsModel("Quarana","ksh200",R.drawable.cake))

        val adapter = ItemAdapter(drinks)
        recyclerView.adapter = adapter


        return  binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}