package com.skiapp.vancityskiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MidwayChair extends AppCompatActivity {

    private CypressSingleton cypressSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midway_chair);
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            cypressSingleton = CypressSingleton.getInstance(new ResultListener() {
                                @Override
                                public void onResultFetched() {
                                    ImageView imageView305 = findViewById(R.id.imageView305);
                                    switch(cypressSingleton.midwayChair) {
                                        case "open":
                                            imageView305.setImageResource(R.drawable.greencheck);
                                            break;
                                        case "closed":
                                            imageView305.setImageResource(R.drawable.redx);
                                            break;
                                        case "standby":
                                            imageView305.setImageResource(R.drawable.triangle);
                                            break;
                                    }

                                    ImageView imageView306 = findViewById(R.id.imageView306);
                                    ImageView imageView315 = findViewById(R.id.imageView315);
                                    ImageView imageView316 = findViewById(R.id.imageView316);
                                    ImageView imageView317 = findViewById(R.id.imageView317);
                                    setStatus(imageView306, cypressSingleton.shuttle);
                                    setStatus(imageView315, cypressSingleton.blaster);
                                    setStatus(imageView316, cypressSingleton.hutRun);
                                    setStatus(imageView317, cypressSingleton.webbSite);

                                    TextView textView315 = findViewById(R.id.textView315);
                                    textView315.setText("Runs Open: " + cypressSingleton.runsOpenMidwayChair + "/4");
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
