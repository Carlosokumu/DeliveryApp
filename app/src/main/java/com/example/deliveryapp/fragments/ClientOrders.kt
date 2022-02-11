package com.example.deliveryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.deliveryapp.viewmodels.DelivererVm
import com.example.deliveryapp.models.OrderInfo
import com.example.deliveryapp.adapters.OdersAdapter
import com.example.deliveryapp.databinding.FragmentClientOrdersBinding
import com.sdsmdg.tastytoast.TastyToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ClientOrders : Fragment() {
    private lateinit var viewModel: DelivererVm
    private var _binding: FragmentClientOrdersBinding?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClientOrdersBinding.inflate(layoutInflater, container, false)

        viewModel = ViewModelProvider(this).get(DelivererVm::class.java)

         viewModel.getOrders().enqueue(object : Callback<List<OrderInfo>>{
             override fun onResponse(
                 call: Call<List<OrderInfo>>,
                 response: Response<List<OrderInfo>>
             ) {
                 if (response.isSuccessful){
                     val adapter = OdersAdapter(response.body()!!,requireContext())
                     _binding?.clientOrder?.visibility = View.GONE
                     _binding?.recyclerOrders?.adapter =  adapter
                 }
             }

             override fun onFailure(call: Call<List<OrderInfo>>, t: Throwable) {
                  TastyToast.makeText(requireContext(),"Soomething Went Wrong",TastyToast.LENGTH_SHORT,TastyToast.ERROR).show()
             }

         })





        return  _binding?.root


    }




//    fun getDummyList() =
//         listOf(
//            OrderInfo("Queen Cake","Ezra Mochiemo","100"),
//            OrderInfo("Queen Cake","Ezra Mochiemo","100"),
//            OrderInfo("Queen Cake","Ezra Mochiemo","100"),
//        )



}