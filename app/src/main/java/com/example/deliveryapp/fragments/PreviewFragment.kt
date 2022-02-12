package com.example.deliveryapp.fragments

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.example.deliveryapp.R
import com.example.deliveryapp.activities.SignIn
import com.example.deliveryapp.activities.WalkThrough
import com.example.deliveryapp.models.OrderInfo
import com.example.deliveryapp.viewmodels.DelivererVm
import com.example.mombasa.geofence.LocationResult
import com.example.mombasa.geofence.MyLocation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase


class PreviewFragment(context: Context) : Fragment() {
    private lateinit var confirmDialog: ConfirmDialog

    private lateinit var viewModel: DelivererVm

    var longitude: Double = 0.0
    var latitude: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val image = requireArguments().getInt("image")
        val price = requireArguments().getString("price")
        val name = requireArguments().getString("name")
        val description = requireArguments().getString("description")


        val emailUser = FirebaseAuth.getInstance().currentUser?.email

        val view = inflater.inflate(R.layout.fragment_preview, container, false)
        view.findViewById<ImageView>(R.id.roundedImageView).setImageResource(image)
        view.findViewById<TextView>(R.id.foodDescription).text = description
        view.findViewById<TextView>(R.id.foodName).text = name
        view.findViewById<TextView>(R.id.priceTxt).text = price


        confirmDialog = ConfirmDialog.newInstance(
            OrderInfo(
                name = name!!,
                orderedBy = emailUser!!,
                price = price!!,
                latitude = latitude.toString(),
                longitude = longitude.toString()
            ), context = requireActivity()
        )


        view.findViewById<Button>(R.id.btnCart).setOnClickListener {

        }
        view.findViewById<Button>(R.id.btnOrder).setOnClickListener {
            confirmDialog.show(requireActivity().supportFragmentManager, "Confirm")
        }
        return view
    }

    companion object {


        fun newInstance(
            name: String,
            image: Int,
            price: String,
            description: String,context: Context
        ): PreviewFragment {
            val args = bundleOf(
                "name" to name,
                "price" to price,
                "description" to description,
                "image" to image
            )

            val fragment = PreviewFragment(context)
            fragment.arguments = args
            return fragment
        }
    }





}