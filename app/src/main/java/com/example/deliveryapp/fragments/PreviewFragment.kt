package com.example.deliveryapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import com.example.deliveryapp.R


class PreviewFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val image =requireArguments().getInt("image")
        val price =requireArguments().getString("price")
        val name = requireArguments().getString("name")
        val description = requireArguments().getString("description")

        val view = inflater.inflate(R.layout.fragment_preview, container, false)
        view.findViewById<ImageView>(R.id.roundedImageView).setImageResource(image)
        view.findViewById<TextView>(R.id.foodDescription).text = description
        view.findViewById<TextView>(R.id.foodName).text =  name
        view.findViewById<TextView>(R.id.priceTxt).text = price





        view.findViewById<Button>(R.id.btnCart).setOnClickListener {

        }
        view.findViewById<Button>(R.id.btnOrder).setOnClickListener {

        }
        return view
    }

   companion object {


       fun newInstance(name: String,image: Int,price: String,description: String): PreviewFragment {
           val args = bundleOf(
               "name" to  name,
               "price" to price,
                "description" to description,
               "image" to image
           )

           val fragment = PreviewFragment()
           fragment.arguments = args
           return fragment
       }
   }





}