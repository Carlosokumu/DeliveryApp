package com.example.deliveryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.deliveryapp.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignIn : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        binding.ivLoginBack.setOnClickListener { onBackPressed() }
        binding.tvRegister.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener{ login() }

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
            //signing with email and pwd
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //end dialog
//                    dialog.isDismiss()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
//                    Close Dialog
//                    dialog.isDismiss()
                    Toast.makeText(
                        this, "Error!${task.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}