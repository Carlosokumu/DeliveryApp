package com.example.deliveryapp.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryapp.*
import com.example.deliveryapp.fragments.ClientFragmentInfo
import com.example.deliveryapp.fragments.PaymentFragment
import com.example.deliveryapp.models.UserInfo
import com.example.deliveryapp.utils.Extensions.asOderInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson

class DeliverersAdapters(val cakes: List<DelivererInfo>, val context: Context) :
    RecyclerView.Adapter<BaseViewHolder>(), onUserInfo {
    val gson: Gson = Gson()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.deliverer_row,
            parent,
            false
        )
        return BaseViewHolder(view)
    }


    override fun getItemCount(): Int {
        return cakes.size
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        holder.itemView.findViewById<TextView>(R.id.phoneD).text = cakes[position].phoneNumber
        holder.itemView.findViewById<TextView>(R.id.deliverer).text = cakes[position].firstName

        holder.itemView.findViewById<TextView>(R.id.txtAssign).setOnClickListener {
            ClientFragmentInfo.newInstance(
              this,cakes[position].phoneNumber
            ).show((holder.itemView.context as AppCompatActivity).supportFragmentManager, "ClientInfo")
        }
//            val data = ClientInfo(
//                longitude =
//            )
//            val jsonData = gson.toJson(data)
//            Log.d("ItemName", order[position].name)

    }

    override fun onUserInfo(clientName: String, longitude: String, latitude: String, phoneNumber: String) {
        val data = ServerModel(
                 longitude = longitude,
                 latitude = latitude,
                 clientName = clientName,
                 phoneNumber = phoneNumber
           )
            val jsonData = gson.toJson(data)
          (context.applicationContext as DeliveryApp).getSocket().emit("onDeliver", jsonData)

    }


}
