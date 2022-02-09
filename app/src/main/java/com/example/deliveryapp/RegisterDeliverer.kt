package com.example.deliveryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.deliveryapp.databinding.ActivityDashBoardBinding
import com.example.deliveryapp.databinding.ActivityRegisterDelivererBinding
import com.sdsmdg.tastytoast.TastyToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterDeliverer : AppCompatActivity(), View.OnClickListener {

    private val viewModel = ViewModelProvider(this).get(DelivererVm::class.java)
    private lateinit var binding: ActivityRegisterDelivererBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterDelivererBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegister.setOnClickListener(this)

    }


    override fun onClick(view: View?) {
        register()
    }


    private fun register() {
        if (binding.dFirstName.text.isEmpty() || binding.dLastName.text.isEmpty()) {
            TastyToast.makeText(
                this,
                "Check Your fields",
                TastyToast.LENGTH_SHORT,
                TastyToast.WARNING
            ).show()
        } else {
            val firstName = binding.dFirstName.text.toString().trim()
            val lastName = binding.dLastName.text.toString().trim()
            val phone = binding.dPhone.text.toString().trim()
            binding.btnRegister.text ="Registering"
            viewModel.registerDeliverer(firstName, lastName, phone).enqueue(object :
                Callback<Success> {
                override fun onResponse(call: Call<Success>, response: Response<Success>) {

                }

                override fun onFailure(call: Call<Success>, t: Throwable) {

                }

            })
        }

    }
}