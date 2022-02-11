package com.example.deliveryapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog
import com.example.deliveryapp.fragments.HelpDialog
import com.example.deliveryapp.utils.Settings
import com.example.deliveryapp.databinding.ActivitySignInBinding
import com.example.deliveryapp.utils.Extensions.hideBar
import com.google.firebase.auth.FirebaseAuth
import com.sdsmdg.tastytoast.TastyToast

class SignIn : AppCompatActivity(), MaterialDialog.SingleButtonCallback {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var profileDialog: HelpDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         hideBar()
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        profileDialog = HelpDialog.newInstance(this, "LogAsAdmin")
        mAuth = FirebaseAuth.getInstance()

        binding.ivLoginBack.setOnClickListener { onBackPressed() }
        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener{ login() }

        binding.tvLoginAsAdmin.setOnClickListener {
          profileDialog.show(supportFragmentManager,"help")
        }

    }

    private fun login() {
        //Show dialog
//        val dialog = ProgressDialog(this)
//        dialog.startLoading()

        val email = binding.etEmail.text.toString().trim()
        val password = binding.etLoginPassword.text.toString().trim()
        if (email.isEmpty() && password.isEmpty()) {
            Toast.makeText(this, "Fields are empty!", Toast.LENGTH_SHORT).show()
        } else if (email.isEmpty()) {
            Toast.makeText(this, "Email is empty!", Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Password is empty!", Toast.LENGTH_SHORT).show()
        } else {
            binding.btnLogin.setText("Signing in")
            //signing with email and pwd
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //end dialog
//                    dialog.isDismiss()
                         TastyToast.makeText(this,"Sucessfully Logged in",Toast.LENGTH_SHORT,TastyToast.SUCCESS).show()
                        val intent = Intent(this, DashBoard::class.java)
                        startActivity(intent)
                        this.finish()
                    Settings.loggedasCustomer(true)

                    } else {
                        TastyToast.makeText(this,"Something Went Wrong",Toast.LENGTH_SHORT,TastyToast.ERROR).show()
                         binding.btnLogin.text ="Sign in"
//                    val intent = Intent(this, DashBoard::class.java)
//                    startActivity(intent)
//                    finish()
                }
            }
        }
    }

    override fun onClick(dialog: MaterialDialog, which: DialogAction) {
        dialog.dismiss()
    }
}