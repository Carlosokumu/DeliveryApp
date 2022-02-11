package com.example.deliveryapp.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.ObjectsCompat
import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog
import com.example.deliveryapp.*
import com.example.deliveryapp.databinding.ActivityDashBoardBinding
import com.example.deliveryapp.fragments.ProfileDialog
import com.example.deliveryapp.models.NotificationData
import com.example.deliveryapp.models.UserInfo
import com.example.deliveryapp.utils.ObjectBox
import com.example.deliveryapp.utils.Settings
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.sdsmdg.tastytoast.TastyToast
import com.tingyik90.snackprogressbar.SnackProgressBar
import com.tingyik90.snackprogressbar.SnackProgressBarManager
import io.socket.client.Socket
import io.socket.emitter.Emitter


class DashBoard : AppCompatActivity(), View.OnClickListener, MaterialDialog.SingleButtonCallback {

    private lateinit var profileDialog: ProfileDialog
    lateinit var mSocket: Socket
    private lateinit var binding:  ActivityDashBoardBinding
    val gson: Gson = Gson()



    companion object {
        const val CODE = 413
    }
    private lateinit var snackProgressBarManager: SnackProgressBarManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val data =  UserInfo(name = "",what = "",userId = FirebaseAuth.getInstance().currentUser?.email!!)
        val jsonData = gson.toJson(data)

        (applicationContext as DeliveryApp).getSocket().emit("onOrder", jsonData)

        val onOrder = Emitter.Listener {

            val userInfo= gson.fromJson(it[0].toString(), UserInfo::class.java)

            runOnUiThread {
                if (!ObjectsCompat.equals(userInfo.name,"")){
                    TastyToast.makeText(
                        this,
                        "You have a new Notification",
                        TastyToast.LENGTH_SHORT,
                        TastyToast.WARNING
                    ).show()
                }
            }
            if (!ObjectsCompat.equals(userInfo.name,"")){
                ObjectBox.store.boxFor(NotificationData::class.java).put(NotificationData(whatOrdered =userInfo.what,message = "Oder is  on the Way"))
            }

            Log.d("Data",userInfo.name)
        }

        (applicationContext as DeliveryApp).getSocket().on("newOrder",onOrder)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
                Log.d("permission", "permission denied to SEND_SMS - requesting it")
                val permissions = arrayOf<String>(Manifest.permission.SEND_SMS)
                requestPermissions(permissions, DelivererActivity.PERMISSION_REQUEST_CODE)
            }
        }

        profileDialog = ProfileDialog.newInstance(this)

        binding.cakes.setOnClickListener(this)
        binding.dinner.setOnClickListener(this)
        binding.drinks.setOnClickListener(this)
        binding.profileSection.setOnClickListener(this)
        binding.deliverer.setOnClickListener(this)
        binding.notifications.setOnClickListener(this)
        //binding.location.setOnClickListener(this)
        snackProgressBarManager = SnackProgressBarManager(binding.parent)
            .setProgressBarColor(R.color.colorAccent)
            .setOverlayLayoutAlpha(0.6f);


    }

    override fun onClick(view: View) {
        when(view){
            binding.cakes -> {
                val intent = Intent(this, ItemsManager::class.java).putExtra("WHAT", "cake")
                startActivity(intent)
            }
            binding.dinner -> {
                val intent = Intent(this, ItemsManager::class.java).putExtra("WHAT", "dinner")
                startActivity(intent)
            }
            binding.drinks -> {
                val intent = Intent(this, ItemsManager::class.java).putExtra("WHAT", "drinks")
                startActivity(intent)
            }
            binding.profileSection -> {
                profileDialog.show(supportFragmentManager, "profile")
            }
            binding.deliverer -> {
                val intent = Intent(this, RegisterDeliverer::class.java)
                startActivity(intent)
            }
            binding.notifications -> {
                val intent = Intent(this, NotificationActivity::class.java)
                startActivity(intent)
            }
//            binding.location -> {
//                val intent = Intent(this, MapsActivity::class.java)
//                startActivity(intent)
//            }
        }
    }

    override fun onClick(dialog: MaterialDialog, which: DialogAction) {
        logOut()
    }



    private  fun logOut(){
        FirebaseAuth.getInstance().signOut()

        val snackProgressBar = SnackProgressBar(
            SnackProgressBar.TYPE_CIRCULAR,
            "Logging out..."
        )
            .setSwipeToDismiss(false)
        Settings.loggedasCustomer(false)
        Settings.loggedAsAdmin(false)
        Settings.loggedAsDeliverer(false)
        snackProgressBarManager.show(snackProgressBar, SnackProgressBarManager.LENGTH_LONG)
        Snackbar.make(findViewById(android.R.id.content)!!, "Logging Out..", Snackbar.LENGTH_SHORT).show()
        startActivity(Intent(this, SignIn::class.java))
        this.finish()
    }



}