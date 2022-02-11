package com.example.deliveryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryapp.models.NotificationData
import com.example.deliveryapp.interfaces.OnItemListener
import com.example.deliveryapp.R

class NotificationAdapter(val notificationList: ArrayList<NotificationData>) : RecyclerView.Adapter<BaseViewHolder>() {

    private  lateinit var listener: OnItemListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.notification_layout,parent,false)
        return  BaseViewHolder(view)
    }



    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.whatOder).setText(notificationList[position].whatOrdered)
        holder.itemView.findViewById<TextView>(R.id.message).setText(notificationList[position].message)
    }



    override fun getItemCount(): Int {
        return notificationList.size
    }




}