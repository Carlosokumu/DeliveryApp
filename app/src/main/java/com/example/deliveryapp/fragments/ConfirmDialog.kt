package com.example.deliveryapp.fragments

import android.app.Dialog
import android.content.Context
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.MaterialDialog
import com.example.deliveryapp.R
import com.example.deliveryapp.models.OrderInfo
import com.example.deliveryapp.models.Success
import com.example.deliveryapp.viewmodels.DelivererVm
import com.example.mombasa.geofence.LocationResult
import com.example.mombasa.geofence.MyLocation
import com.sdsmdg.tastytoast.TastyToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConfirmDialog(context: Context) : DialogFragment(), Callback<Success> {


    private lateinit var viewModel: DelivererVm

    private lateinit var btnConfirm: TextView


    companion object {

        lateinit var orderInfo: OrderInfo

        fun newInstance(orderInfo: OrderInfo, context: Context): ConfirmDialog {
            this.orderInfo = orderInfo
            return ConfirmDialog(context)
        }

    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext()).inflate(
            R.layout.confirm_layout,
            null,
            false
        )


        val txtNameConfirm = view.findViewById<TextView>(R.id.nameConfirm)
        btnConfirm = view.findViewById(R.id.btnConfirm)
        val priceConfirm = view.findViewById<TextView>(R.id.priceConfirm)



        priceConfirm.text = orderInfo.price
        txtNameConfirm.text = orderInfo.name


        viewModel = ViewModelProvider(this).get(DelivererVm::class.java)


        btnConfirm.setOnClickListener {
            (it as TextView).text = "Placing Order"
            viewModel.placeOder(
                name = orderInfo.name, orderBy = orderInfo.orderedBy, price = orderInfo.price,
                longitude = orderInfo.longitude.toString(), latitude = orderInfo.latitude.toString()
            ).enqueue(this)
        }

        return MaterialDialog.Builder(requireActivity()).customView(view, false)
            .backgroundColor(resources.getColor(R.color.whiteuniv))
            .build()
    }

    override fun onResponse(call: Call<Success>, response: Response<Success>) {
        if (response.isSuccessful) {
            TastyToast.makeText(
                context,
                "Order Placed Sucessfully,Please Check your Email for delivery Updates",
                TastyToast.LENGTH_LONG,
                TastyToast.SUCCESS
            ).show()

            this.dismiss()
        } else {
            TastyToast.makeText(
                context,
                "Failed to Place order Please try again Later",
                TastyToast.LENGTH_LONG,
                TastyToast.ERROR
            ).show()
            this.dismiss()
        }
    }

    override fun onFailure(call: Call<Success>, t: Throwable) {
        TastyToast.makeText(
            context,
            "Failed to Place order Please try again Later",
            TastyToast.LENGTH_LONG,
            TastyToast.ERROR
        ).show()
    }
}