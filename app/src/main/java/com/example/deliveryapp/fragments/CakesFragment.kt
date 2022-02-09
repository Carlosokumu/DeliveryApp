package com.example.deliveryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.deliveryapp.R
import com.example.deliveryapp.adapters.ItemAdapter
import com.example.deliveryapp.databinding.FragmentCakesBinding
import com.example.deliveryapp.models.ItemsModel


class CakesFragment() : Fragment() {

    private var _binding: FragmentCakesBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCakesBinding.inflate(layoutInflater, container, false)
        //val adapter = CakesAdapter(getCakes())

        /*_binding?.cakesrecyclerView?.adapter = adapter
        _binding?.cakesrecyclerView?.layoutManager = GridLayoutManager(requireContext(),2)*/


        val recyclerView = _binding!!.cakesrecyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

        val cakes = ArrayList<ItemsModel>()
        cakes.add(ItemsModel("BlackForest","ksh450",R.drawable.cake))
        cakes.add(ItemsModel("WhiteForest","ksh4000",R.drawable.cake))
        cakes.add(ItemsModel("QueenCake","ksh350",R.drawable.cake))
        cakes.add(ItemsModel("Muffins","ksh250",R.drawable.cake))
        cakes.add(ItemsModel("Scones","ksh150",R.drawable.cake))
        cakes.add(ItemsModel("Kdf","ksh850",R.drawable.cake))
        cakes.add(ItemsModel("LoveCake","ksh550",R.drawable.cake))
        cakes.add(ItemsModel("Ngumu","ksh650",R.drawable.cake))

        val adapter = ItemAdapter(cakes)
        recyclerView.adapter = adapter

       return  _binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}