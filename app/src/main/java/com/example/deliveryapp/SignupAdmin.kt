package com.example.deliveryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.deliveryapp.databinding.ActivitySignInBinding
import com.example.deliveryapp.databinding.ActivitySignUpBinding
import com.example.deliveryapp.databinding.ActivitySignupAdminBinding
import com.example.deliveryapp.utils.Extensions.hideBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sdsmdg.tastytoast.TastyToast


class SignupAdmin : AppCompatActivity() {


    private lateinit var binding: ActivitySignupAdminBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideBar()
        mAuth = FirebaseAuth.getInstance()
        binding = ActivitySignupAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegister.setOnClickListener {
            register()
        }
    }





    private fun register() {
        val firstName = binding.adFirstName.text.toString().trim()
        val lastName = binding.adLastName.text.toString().trim()
        val email = binding.adEmail.text.toString().trim()
        val password = binding.adPassword.text.toString().trim()
        val cPassword = binding.adCPassword.text.toString().trim()


        if (firstName.isEmpty() && lastName.isEmpty() && email.isEmpty() && email.isEmpty() &&
            password.isEmpty() && cPassword.isEmpty()
        ) {
            Toast.makeText(this, "Fields are empty", Toast.LENGTH_SHORT).show()
        } else if (firstName.isEmpty()) {
            Toast.makeText(this, "Firstname is empty", Toast.LENGTH_SHORT).show()
        } else if (lastName.isEmpty()) {
            Toast.makeText(this, "LastName is empty", Toast.LENGTH_SHORT).show()
        } else if (email.isEmpty()) {
            Toast.makeText(this, "Email is empty", Toast.LENGTH_SHORT).show()
        } else if (email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Enter valid Email address !", Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Password is empty", Toast.LENGTH_SHORT).show()
        } else if (cPassword.isEmpty()) {
            Toast.makeText(this, "Confirm Your Password", Toast.LENGTH_SHORT).show()
        } else if (cPassword.isNotEmpty() && cPassword != password) {
            Toast.makeText(this, "Passwords Do not Match", Toast.LENGTH_SHORT).show()
        } else {

            binding.btnRegister.setText("Registering..")
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                //progress bar
//                val dialog = ProgressDialog(this)
//                dialog.startLoading()
                if (task.isSuccessful) {

                    //Store to database
                    Settings.loggedAsAdmin(true)
                    val uid = mAuth.currentUser!!.uid
                    databaseReference = FirebaseDatabase.getInstance().reference
                    // val user = Users(firstName, lastName, email, phone, password)
                    //databaseReference.child(phone).setValue(user).addOnSuccessListener {
                    Settings.loggedAsAdmin(true)
                    val userMap = HashMap<String, Any>()
                    userMap["userID"] = uid
                    userMap["firstName"] = firstName
                    userMap["lastName"] = lastName
                    userMap["email"] = email
                    userMap["password"] = password
                    this.finish()

                    databaseReference.child("users").child(uid)
                        .updateChildren(userMap).addOnCompleteListener {

                            val intent = Intent(this, AdminSection::class.java)
                            startActivity(intent)
                            Toast.makeText(
                                this, "Registration Successful",
                                Toast.LENGTH_SHORT
                            ).show()
                            //dismiss dialog
                        }

                } else {
                    binding.btnRegister.setText("Become Admin")
                    Toast.makeText(
                        this, task.exception.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    //dismiss dialog
                    //dialog.isDismiss()
                }
            }
        }
    }
}