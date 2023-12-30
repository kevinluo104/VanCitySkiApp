package com.skiapp.vancityskiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class EasyRider extends AppCompatActivity {

    private CypressSingleton cypressSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_rider);

        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            cypressSingleton = CypressSingleton.getInstance(new ResultListener() {
                                @Override
                                public void onResultFetched() {
                                    ImageView imageView278 = findViewById(R.id.imageView278);
                                    switch(cypressSingleton.easyRider) {
                                        case "open":
                                            imageView278.setImageResource(R.drawable.greencheck);
                                            break;
                                        case "closed":
                                            imageView278.setImageResource(R.drawable.redx);
                                            break;
                                        case "standby":
                                            imageView278.setImageResource(R.drawable.triangle);
                                            break;
                                    }
                                    ImageView runway = findViewById(R.id.imageView279);
                                    setStatus(runway, cypressSingleton.runway);
                                    ImageView steezyRider = findViewById(R.id.imageView285);
                                    ImageView gnarlysDen = findViewById(R.id.imageView286);
                                    setStatus(steezyRider, cypressSingleton.steezyRider);
                                    setStatus(gnarlysDen, cypressSingleton.gnarlysDen);
                                    TextView runsOpen = findViewById(R.id.textView202);
                                    TextView terrainParksOpen = findViewById(R.id.textView419);
                                    runsOpen.setText("Runs Open: " + cypressSingleton.runsOpenEasyRider + "/1");
                                    terrainParksOpen.setText("Terrain Parks Open: " + cypressSingleton.terrainParksOpenEasyRider + "/2");
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
