package com.skiapp.vancityskiapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class LionsExpress extends AppCompatActivity {

    private CypressSingleton cypressSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lions_express);

        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            cypressSingleton = CypressSingleton.getInstance(new ResultListener() {
                                @Override
                                public void onResultFetched() {

                                    ImageView status = findViewById(R.id.imageView180);
                                    switch(cypressSingleton.lionsExpress) {
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
                                    ImageView collins = findViewById(R.id.imageView181);
                                    ImageView catTrackLower = findViewById(R.id.imageView219);
                                    ImageView catTrackUpper = findViewById(R.id.imageView220);
                                    ImageView horizon = findViewById(R.id.imageView221);
                                    ImageView horizonByPass = findViewById(R.id.imageView222);
                                    ImageView humptyDumpty = findViewById(R.id.imageView223);
                                    ImageView hutch = findViewById(R.id.imageView224);
                                    ImageView lowerBowen = findViewById(R.id.imageView225);
                                    ImageView primaryPower = findViewById(R.id.imageView226);
                                    ImageView bowenFace = findViewById(R.id.imageView227);
                                    ImageView bowenWest = findViewById(R.id.imageView228);
                                    ImageView gibsons = findViewById(R.id.imageView229);
                                    ImageView moons = findViewById(R.id.imageView230);
                                    ImageView rainbow = findViewById(R.id.imageView231);
                                    ImageView slash = findViewById(R.id.imageView232);
                                    ImageView upperBowen = findViewById(R.id.imageView233);
                                    ImageView upperRainbow = findViewById(R.id.imageView234);
                                    ImageView bowenWestGlades = findViewById(R.id.imageView235);
                                    ImageView cratorGlades = findViewById(R.id.imageView236);
                                    ImageView darksideGlades = findViewById(R.id.imageView237);
                                    ImageView elevatorGlades = findViewById(R.id.imageView238);
                                    ImageView gibsonGlades = findViewById(R.id.imageView239);
                                    ImageView LTDGlades = findViewById(R.id.imageView240);
                                    ImageView underTheVolcanoGlades = findViewById(R.id.imageView241);

                                    setStatus(collins, cypressSingleton.collins);
                                    setStatus(catTrackLower, cypressSingleton.catTrackLower);
                                    setStatus(catTrackUpper, cypressSingleton.catTrackUpper);
                                    setStatus(horizon, cypressSingleton.horizon);
                                    setStatus(horizonByPass, cypressSingleton.horizonByPass);
                                    setStatus(humptyDumpty, cypressSingleton.humptyDumpty);
                                    setStatus(hutch, cypressSingleton.hutch);
                                    setStatus(lowerBowen, cypressSingleton.lowerBowen);
                                    setStatus(primaryPower, cypressSingleton.primaryPower);
                                    setStatus(bowenFace, cypressSingleton.bowenFace);
                                    setStatus(bowenWest, cypressSingleton.bowenWest);
                                    setStatus(gibsons, cypressSingleton.gibsons);
                                    setStatus(moons, cypressSingleton.moons);
                                    setStatus(rainbow, cypressSingleton.rainbow);
                                    setStatus(slash, cypressSingleton.slash);
                                    setStatus(upperBowen, cypressSingleton.upperBowen);
                                    setStatus(upperRainbow, cypressSingleton.upperRainbow);
                                    setStatus(bowenWestGlades, cypressSingleton.bowenWestGlades);
                                    setStatus(cratorGlades, cypressSingleton.cratorGlades);
                                    setStatus(darksideGlades, cypressSingleton.darksideGlades);
                                    setStatus(elevatorGlades, cypressSingleton.elevatorGlades);
                                    setStatus(gibsonGlades, cypressSingleton.gibsonGlades);
                                    setStatus(LTDGlades, cypressSingleton.LTDGlades);
                                    setStatus(underTheVolcanoGlades, cypressSingleton.underTheVolcanoGlades);

                                    ImageView sunrisePark = findViewById(R.id.imageView243);
                                    setStatus(sunrisePark, cypressSingleton.sunrisePark);

                                    TextView runsOpen = findViewById(R.id.textView154);
                                    runsOpen.setText("Runs Open: " + cypressSingleton.runsOpenLionsExpress + "/24");
                                    TextView terrainParksOpen = findViewById(R.id.textView418);
                                    terrainParksOpen.setText("Terrain Parks Open: " + cypressSingleton.terrainParksOpenLionsExpress + "/1");
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
