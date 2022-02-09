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
import com.example.deliveryapp.models.CakeModel


class Drinks : Fragment() {



    private var _binding: FragmentDrinksBinding?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDrinksBinding.inflate(layoutInflater, container, false)


        _binding?.cakesrecyclerView?.adapter =DrinksAdapter(getDrinks())
        _binding?.cakesrecyclerView?.layoutManager = GridLayoutManager(requireContext(),2)



        return  _binding?.root
    }




    private fun getDrinks(): List<CakeModel> {
        val cakes = mutableListOf<CakeModel>()
        cakes.add(CakeModel("KenyaCane","150",R.drawable.cake))
        cakes.add(CakeModel("Cocacola","450",R.drawable.cake))
        cakes.add(CakeModel("Sprite","450",R.drawable.cake))
        cakes.add(CakeModel("JohnWalker","450",R.drawable.cake))
        cakes.add(CakeModel("RedLabel","450",R.drawable.cake))
        cakes.add(CakeModel("BlackForest","450",R.drawable.cake))
        cakes.add(CakeModel("BlackForest","450",R.drawable.cake))
        cakes.add(CakeModel("BlackForest","450",R.drawable.cake))
        return cakes
    }






}