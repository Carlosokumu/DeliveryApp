package com.example.deliveryapp.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.util.ObjectsCompat
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog
import com.example.deliveryapp.*
import com.example.deliveryapp.adapters.ServerModel
import com.example.deliveryapp.adapters.WorkAdapter
import com.example.deliveryapp.databinding.ActivityDelivererBinding
import com.example.deliveryapp.fragments.ProfileDialog
import com.example.deliveryapp.models.ClientInfo
import com.example.deliveryapp.models.DelivererInfo
import com.example.deliveryapp.utils.ObjectBox
import com.example.deliveryapp.utils.Settings
import com.example.deliveryapp.viewmodels.DelivererVm
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.sdsmdg.tastytoast.TastyToast
import com.tingyik90.snackprogressbar.SnackProgressBar
import io.socket.emitter.Emitter
import retrofit2.Call
import retrofit2.Response


class DelivererActivity : AppCompatActivity(), MaterialDialog.SingleButtonCallback {


    private lateinit var profileDialog: ProfileDialog
    private lateinit var viewModel: DelivererVm
    private lateinit var binding: ActivityDelivererBinding
    val gson: Gson = Gson()
    companion object {
       const val PERMISSION_REQUEST_CODE =201
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDelivererBinding.inflate(layoutInflater)
        setContentView(binding.root)
        profileDialog = ProfileDialog.newInstance(this)


        viewModel = ViewModelProvider(this).get(DelivererVm::class.java)

        val data =  ServerModel(longitude = "",latitude= "",clientName = "",phoneNumber= "")
        val jsonData = gson.toJson(data)
        (applicationContext as DeliveryApp).getSocket().emit("onDeliver", jsonData)







        val onAssign = Emitter.Listener {

            val clientInfo = gson.fromJson(it[0].toString(), ServerModel::class.java)
            Log.d("BroughtIn",clientInfo.clientName)

            runOnUiThread {
                TastyToast.makeText(
                    this,
                    "You have a new Notification",
                    TastyToast.LENGTH_SHORT,
                    TastyToast.WARNING
                ).show()
                if (!ObjectsCompat.equals(clientInfo.phoneNumber, "")) {
                    TastyToast.makeText(
                        this,
                        "You have a new Notification",
                        TastyToast.LENGTH_SHORT,
                        TastyToast.WARNING
                    ).show()

                }
            }
            if (!ObjectsCompat.equals(clientInfo.phoneNumber, "")) {
                ObjectBox.store.boxFor(ClientInfo::class.java).put(
                    ClientInfo(
                        longitude = clientInfo.longitude,
                        latitude = clientInfo.latitude,
                        clientName = clientInfo.clientName,
                        phoneNumber = ""
                    )
                )
            }
        }

        (applicationContext as DeliveryApp).getSocket().on("toPhone",onAssign)




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
                Log.d("permission", "permission denied to SEND_SMS - requesting it")
                val permissions = arrayOf<String>(Manifest.permission.SEND_SMS)
                requestPermissions(permissions, PERMISSION_REQUEST_CODE)
            }
        }

        binding.profileSection.setOnClickListener {
            profileDialog.show(supportFragmentManager,"Profile")


        }

//        binding.checkOut.setOnClickListener {
//            startActivity(Intent(this,MapsActivity::class.java))
//        }
        if (ObjectBox.store.boxFor(ClientInfo::class.java).all.isEmpty()){
            binding.checkNo.visibility = View.VISIBLE
        }
        else {
            binding.checkOut.visibility = View.GONE
            binding.recyclerWorks.adapter = WorkAdapter(ObjectBox.store.boxFor(ClientInfo::class.java).all,this).apply {
                notifyDataSetChanged()
            }


        }


        viewModel.getDelivers().enqueue(object :
            retrofit2.Callback<List<DelivererInfo>> {
            override fun onResponse(
                call: Call<List<DelivererInfo>>,
                response: Response<List<DelivererInfo>>
            ) {
                   if (response.isSuccessful){
                       Settings.setUserName(response.body()?.last()?.firstName!!)
                   }
            }

            override fun onFailure(call: Call<List<DelivererInfo>>, t: Throwable) {

            }
        })

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
        Settings.loggedAsDeliverer(false)
        Snackbar.make(findViewById(android.R.id.content)!!, "Logging Out..", Snackbar.LENGTH_SHORT).show()
        startActivity(Intent(this, SignIn::class.java))
        this.finish()
    }
}