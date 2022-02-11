package com.example.deliveryapp.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.deliveryapp.R
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughActivity
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughCard


class WalkThrough: FancyWalkthroughActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fancywalkthroughCard1 = FancyWalkthroughCard(
            "Welcome",
            "Welcome to our Restaurant.We make you feel at home!",
        )
        val fancywalkthroughCard2 = FancyWalkthroughCard(
            "Pick the best",
            "Pick  what's  right for you  based on our Samples.",
        )
        val fancywalkthroughCard3 = FancyWalkthroughCard(
            "Choose your meal",
            "Easily find the type of food you're craving.",

        )
        val fancywalkthroughCard4 = FancyWalkthroughCard(
            "Meal is on the way",
            "Get ready and comfortable while our biker bring your meal at your door.",

        )

        fancywalkthroughCard1.setBackgroundColor(R.color.white)
        fancywalkthroughCard1.setIconLayoutParams(300, 300, 0, 0, 0, 0)
        fancywalkthroughCard1.setDisplaySkip(true)
        fancywalkthroughCard2.setBackgroundColor(R.color.white)
        fancywalkthroughCard2.setIconLayoutParams(300, 300, 0, 0, 0, 0)
        fancywalkthroughCard2.setDisplaySkip(true)
        fancywalkthroughCard3.setBackgroundColor(R.color.white)
        fancywalkthroughCard3.setIconLayoutParams(300, 300, 0, 0, 0, 0)
        fancywalkthroughCard3.setDisplaySkip(true)
        fancywalkthroughCard4.setBackgroundColor(R.color.white)
        fancywalkthroughCard4.setIconLayoutParams(300, 300, 0, 0, 0, 0)
        fancywalkthroughCard4.setDisplaySkip(true)
        val pages: MutableList<FancyWalkthroughCard> = ArrayList()

        pages.add(fancywalkthroughCard1)
        pages.add(fancywalkthroughCard2)
        pages.add(fancywalkthroughCard3)
        pages.add(fancywalkthroughCard4)

        for (page in pages) {
            page.setTitleColor(R.color.black)
            page.setDescriptionColor(R.color.black)
        }
        setFinishButtonTitle("Get Started")
        showNavigationControls(true)
        //setColorBackground(R.color.colorGreen)
        setImageBackground(R.drawable.restaurant);
        //setImageBackground(R.drawable.restaurant);
        setInactiveIndicatorColor(R.color.grey_600)
        setActiveIndicatorColor(R.color.colorGreen)
        setOnboardPages(pages)

    }


    override fun onFinishButtonPressed() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), MY_LOCATION_REQUEST_CODE)
        }
        else{
            startActivity(Intent(this, SignIn::class.java))
        }

    }


    companion object {
        const val  MY_LOCATION_REQUEST_CODE=2122
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_LOCATION_REQUEST_CODE){
            startActivity(Intent(this, SignIn::class.java))
        }
    }

}