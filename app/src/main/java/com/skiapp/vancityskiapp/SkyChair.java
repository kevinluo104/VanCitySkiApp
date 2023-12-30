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
                                    ImageView status = findViewById(R.id.imageView288);
                                    switch(cypressSingleton.skyChair) {
                                        case "open":
                                            status.setImageResource(R.drawable.greencheck);
                                            break;
                                        case "closed":
                                            status.setImageResource(R.drawable.redx);
                                            break;
                                        case "standby":
                                            status.setImageResource(R.drawable.triangle);
                                            break;
                                    }
                                    ImageView horseflyCanyon = findViewById(R.id.imageView289);
                                    ImageView T33 = findViewById(R.id.imageView299);
                                    ImageView glades = findViewById(R.id.imageView300);
                                    ImageView ripcord = findViewById(R.id.imageView301);
                                    ImageView tomcat = findViewById(R.id.imageView302);
                                    ImageView topGun = findViewById(R.id.imageView303);
                                    setStatus(horseflyCanyon, cypressSingleton.horseflyCanyon);
                                    setStatus(T33, cypressSingleton.T33);
                                    setStatus(glades, cypressSingleton.glades);
                                    setStatus(ripcord, cypressSingleton.ripcord);
                                    setStatus(tomcat, cypressSingleton.tomcat);
                                    setStatus(topGun, cypressSingleton.topGun);
                                    TextView runsOpen = findViewById(R.id.textView210);
                                    runsOpen.setText("Runs Open: " + cypressSingleton.runsOpenSkyChair + "/6");
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
