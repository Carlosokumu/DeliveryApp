package com.example.deliveryapp.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.deliveryapp.*
import com.example.deliveryapp.models.UserInfo
import com.example.deliveryapp.utils.ObjectBox
import com.example.mombasa.geofence.LocationResult
import com.example.mombasa.geofence.MyLocation
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.sdsmdg.tastytoast.TastyToast
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URISyntaxException

class PaymentFragment(context: Context) : BottomSheetDialogFragment() {


    private lateinit var viewModel: DelivererVm
    lateinit var mSocket: Socket
    var longitude: Double = 0.0
    var latitude: Double = 0.0

    val gson: Gson = Gson()




    companion object {
        lateinit var orderInfo: OrderInfo


        fun newInstance(orderInfo: OrderInfo, context: Context): PaymentFragment {
            this.orderInfo = orderInfo

            return PaymentFragment(context)
        }
    }


    init {

        val locationResult: LocationResult = object : LocationResult() {
            override fun getLocation(location: Location?) {
                longitude = location!!.longitude
                latitude = location.latitude

            }

        }
        MyLocation(context, locationResult).getLocations()
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            mSocket = IO.socket("https://chatsocketcarlos.herokuapp.com")

        } catch (e: URISyntaxException) {

        }
        mSocket.connect()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.payment_fragment, container, false)
        view.findViewById<TextView>(R.id.type).text = orderInfo.name
        view.findViewById<TextView>(R.id.amount).text = orderInfo.price
        viewModel = ViewModelProvider(this).get(DelivererVm::class.java)


        var onOrder = Emitter.Listener {
            val userInfo = gson.fromJson(it[0].toString(), UserInfo::class.java)
            Log.d("Data", userInfo.name)
        }

        mSocket.on("newOrder", onOrder)


        view.findViewById<TextView>(R.id.btnplaceOder).setOnClickListener {
            TastyToast.makeText(
                context,
                "Placing Oder",
                TastyToast.LENGTH_SHORT,
                TastyToast.WARNING
            ).show()

            viewModel.placeOder(
                orderInfo.name,
                orderBy = orderInfo.orderedBy,
                price = orderInfo.price,
                latitude = latitude.toString(),
                longitude = longitude.toString()
            ).enqueue(object : Callback<Success> {
                override fun onResponse(call: Call<Success>, response: Response<Success>) {
                    if (response.isSuccessful) {
                        TastyToast.makeText(
                            context,
                            "Your order is Being processed!",
                            TastyToast.LENGTH_SHORT,
                            TastyToast.SUCCESS
                        ).show()
                        dismiss()
                    }

                }

                override fun onFailure(call: Call<Success>, t: Throwable) {
                    TastyToast.makeText(
                        context,
                        "Something Went Wrong",
                        TastyToast.LENGTH_SHORT,
                        TastyToast.ERROR
                    ).show()
                    dismiss()
                }

            })

        }
        return view
    }
}