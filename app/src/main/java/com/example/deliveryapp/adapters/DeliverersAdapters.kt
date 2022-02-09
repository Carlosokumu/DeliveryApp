package com.example.deliveryapp.adapters

import android.view.LayoutInflater
import android.view.TextureView
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryapp.DelivererInfo
import com.example.deliveryapp.R
import com.example.deliveryapp.fragments.PaymentFragment

class DeliverersAdapters(val cakes: List<DelivererInfo>): RecyclerView.Adapter<BaseViewHolder>() {




    override  fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.deliverer_row,
            parent,
            false)
        return BaseViewHolder(view)
    }



    override fun getItemCount(): Int {
        return cakes.size
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

             holder.itemView.findViewById<TextView>(R.id.deliverer).setText(cakes[position].name)
             holder.itemView.findViewById<TextView>(R.id.phoneD).setText(cakes[position].phone)
             // holder.itemView.findViewById<TextView>(R.id.phoneD).setText(cakes[position].phone)
    }
}