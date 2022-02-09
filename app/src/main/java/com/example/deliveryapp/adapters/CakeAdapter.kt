package com.example.deliveryapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryapp.R
import com.example.deliveryapp.models.CakeModel

class CakeAdapter(val cakesList: ArrayList<CakeModel>) : RecyclerView.Adapter<CakeAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindItems(cake: CakeModel){
            val tvCakeName = itemView.findViewById<TextView>(R.id.tvFoodName)
            val tvCakePrice = itemView.findViewById<TextView>(R.id.tvPrice)
            val ivCakeImage = itemView.findViewById<ImageView>(R.id.ivFood)

            tvCakeName.text = cake.name
            tvCakePrice.text = cake.price
            ivCakeImage.setImageResource(cake.image)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(cakesList[position])
    }

    override fun getItemCount(): Int {
        return cakesList.size
    }


}