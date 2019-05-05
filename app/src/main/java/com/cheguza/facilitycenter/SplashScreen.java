package com.cheguza.facilitycenter;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.cheguza.facilitycenter.Home.HomeActivity;
import com.skyfishjy.library.RippleBackground;

public class SplashScreen extends AppCompatActivity {

    final static int SPLASH_TIME_OUT=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this,SelectUserActivity.class);
                startActivity(intent);

                finish();
            }
        },SPLASH_TIME_OUT);

        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.content);
        ImageView imageView=(ImageView)findViewById(R.id.centerImage);
        rippleBackground.startRippleAnimation();
    }
}
