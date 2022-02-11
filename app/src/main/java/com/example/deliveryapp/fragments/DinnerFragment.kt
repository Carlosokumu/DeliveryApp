package com.example.deliveryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.deliveryapp.R
import com.example.deliveryapp.adapters.Cake
import com.example.deliveryapp.adapters.CakesAdapter
import com.example.deliveryapp.adapters.DinnersAdapter
import com.example.deliveryapp.databinding.FragmentCakesBinding
import com.example.deliveryapp.databinding.FragmentDinnerBinding
import com.example.deliveryapp.models.CakeModel


class DinnerFragment : Fragment() {

    private var _binding: FragmentDinnerBinding?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDinnerBinding.inflate(layoutInflater, container, false)


        _binding?.cakesrecyclerView?.adapter = DinnersAdapter(getDinner())
        _binding?.cakesrecyclerView?.layoutManager = GridLayoutManager(requireContext(),2)



        return  _binding?.root
    }


    private fun getDinner(): List<CakeModel>{
        val cakes = mutableListOf<CakeModel>()
        cakes.add(CakeModel("Sushi","300",R.drawable.mealthree))
        cakes.add(CakeModel("Cavier","750",R.drawable.mealtwo))
        cakes.add(CakeModel("French Fries","400",R.drawable.pancakes))
        cakes.add(CakeModel("Chicken Fries","510",R.drawable.chicken))
        cakes.add(CakeModel("Mexican Greens","950",R.drawable.mexican))
        //cakes.add(CakeModel("Italian Pastas","900",R.drawable.mealone))

        return cakes
    }




}