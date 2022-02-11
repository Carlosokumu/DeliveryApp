package com.example.deliveryapp

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.afollestad.materialdialogs.MaterialDialog
import com.example.deliveryapp.fragments.ProfileDialog
import com.google.firebase.auth.FirebaseAuth
import kotlin.properties.Delegates

class HelpDialog: DialogFragment() {




    companion object {
        private lateinit  var callback: MaterialDialog.SingleButtonCallback
        lateinit var whichHelp: String

        fun newInstance(buttonCallback: MaterialDialog.SingleButtonCallback,whichHelp: String): HelpDialog {
            callback = buttonCallback
            this.whichHelp = whichHelp
            return  HelpDialog()
        }
    }




    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext()).inflate(
            R.layout.help_layout,
            null,
            false
        )

        val helpText = view.findViewById<TextView>(R.id.helpDescription)

       when(whichHelp){
           "LogAsAdmin" ->{
               helpText.setText(getString(R.string.helpLogAsdmin))
           }
           "SignUpAsAdmin" ->{
               helpText.setText(getString(R.string.helpsignUpAsAdmin))
           }
       }

        return  MaterialDialog.Builder(requireActivity()).customView(view, false)
            .positiveText("Got it")
            .onPositive(callback)
            .backgroundColor(resources.getColor(R.color.whiteuniv))
            //  .btnSelector(R.color.whiteuniv)
            .build()
    }
}