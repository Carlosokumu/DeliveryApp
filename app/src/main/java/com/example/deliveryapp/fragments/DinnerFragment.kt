package com.example.deliveryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.deliveryapp.R
import com.example.deliveryapp.adapters.ItemAdapter
import com.example.deliveryapp.databinding.FragmentDinnerBinding
import com.example.deliveryapp.models.ItemsModel


class DinnerFragment : Fragment() {

    private var _binding: FragmentDinnerBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDinnerBinding.inflate(layoutInflater, container, false)


//        _binding?.cakesrecyclerView?.adapter = DinnersAdapter(getDinner())
//        _binding?.cakesrecyclerView?.layoutManager = GridLayoutManager(requireContext(),2)

        val recyclerView = _binding!!.cakesrecyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

        val item = ArrayList<ItemsModel>()
        item.add(ItemsModel("Fries","ksh150",R.drawable.dinner))
        item.add(ItemsModel("Rice","ksh400",R.drawable.dinner))
        item.add(ItemsModel("Pilau","ksh200",R.drawable.dinner))
        item.add(ItemsModel("Matoke","ksh300",R.drawable.cake))
        item.add(ItemsModel("Ugali","ksh100",R.drawable.dinner))
        item.add(ItemsModel("Fish","ksh250",R.drawable.cake))
        item.add(ItemsModel("Githeri","ksh50",R.drawable.cake))
        item.add(ItemsModel("Cavier","ksh6,850",R.drawable.cake))

        val adapter = ItemAdapter(item)
        recyclerView.adapter = adapter



        return  binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}