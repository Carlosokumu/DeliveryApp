package com.example.deliveryapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.deliveryapp.R
import com.example.deliveryapp.interfaces.onUserInfo
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sdsmdg.tastytoast.TastyToast

class ClientFragmentInfo: BottomSheetDialogFragment() {


     private  lateinit var longitude: String
     private  lateinit var latitude:  String




 companion object {
     private lateinit var clientinfo: onUserInfo
     private  lateinit var phoneNumber: String
     fun newInstance(info: onUserInfo, phone: String): ClientFragmentInfo {
         this.clientinfo = info
         this.phoneNumber = phone
         return  ClientFragmentInfo()
     }

 }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.enter_details, container, false)

        val eLat = view.findViewById<EditText>(R.id.latitude)
        val eLong = view.findViewById<EditText>(R.id.longitude)
        val  eclientName = view.findViewById<EditText>(R.id.clientName)
        val  confirm = view.findViewById<Button>(R.id.confirm)


        confirm.setOnClickListener {
            if (eLat.text.toString().isEmpty() || eLong.text.toString().isEmpty() ||  eclientName.text.toString().isEmpty()){
                TastyToast.makeText(requireContext(),"Check Your Fields",TastyToast.LENGTH_SHORT,TastyToast.ERROR).show()
            }
            else {
                clientinfo.onUserInfo(eLat.text.toString(),eLong.text.toString(),eclientName.text.toString(),
                    phoneNumber)
                dismiss()

            }
        }


        return  view
    }
}