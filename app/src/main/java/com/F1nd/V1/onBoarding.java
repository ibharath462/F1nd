package com.F1nd.V1;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughActivity;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughCard;

import java.util.ArrayList;
import java.util.List;

public class onBoarding extends FancyWalkthroughActivity {

    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_on_boarding);
        prefs = getSharedPreferences("f1nd.initial.bharath.suck", MODE_PRIVATE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;

        height = (height > 2000) ? 1024 : 768;

        Intent ti =getIntent();
        boolean isFromMA = ti.getBooleanExtra("isFromMA",false);

        if(prefs.getBoolean("firstrun", true) || isFromMA){

            FancyWalkthroughCard fancywalkthroughCard1 = new FancyWalkthroughCard("Find Restaurant", "Find the best restaurant in your neighborhood.",R.drawable.ap);
            FancyWalkthroughCard fancywalkthroughCard2 = new FancyWalkthroughCard("Pick the best", "Pick the right place using trusted ratings and reviews.",R.drawable.ap);
            FancyWalkthroughCard fancywalkthroughCard3 = new FancyWalkthroughCard("Choose your meal", "Easily find the type of food you're craving.",R.drawable.ap);
            FancyWalkthroughCard fancywalkthroughCard4 = new FancyWalkthroughCard("Meal is on the way", "Get ready and comfortable while our biker bring your meal at your door.",R.drawable.ap);



            fancywalkthroughCard1.setBackgroundColor(R.color.white);
            fancywalkthroughCard1.setIconLayoutParams(2048,height,0,0,0,0);
            fancywalkthroughCard2.setBackgroundColor(R.color.white);
            fancywalkthroughCard2.setIconLayoutParams(2048,height,0,0,0,0);
            fancywalkthroughCard3.setBackgroundColor(R.color.white);
            fancywalkthroughCard3.setIconLayoutParams(2048,height,0,0,0,0);

            fancywalkthroughCard4.setIconLayoutParams(2048,height,0,0,0,0);
            List<FancyWalkthroughCard> pages = new ArrayList<>();

            pages.add(fancywalkthroughCard1);
            pages.add(fancywalkthroughCard2);
            pages.add(fancywalkthroughCard3);
            pages.add(fancywalkthroughCard4);

            for (FancyWalkthroughCard page : pages) {
                page.setTitleColor(R.color.black);
                fancywalkthroughCard4.setBackgroundColor(R.color.white);
                page.setDescriptionColor(R.color.black);
            }
            setFinishButtonTitle("Get Started");
            showNavigationControls(true);
            setColorBackground(R.color.colorPrimaryDark );
            //setImageBackground(R.drawable.dictionary);
            setInactiveIndicatorColor(R.color.grey_600);

            setActiveIndicatorColor(R.color.colorAccent);
            setOnboardPages(pages);

        }else{
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }





    }

    @Override
    public void onFinishButtonPressed() {

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }




}
