package com.example.deliveryapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryapp.DeliveryApp
import com.example.deliveryapp.models.NotificationData
import com.example.deliveryapp.models.OrderInfo
import com.example.deliveryapp.R
import com.example.deliveryapp.models.UserInfo
import com.example.deliveryapp.utils.ObjectBox
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.gson.Gson
import io.socket.client.Socket
import io.socket.emitter.Emitter

class OdersAdapter(val order: List<OrderInfo>, val context: Context) :
    RecyclerView.Adapter<BaseViewHolder>() {


    lateinit var mSocket: Socket
    val gson: Gson = Gson()
    var reference = FirebaseDatabase.getInstance().reference
    private lateinit var name: String
    private lateinit var userId: String


//    init {
//        try {
//            mSocket = IO.socket("https://chatsocketcarlos.herokuapp.com")
//
//        } catch (e: URISyntaxException) {
//              Log.d("Reason",e.reason)
//        }
//       // mSocket.connect()
//    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.row_oder,
            parent,
            false
        )
        return BaseViewHolder(view)
    }


    override fun getItemCount(): Int {
        return order.size
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.price).setText(order[position].price)
        holder.itemView.findViewById<TextView>(R.id.namef).setText(order[position].name)
        holder.itemView.findViewById<TextView>(R.id.oderer).setText(order[position].orderedBy)
//        holder.itemView.findViewById<TextView>(R.id.txtLongitude).setText(order[position].longitude)
//        holder.itemView.findViewById<TextView>(R.id.txtLatitude).setText(order[position].latitude)


        val query: Query =
            reference.child("users").child(FirebaseAuth.getInstance().currentUser!!.uid)


        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    name = snapshot.child("firstName").value.toString()
                    userId = snapshot.child("userID").value.toString()

                    Log.d("NAME", name)
                    Log.d("USERID", userId)

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })



        holder.itemView.findViewById<TextView>(R.id.confirmOder).setOnClickListener {
            val data = UserInfo(
                name = name,
                what = order[position].name,
                userId = order[position].orderedBy
            )
            val jsonData = gson.toJson(data)
            Log.d("ItemName", order[position].name)
            (context.applicationContext as DeliveryApp).getSocket().emit("onOrder", jsonData)
        }

        val onOrder = Emitter.Listener {

            val userInfo = gson.fromJson(it[0].toString(), UserInfo::class.java)
            ObjectBox.store.boxFor(NotificationData::class.java).put(
                NotificationData(
                    whatOrdered = userInfo.what,
                    message = "Your Order has been Confirmed"
                )
            )
            Log.d("Data", userInfo.name)
        }

        (context.applicationContext as DeliveryApp).getSocket().on("newOrder", onOrder)

    }
}
