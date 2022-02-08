package com.example.deliveryapp

import android.content.Intent
import android.os.Bundle
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughActivity
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughCard


class WalkThrough: FancyWalkthroughActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fancywalkthroughCard1 = FancyWalkthroughCard(
            "Find Restaurant",
            "Find the best restaurant in your neighborhood.",
        )
        val fancywalkthroughCard2 = FancyWalkthroughCard(
            "Pick the best",
            "Pick the right place using trusted ratings and reviews.",
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
         startActivity(Intent(this,SignIn::class.java))
    }

}