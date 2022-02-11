package com.example.deliveryapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryapp.ClientInfo
import com.example.deliveryapp.MapsActivity
import com.example.deliveryapp.R

class  WorkAdapter(val clientInfo: List<ClientInfo>,val context: Context): RecyclerView.Adapter<BaseViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.deliverieslist,
            parent,
            false
        )
        return BaseViewHolder(view)
    }


    override fun getItemCount(): Int {
        return clientInfo.size
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        holder.itemView.findViewById<TextView>(R.id.client).text = clientInfo[position].clientName
        holder.itemView.findViewById<TextView>(R.id.addressLat).text = clientInfo[position].latitude
        holder.itemView.findViewById<TextView>(R.id.addressLong).text = clientInfo[position].longitude

        holder.itemView.findViewById<TextView>(R.id.geofence).setOnClickListener {
            context.startActivity(Intent(context, MapsActivity::class.java))
        }

    }
}