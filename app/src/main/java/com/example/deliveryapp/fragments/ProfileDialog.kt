package com.example.deliveryapp.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.afollestad.materialdialogs.MaterialDialog
import com.example.deliveryapp.R
import com.example.deliveryapp.utils.Settings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class ProfileDialog: DialogFragment() {

    var reference = FirebaseDatabase.getInstance().reference


    companion object {
        private lateinit  var callback: MaterialDialog.SingleButtonCallback

        fun newInstance(buttonCallback: MaterialDialog.SingleButtonCallback): ProfileDialog {
            callback = buttonCallback
            return ProfileDialog()
        }
    }




    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext()).inflate(
            R.layout.layout_profile,
            null,
            false
        )
        val query: Query = reference.child("users").child(FirebaseAuth.getInstance().currentUser!!.uid)


          query.addValueEventListener(object : ValueEventListener {
              override fun onDataChange(snapshot: DataSnapshot) {
                  if (snapshot.exists()) {
                      // dataSnapshot is the "issue" node with all children with id 0
                      val name = snapshot.child("firstName").value.toString()
                      view.findViewById<TextView>(R.id.text_view_name).text = name
                      Log.d("NAME",name)

                  }
              }

              override fun onCancelled(error: DatabaseError) {

              }

          })


        val user = FirebaseAuth.getInstance().currentUser
        if (user != null){
            if (Settings.isCustomer()!!){
                view.findViewById<TextView>(R.id.signedAs).text = "Customer"
            }else if(Settings.isAdmin()!!){
                view.findViewById<TextView>(R.id.signedAs).text = "Admin"
                view.findViewById<TextView>(R.id.text_adv).text = getString(R.string.currentSignIn)
            }
            else if(Settings.isDeliverer()!!){
                view.findViewById<TextView>(R.id.signedAs).text = "Rider"
                view.findViewById<TextView>(R.id.text_view_name).text = Settings.getUserName()
                view.findViewById<TextView>(R.id.text_adv).text = getString(R.string.signedUser)
            }

        }

        return  MaterialDialog.Builder(requireActivity()).customView(view, false)
            .positiveText("Sign out")
            .onPositive(callback)
            .backgroundColor(resources.getColor(R.color.whiteuniv))
            //  .btnSelector(R.color.whiteuniv)
            .build()
    }
}