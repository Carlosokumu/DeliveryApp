package com.example.deliveryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryapp.interfaces.OnItemListener
import com.example.deliveryapp.R
import com.example.deliveryapp.fragments.PaymentFragment
import com.example.deliveryapp.models.CakeModel
import com.example.deliveryapp.utils.Extensions.asOderInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

class CakesAdapter(val cakesList: List<CakeModel>): RecyclerView.Adapter<BaseViewHolder>() {


    private  lateinit var listener: OnItemListener

    lateinit var mSocket: Socket
    val gson: Gson = Gson()


    override  fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
          val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlayout,
        parent,
        false)
          return BaseViewHolder(view)
    }



    init {
        try {
            mSocket = IO.socket("https://chatsocketcarlos.herokuapp.com")

        } catch (e: URISyntaxException) {

        }
        mSocket.connect()
    }

    fun setListener(listener: OnItemListener){
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return cakesList.size
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {


//        holder.itemView.setOnClickListener {
//            PaymentFragment.newInstance(cakesList[position].asOderInfo(FirebaseAuth.getInstance().currentUser?.email!!),context = holder.itemView.context).show((holder.itemView.context as AppCompatActivity).supportFragmentManager,"Payment")
//        }
//        val tvCakeName = holder.itemView.findViewById<TextView>(R.id.name)
//        val tvCakePrice = holder.itemView.findViewById<TextView>(R.id.price)
//        val ivCakeImage = holder.itemView.findViewById<de.hdodenhof.circleimageview.CircleImageView>(R.id.image)
//
//        tvCakeName.text = cakesList[position].name
//        tvCakePrice.text = cakesList[position].price
//        ivCakeImage.setImageResource(cakesList[position].image)


    }
}

class Cake(name: String) {

}
