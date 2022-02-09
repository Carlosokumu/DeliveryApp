package com.example.deliveryapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryapp.OnItemListener
import com.example.deliveryapp.R
import com.example.deliveryapp.fragments.PaymentFragment
import com.example.deliveryapp.models.CakeModel
import com.example.deliveryapp.utils.Extensions.asOderInfo
import com.google.firebase.auth.FirebaseAuth

class CakesAdapter(val cakesList: List<CakeModel>): RecyclerView.Adapter<BaseViewHolder>() {


    private  lateinit var listener: OnItemListener

    override  fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
          val view = LayoutInflater.from(parent.context).inflate(R.layout.row_items,
        parent,
        false)
          return BaseViewHolder(view)
    }

    fun setListener(listener: OnItemListener){
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return cakesList.size
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            PaymentFragment.newInstance(cakesList[position].asOderInfo(FirebaseAuth.getInstance().currentUser?.displayName!!)).show((holder.itemView.context as AppCompatActivity).supportFragmentManager,"Payment")
        }

    }
}

class Cake(name: String) {

}
