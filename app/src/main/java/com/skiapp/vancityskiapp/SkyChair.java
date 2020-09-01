package com.skiapp.vancityskiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SkyChair extends AppCompatActivity {

    private CypressSingleton cypressSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sky_chair);
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            cypressSingleton = CypressSingleton.getInstance(new ResultListener() {
                                @Override
                                public void onResultFetched() {
                                    ImageView imageView288 = findViewById(R.id.imageView288);
                                    switch(cypressSingleton.skyChair) {
                                        case "open":
                                            imageView288.setImageResource(R.drawable.greencheck);
                                            break;
                                        case "closed":
                                            imageView288.setImageResource(R.drawable.redx);
                                            break;
                                        case "standby":
                                            imageView288.setImageResource(R.drawable.triangle);
                                            break;
                                    }
                                    ImageView imageView289 = findViewById(R.id.imageView289);
                                    ImageView imageView299 = findViewById(R.id.imageView299);
                                    ImageView imageView300 = findViewById(R.id.imageView300);
                                    ImageView imageView301 = findViewById(R.id.imageView301);
                                    ImageView imageView302 = findViewById(R.id.imageView302);
                                    ImageView imageView303 = findViewById(R.id.imageView303);
                                    setStatus(imageView289, cypressSingleton.horseflyCanyon);
                                    setStatus(imageView299, cypressSingleton.T33);
                                    setStatus(imageView300, cypressSingleton.glades);
                                    setStatus(imageView301, cypressSingleton.ripcord);
                                    setStatus(imageView302, cypressSingleton.tomcat);
                                    setStatus(imageView303, cypressSingleton.topGun);
                                    TextView textView210 = findViewById(R.id.textView210);
                                    textView210.setText("Runs Open: " + cypressSingleton.runsOpenSkyChair + "/6");
                                }
                            }, getApplicationContext());
                            cypressSingleton.startThread();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }.start();
    }

    public void setStatus(ImageView image, String status) {
        switch (status) {
            case "open":
                image.setImageResource(R.drawable.greencheck);
                return;
            case "closed":
                image.setImageResource(R.drawable.redx);
                return;
            case "standby":
                image.setImageResource(R.drawable.triangle);
                return;
        }
    }
}
