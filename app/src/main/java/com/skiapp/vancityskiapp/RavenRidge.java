package com.skiapp.vancityskiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RavenRidge extends AppCompatActivity {

    private CypressSingleton cypressSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raven_ridge);
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            cypressSingleton = CypressSingleton.getInstance(new ResultListener() {
                                @Override
                                public void onResultFetched() {
                                    ImageView imageView245 = findViewById(R.id.imageView245);
                                    switch(cypressSingleton.ravenRidge) {
                                        case "open":
                                            imageView245.setImageResource(R.drawable.greencheck);
                                            break;
                                        case "closed":
                                            imageView245.setImageResource(R.drawable.redx);
                                            break;
                                        case "standby":
                                            imageView245.setImageResource(R.drawable.triangle);
                                            break;
                                    }
                                    ImageView imageView246 = findViewById(R.id.imageView246);
                                    ImageView imageView265 = findViewById(R.id.imageView265);
                                    ImageView imageView266 = findViewById(R.id.imageView266);
                                    ImageView imageView267 = findViewById(R.id.imageView267);
                                    ImageView imageView268 = findViewById(R.id.imageView268);
                                    ImageView imageView269 = findViewById(R.id.imageView269);
                                    ImageView imageView270 = findViewById(R.id.imageView270);
                                    ImageView imageView271 = findViewById(R.id.imageView271);
                                    ImageView imageView272 = findViewById(R.id.imageView272);
                                    ImageView imageView273 = findViewById(R.id.imageView273);
                                    ImageView imageView274 = findViewById(R.id.imageView274);
                                    ImageView imageView275 = findViewById(R.id.imageView275);
                                    ImageView imageView276 = findViewById(R.id.imageView276);
                                    setStatus(imageView246, cypressSingleton.threeBears);
                                    setStatus(imageView265, cypressSingleton.bennys);
                                    setStatus(imageView266, cypressSingleton.crazyRaven);
                                    setStatus(imageView267, cypressSingleton.lowerCoyote7);
                                    setStatus(imageView268, cypressSingleton.rideout);
                                    setStatus(imageView269, cypressSingleton.bilodeau);
                                    setStatus(imageView270, cypressSingleton.firstSun);
                                    setStatus(imageView271, cypressSingleton.shoreGlades);
                                    setStatus(imageView272, cypressSingleton.shoreLine);
                                    setStatus(imageView273, cypressSingleton.upperCoyote7);
                                    setStatus(imageView274, cypressSingleton.blackFly);
                                    setStatus(imageView275, cypressSingleton.backOnBlack);
                                    setStatus(imageView276, cypressSingleton.meteor);
                                    TextView textView185 = findViewById(R.id.textView185);
                                    textView185.setText("Runs Open: " + cypressSingleton.runsOpenRavenRidge + "/13");
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
