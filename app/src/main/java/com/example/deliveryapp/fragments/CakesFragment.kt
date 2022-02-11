package com.example.deliveryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.deliveryapp.interfaces.OnItemListener
import com.example.deliveryapp.models.OrderInfo
import com.example.deliveryapp.R
import com.example.deliveryapp.adapters.CakesAdapter
import com.example.deliveryapp.databinding.FragmentCakesBinding
import com.example.deliveryapp.models.CakeModel


class CakesFragment() : Fragment(), OnItemListener {

    private var _binding: FragmentCakesBinding?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCakesBinding.inflate(layoutInflater, container, false)
        val adapter = CakesAdapter(getCakes())
         adapter.setListener(this)
        _binding?.cakesrecyclerView?.adapter = adapter
       // _binding?.cakesrecyclerView?.layoutManager = GridLayoutManager(requireContext(),2)




       return  _binding?.root
    }


    private fun getCakes(): List<CakeModel> {
        val cakes = mutableListOf<CakeModel>()
        cakes.add(CakeModel("Peggies","1450",R.drawable.peggy))
        cakes.add(CakeModel("Chocholate Sweet Cake","800",R.drawable.cakered))
        cakes.add(CakeModel("Pink Muffins","450",R.drawable.cakered))
        cakes.add(CakeModel("Queen Cakes","1000",R.drawable.pancakes))
       // cakes.add(CakeModel("BlackForest","450",R.drawable.cake))
      //  cakes.add(CakeModel("BlackForest","450",R.drawable.cake))
      //  cakes.add(CakeModel("BlackForest","450",R.drawable.cake))
       // cakes.add(CakeModel("BlackForest","450",R.drawable.cake))
        return cakes
    }

    override fun clientOrder(item: OrderInfo) {
        Toast.makeText(requireContext(),item.orderedBy,Toast.LENGTH_SHORT).show()
    }


}