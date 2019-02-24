package com.example.kamal.bmicalculator;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

/**
 * Created by ssaurel on 02/12/2016.
 */
public class SplashActivity extends AppCompatActivity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    Thread splashTread;
    SharedPref sharedPref, sharedColor;
    ImageView c1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref= new SharedPref(this);
        sharedColor = new SharedPref(this);
        if (sharedPref.loadNightModeState() == true)
        {
            setTheme(R.style.splashScreenTheme_Dark);
        } else if(sharedColor.loadColorRed()==true){
            setTheme(R.style.RedTheme);
        } else if(sharedColor.loadDefaultColor()==true){
            setTheme(R.style.AppTheme);
        } else if(sharedColor.loadColorOrange()==true){
            setTheme(R.style.OrangeTheme);
        } else if(sharedColor.loadColorSky()==true){
            setTheme(R.style.SkyTheme);
        } else if(sharedColor.loadColorGreen()==true){
            setTheme(R.style.GreenTheme);
        } else if(sharedColor.loadColorPink()==true){
            setTheme(R.style.PinkTheme);
        }
        else
        {
            setTheme(R.style.splashScreenTheme);

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splash();

    }

 /*   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent intent = new Intent(getApplicationContext(),
                MainActivity.class);
        startActivity(intent);
        finish();
    }*/

    public void splash(){
        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 2500) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(SplashActivity.this,
                            MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashActivity.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    SplashActivity.this.finish();
                }

            }
        };
        splashTread.start();

    }

    public void run(){
     new Handler().postDelayed(new Runnable() {

         /*
          * Showing splash screen with a timer. This will be useful when you
          * want to show case your app logo / company
          */

         @Override
         public void run() {
             // This method will be executed once the timer is over
             // Start your app main activity
             Intent i = new Intent(SplashActivity.this, MainActivity.class);
             startActivity(i);

             // close this activity
             finish();
         }
     }, SPLASH_TIME_OUT);
 }




}