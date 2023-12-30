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
                                    ImageView status = findViewById(R.id.imageView245);
                                    switch(cypressSingleton.ravenRidge) {
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
                                    ImageView threeBears = findViewById(R.id.imageView246);
                                    ImageView bennys = findViewById(R.id.imageView265);
                                    ImageView crazyRaven = findViewById(R.id.imageView266);
                                    ImageView lowerCoyote7 = findViewById(R.id.imageView267);
                                    ImageView rideout = findViewById(R.id.imageView268);
                                    ImageView bilodeau = findViewById(R.id.imageView269);
                                    ImageView firstSun = findViewById(R.id.imageView270);
                                    ImageView shoreGlades = findViewById(R.id.imageView271);
                                    ImageView shoreLine = findViewById(R.id.imageView272);
                                    ImageView upperCoyote7 = findViewById(R.id.imageView273);
                                    ImageView blackFly = findViewById(R.id.imageView274);
                                    ImageView backOnBlack = findViewById(R.id.imageView275);
                                    ImageView meteor = findViewById(R.id.imageView276);
                                    setStatus(threeBears, cypressSingleton.threeBears);
                                    setStatus(bennys, cypressSingleton.bennys);
                                    setStatus(crazyRaven, cypressSingleton.crazyRaven);
                                    setStatus(lowerCoyote7, cypressSingleton.lowerCoyote7);
                                    setStatus(rideout, cypressSingleton.rideout);
                                    setStatus(bilodeau, cypressSingleton.bilodeau);
                                    setStatus(firstSun, cypressSingleton.firstSun);
                                    setStatus(shoreGlades, cypressSingleton.shoreGlades);
                                    setStatus(shoreLine, cypressSingleton.shoreLine);
                                    setStatus(upperCoyote7, cypressSingleton.upperCoyote7);
                                    setStatus(blackFly, cypressSingleton.blackFly);
                                    setStatus(backOnBlack, cypressSingleton.backOnBlack);
                                    setStatus(meteor, cypressSingleton.meteor);
                                    TextView runsOpen = findViewById(R.id.textView185);
                                    runsOpen.setText("Runs Open: " + cypressSingleton.runsOpenRavenRidge + "/13");
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
