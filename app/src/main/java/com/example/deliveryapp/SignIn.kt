package com.example.deliveryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.deliveryapp.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class SignIn : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var adminRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()
        adminRef = FirebaseDatabase.getInstance().reference.child("Admin")

        binding.ivLoginBack.setOnClickListener { onBackPressed() }
        binding.tvRegister.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.tvLoginAsAdmin.setOnClickListener {
            binding.tvLoginAsUser.visibility = View.VISIBLE
            binding.btnLogin.text = "Login Admin"
            binding.tvLoginAsAdmin.visibility = View.GONE
        }
        binding.tvLoginAsUser.setOnClickListener {
            binding.tvLoginAsAdmin.visibility = View.VISIBLE
            binding.tvLoginAsUser.visibility = View.GONE
            binding.btnLogin.text = "Sign In"
        }

        binding.btnLogin.setOnClickListener{ login() }

    }

    private fun login() {
        //Show dialog
//        val dialog = ProgressDialog(this)
//        dialog.startLoading()

        if (binding.btnLogin.text == "Login Admin") {
            //login admin
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etLoginPassword.text.toString().trim()
            loginAdmin(email,password)

        } else {
            //login user
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

    private fun loginAdmin(email: String, password: String) {
        adminRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    val dbEmail = snapshot.child("Email").value.toString()
                    val dbPassword = snapshot.child("password").value.toString()

                    if (email == dbEmail && password == dbPassword){
                        val intent = Intent(this@SignIn, MainActivity::class.java)
                        startActivity(intent)

                        Toast.makeText(this@SignIn,"Admin Sign In Successful",
                            Toast.LENGTH_SHORT).show()
                    }else {
                        Toast.makeText(this@SignIn,"Kindly Use Correct credentials",
                            Toast.LENGTH_SHORT).show()
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignIn,"Error${error.message}",
                    Toast.LENGTH_SHORT).show()
            }
        })
    }
}