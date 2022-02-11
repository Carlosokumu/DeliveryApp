package com.example.deliveryapp

import AddRemainder
import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResultListener
import com.example.deliveryapp.databinding.FragmentRemainderBinding
import com.example.deliveryapp.utils.Extensions.showReminderInMap
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.material.snackbar.Snackbar
import com.sdsmdg.tastytoast.TastyToast

class ReminderFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {


    //
    private var map: GoogleMap? = null
    private var _binding: FragmentRemainderBinding? = null

    private lateinit var locationManager: LocationManager
    private lateinit var repo: RemainderRepo
    // private lateinit var latLng: com.example.mombasa.interfaces.LatLng


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            // latLng = context as com.example.mombasa.interfaces.LatLng
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement LatLng")
        }
    }

    companion object {

        private const val MY_LOCATION_REQUEST_CODE = 329

        private const val EXTRA_LAT_LNG = "EXTRA_LAT_LNG"
        private const val EXTRA_ZOOM = "EXTRA_ZOOM"

        fun newInstance() = ReminderFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // val reminder = RemainderRepo(latLng=)
        repo = (activity?.applicationContext as DeliveryApp).getRepo()
        setFragmentResultListener(
            "REQUEST_KEY"
        ) { requestKey, bundle ->
            val result = bundle.getBoolean("SUCESS")
            if (result) {
                showReminders()
                val reminder = repo.getLast()

                Log.d("Testing1", reminder?.latLng.toString())

                //Log.d("Testing2", reminder?.lat.toString())
                map?.moveCamera(CameraUpdateFactory.newLatLngZoom(reminder!!.latLng!!, 15f))
                //Snackbar.make(main, R.string.reminder_added_success, Snackbar.LENGTH_LONG).show()
                TastyToast.makeText(
                    requireContext(),
                    getString(R.string.reminder_added_success),
                    TastyToast.LENGTH_SHORT,
                    TastyToast.SUCCESS
                )

            }
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRemainderBinding.inflate(layoutInflater, container, false)
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MY_LOCATION_REQUEST_CODE
            )
        }
        _binding?.newReminder?.visibility = View.GONE
        _binding?.currentLocation?.visibility = View.GONE
        locationManager =
            requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager


        _binding?.newReminder?.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.add(R.id.myContainer,AddRemainder())?.commit()
            map?.run {
                //latLng.onLatLong(cameraPosition.target, cameraPosition.zoom)
            }

            activity?.supportFragmentManager?.beginTransaction()?.add(
                R.id.fragment_container_view, AddRemainder()
            )
//                activity?.supportFragmentManager?.commit {
//                    setCustomAnimations(
//                        R.anim.slide_in,
//                        R.anim.fade_out,
//                        R.anim.fade_in,
//                        R.anim.slide_out
//                    )
//                    setReorderingAllowed(true)
//                    replace<AddRemainder>(R.id.fragment_container_view,args =  Bundle().apply {
//                        map?.run {
//                            putParcelable(EXTRA_LAT_LNG,cameraPosition.target)
//                            putFloat(EXTRA_ZOOM,cameraPosition.zoom)
//                        }
//
//                    })
            //addToBackStack(this.javaClass.simpleName)
        }

    return _binding!!.root
}


override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
    mapFragment.getMapAsync(this)
}





override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
) {
    if (requestCode == MY_LOCATION_REQUEST_CODE) {
        onMapAndPermissionReady()
    }


}


override fun onMapReady(map: GoogleMap) {
    this.map = map


    //Map Configurations
    map.run {
        uiSettings.isMapToolbarEnabled = false
        uiSettings.isMyLocationButtonEnabled = false
        setOnMarkerClickListener(this@ReminderFragment)
    }
    animateCamera()
    onMapAndPermissionReady()
    showRelativePosition()
}


private fun onMapAndPermissionReady() {

    if (map != null && ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        map?.isMyLocationEnabled = true
        _binding?.newReminder?.visibility = View.VISIBLE
        _binding?.currentLocation?.visibility = View.VISIBLE
        _binding?.currentLocation?.setOnClickListener {
            val bestProvider = locationManager.getBestProvider(Criteria(), false)
            val location = locationManager.getLastKnownLocation(bestProvider!!)
            if (location != null) {
                val latLng = LatLng(location.latitude, location.longitude)
                Toast.makeText(
                    requireContext(),
                    "Longitude" + latLng.longitude.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                map?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            }

        }
        showReminders()
        centerCamera()

    }

}



    @SuppressLint("MissingPermission")
    private  fun animateCamera(){
        val bestProvider = locationManager.getBestProvider(Criteria(), false)
        val location = locationManager.getLastKnownLocation(bestProvider!!)
        if (location != null) {
            val latLng = LatLng(location.latitude, location.longitude)
            Toast.makeText(
                requireContext(),
                "Longitude" + latLng.longitude.toString(),
                Toast.LENGTH_SHORT
            ).show()
            map?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        }
    }


override fun onMarkerClick(marker: Marker): Boolean {
    val reminder = repo.get(marker.tag as String)
    if (reminder != null) {
        showReminderRemoveAlert(reminder)
    }
    return true
}


/*

         Get the arguments and center the camera according to the LatLong passed

 */



private fun centerCamera() {
    if ( this.arguments != null && requireArguments().containsKey(EXTRA_LAT_LNG)) {
        val latLng = requireArguments().getParcelable<LatLng>(EXTRA_LAT_LNG) as LatLng
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))


    }
}


private fun showReminderRemoveAlert(reminder: Reminder) {
    val alertDialog = AlertDialog.Builder(requireContext()).create()
    alertDialog.run {
        setMessage(getString(R.string.reminder_removal_alert))
        setButton(
            AlertDialog.BUTTON_POSITIVE,
            getString(R.string.reminder_removal_alert_positive)
        ) { dialog, _ ->
            removeReminder(reminder)
            dialog.dismiss()
        }
        setButton(
            AlertDialog.BUTTON_NEGATIVE,
            getString(R.string.reminder_removal_alert_negative)
        ) { dialog, _ ->
            dialog.dismiss()
        }
        show()
    }

}

private fun removeReminder(reminder: Reminder) {

    repo.remove(reminder, success = {
        showReminders()
        Snackbar.make(requireView(), R.string.reminder_removed_success, Snackbar.LENGTH_LONG).show()
    },
        failure = {

            Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG).show()

        })

}

private fun showReminders() {
    map?.run {
        clear()
        for (reminder in repo.getAll()) {
            showReminderInMap(requireContext(), this, reminder)
        }

    }

}

private fun showRelativePosition() {

    if (map != null && ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        map?.isMyLocationEnabled = true


    }
}





}






