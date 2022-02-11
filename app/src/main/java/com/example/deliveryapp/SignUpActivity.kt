package com.example.deliveryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog
import com.example.deliveryapp.databinding.ActivitySignUpBinding
import com.example.deliveryapp.fragments.ProfileDialog
import com.example.deliveryapp.utils.Extensions.hideBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity(), MaterialDialog.SingleButtonCallback {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var profileDialog: HelpDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideBar()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        profileDialog = HelpDialog.newInstance(this,"SignUpAsAdmin")
        mAuth = FirebaseAuth.getInstance()
        binding.ivRegBack.setOnClickListener { onBackPressed() }
        binding.tvLogin.setOnClickListener {
            val intent = Intent(this,SignIn::class.java)
            startActivity(intent)
        }

        binding.needHelp.setOnClickListener{
            profileDialog.show(supportFragmentManager,"help")
        }
        binding.tvLoginAsAdmin.setOnClickListener{
            startActivity(Intent(this,SignupAdmin::class.java))
        }
        binding.btnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val firstName = binding.etFirstName.text.toString().trim()
        val lastName = binding.etLastName.text.toString().trim()
        val email = binding.etRegisterEmail.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val cPassword = binding.etCPassword.text.toString().trim()

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
        } else if (phone.isEmpty()) {
            Toast.makeText(this, "Phone is empty", Toast.LENGTH_SHORT).show()
        } else if (phone.isNotEmpty() && phone.length < 10) {
            Toast.makeText(this, "Phone cannot be less than 10", Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Password is empty", Toast.LENGTH_SHORT).show()
        } else if (cPassword.isEmpty()) {
            Toast.makeText(this, "Confirm Your Password", Toast.LENGTH_SHORT).show()
        } else if (cPassword.isNotEmpty() && cPassword != password) {
            Toast.makeText(this, "Passwords Do not Match", Toast.LENGTH_SHORT).show()
        } else {
            //register with email and pwd
                binding.btnRegister.setText("Registering..")
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                //progress bar
//                val dialog = ProgressDialog(this)
//                dialog.startLoading()
                if (task.isSuccessful) {

                    //Store to database
                    val uid = mAuth.currentUser!!.uid
                    databaseReference = FirebaseDatabase.getInstance().reference
                    // val user = Users(firstName, lastName, email, phone, password)
                    //databaseReference.child(phone).setValue(user).addOnSuccessListener {
                    Settings.loggedasCustomer(true)
                    val userMap = HashMap<String, Any>()
                    userMap["userID"] = uid
                    userMap["firstName"] = firstName
                    userMap["lastName"] = lastName
                    userMap["email"] = email
                    userMap["phone"] = phone
                    userMap["password"] = password

                    databaseReference.child("users").child(uid)
                        .updateChildren(userMap).addOnCompleteListener {
                            val intent = Intent(this, DashBoard::class.java)
                            startActivity(intent)
                            Toast.makeText(
                                this, "Registration Successful",
                                Toast.LENGTH_SHORT
                            ).show()
                            //dismiss dialog
                        }
                    this.finish()

                } else {
                    binding.btnRegister.text = "Sign Up"
                    Toast.makeText(
                        this, "Error",
                        Toast.LENGTH_SHORT
                    ).show()
                    //dismiss dialog
                    //dialog.isDismiss()
                }
            }
        }
    }

    override fun onClick(dialog: MaterialDialog, which: DialogAction) {
        dialog.dismiss()
    }
}