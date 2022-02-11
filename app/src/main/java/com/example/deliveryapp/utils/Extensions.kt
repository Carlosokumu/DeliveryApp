package com.example.deliveryapp.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.deliveryapp.models.OrderInfo
import com.example.deliveryapp.R
import com.example.deliveryapp.Reminder
import com.example.deliveryapp.models.CakeModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*

object Extensions {

    fun CakeModel.asOderInfo(loggedUser: String) =
        OrderInfo(name = this.name,orderedBy = loggedUser,price = this.price,longitude = null,latitude = null)


    fun EditText.requestFocusWithKeyboard(context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (!hasFocus()) {
            requestFocus()
        }
        post {
            imm.showSoftInput(this, InputMethodManager.SHOW_FORCED)
        }

    }


    fun Activity.hideBar(){
        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        else{
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
            //just inCase the actionBar is present
            actionBar?.hide()
        }

    }


    fun showReminderInMap(context: Context, map: GoogleMap, reminder: Reminder) {

        if (reminder.latLng != null) {

            val latLng = reminder.latLng as LatLng

            val vectorToBitmap = vectorToBitmap(context.resources, R.drawable.ic_location)

            val marker = map.addMarker(MarkerOptions().position(latLng).icon(vectorToBitmap))

            marker?.tag = reminder.id

            if (reminder.radius != null) {

                val radius = reminder.radius as Double

                map.addCircle(
                    CircleOptions()
                    .center(reminder.latLng!!)
                    .radius(radius)
                    .strokeColor(ContextCompat.getColor(context, R.color.colorAccent))
                    .fillColor
                        (ContextCompat.getColor(context, R.color.colorAccent)))


            }

        }

    }
    fun vectorToBitmap(resources: Resources, @DrawableRes id: Int): BitmapDescriptor {

        val vectorDrawable = ResourcesCompat.getDrawable(resources, id, null)

        val bitmap = Bitmap.createBitmap(vectorDrawable!!.intrinsicWidth,
            vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)

        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)

        vectorDrawable.draw(canvas)

        return BitmapDescriptorFactory.fromBitmap(bitmap)

    }









}