package com.example.deliveryapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.deliveryapp.adapters.CakesAdapter
import com.example.deliveryapp.adapters.DeliverersAdapters
import com.example.deliveryapp.adapters.OdersAdapter
import com.example.deliveryapp.databinding.FragmentCakesBinding
import com.example.deliveryapp.databinding.FragmentClientOrdersBinding
import com.example.deliveryapp.databinding.FragmentDeliverersListBinding
import com.sdsmdg.tastytoast.TastyToast
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class DeliverersList : Fragment() {
    private var _binding: FragmentDeliverersListBinding? = null

    private lateinit var viewModel: DelivererVm
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDeliverersListBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this).get(DelivererVm::class.java)

         viewModel.getDelivers().enqueue(object :
             retrofit2.Callback<List<DelivererInfo>> {
             override fun onResponse(
                 call: Call<List<DelivererInfo>>,
                 response: Response<List<DelivererInfo>>
             ) {
                     if (response.body()!!.isEmpty()){
                         _binding?.empty?.visibility= View.VISIBLE
                     }
                     else{
                              val adapter = DeliverersAdapters(response.body()!!)
                              _binding?.no?.visibility= View.GONE
                              _binding?.recyclerOrders?.adapter =  adapter

                     }
             }

             override fun onFailure(call: Call<List<DelivererInfo>>, t: Throwable) {
                 Toast.makeText(requireContext(),t.message.toString(),Toast.LENGTH_SHORT).show()
             }

         })


        return _binding?.root
    }







}