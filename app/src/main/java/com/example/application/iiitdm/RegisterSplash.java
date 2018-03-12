package com.example.application.iiitdm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

/**
 * Created by S-Tech Computer on 10/07/2017.
 */

public class RegisterSplash extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.register_splash);
        Thread myThread=new Thread(){
            @Override
            public void run() {
                try
                {
                    sleep(1500);
                    Intent intent=new Intent(getApplicationContext(),Home.class);
                    startActivity(intent);
                    finish();
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
