package com.example.deliveryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog
import com.example.deliveryapp.adapters.FragmentAdapter
import com.example.deliveryapp.databinding.ActivityAdminSectionBinding
import com.example.deliveryapp.databinding.ActivityDashBoardBinding
import com.example.deliveryapp.fragments.ProfileDialog
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.tingyik90.snackprogressbar.SnackProgressBar
import com.tingyik90.snackprogressbar.SnackProgressBarManager

class AdminSection : AppCompatActivity(), MaterialDialog.SingleButtonCallback {


    private lateinit var binding: ActivityAdminSectionBinding
    private val mainPagerAdapter: FragmentAdapter by lazy {
        FragmentAdapter(supportFragmentManager)
    }
    private lateinit var profileDialog: ProfileDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminSectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        profileDialog = ProfileDialog.newInstance(this)



        binding.profileSection.setOnClickListener {
             profileDialog.show(supportFragmentManager,"Profile")
        }
        binding.viewpager.adapter = mainPagerAdapter
        binding.tabs.setupWithViewPager( binding.viewpager)

    }

    override fun onClick(dialog: MaterialDialog, which: DialogAction) {
        dialog.dismiss()
        FirebaseAuth.getInstance().signOut()

        val snackProgressBar = SnackProgressBar(
            SnackProgressBar.TYPE_CIRCULAR,
            "Logging out..."
        )
            .setSwipeToDismiss(false)
        Settings.loggedAsAdmin(false)
        Snackbar.make(findViewById(android.R.id.content)!!, "Logging Out..", Snackbar.LENGTH_LONG).show()
        startActivity(Intent(this, SignIn::class.java))
        this.finish()
    }
}