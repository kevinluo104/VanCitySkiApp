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

                                    ImageView imageView180 = findViewById(R.id.imageView180);
                                    switch(cypressSingleton.lionsExpress) {
                                        case "open":
                                            imageView180.setImageResource(R.drawable.greencheck);
                                            break;
                                        case "closed":
                                            imageView180.setImageResource(R.drawable.redx);
                                            break;
                                        case "standby":
                                            imageView180.setImageResource(R.drawable.triangle);
                                            break;
                                    }
                                    ImageView imageView181 = findViewById(R.id.imageView181);
                                    ImageView imageView219 = findViewById(R.id.imageView219);
                                    ImageView imageView220 = findViewById(R.id.imageView220);
                                    ImageView imageView221 = findViewById(R.id.imageView221);
                                    ImageView imageView222 = findViewById(R.id.imageView222);
                                    ImageView imageView223 = findViewById(R.id.imageView223);
                                    ImageView imageView224 = findViewById(R.id.imageView224);
                                    ImageView imageView225 = findViewById(R.id.imageView225);
                                    ImageView imageView226 = findViewById(R.id.imageView226);
                                    ImageView imageView227 = findViewById(R.id.imageView227);
                                    ImageView imageView228 = findViewById(R.id.imageView228);
                                    ImageView imageView229 = findViewById(R.id.imageView229);
                                    ImageView imageView230 = findViewById(R.id.imageView230);
                                    ImageView imageView231 = findViewById(R.id.imageView231);
                                    ImageView imageView232 = findViewById(R.id.imageView232);
                                    ImageView imageView233 = findViewById(R.id.imageView233);
                                    ImageView imageView234 = findViewById(R.id.imageView234);
                                    ImageView imageView235 = findViewById(R.id.imageView235);
                                    ImageView imageView236 = findViewById(R.id.imageView236);
                                    ImageView imageView237 = findViewById(R.id.imageView237);
                                    ImageView imageView238 = findViewById(R.id.imageView238);
                                    ImageView imageView239 = findViewById(R.id.imageView239);
                                    ImageView imageView240 = findViewById(R.id.imageView240);
                                    ImageView imageView241 = findViewById(R.id.imageView241);

                                    setStatus(imageView181, cypressSingleton.collins);
                                    setStatus(imageView219, cypressSingleton.catTrackLower);
                                    setStatus(imageView220, cypressSingleton.catTrackUpper);
                                    setStatus(imageView221, cypressSingleton.horizon);
                                    setStatus(imageView222, cypressSingleton.horizonByPass);
                                    setStatus(imageView223, cypressSingleton.humptyDumpty);
                                    setStatus(imageView224, cypressSingleton.hutch);
                                    setStatus(imageView225, cypressSingleton.lowerBowen);
                                    setStatus(imageView226, cypressSingleton.primaryPower);
                                    setStatus(imageView227, cypressSingleton.bowenFace);
                                    setStatus(imageView228, cypressSingleton.bowenWest);
                                    setStatus(imageView229, cypressSingleton.gibsons);
                                    setStatus(imageView230, cypressSingleton.moons);
                                    setStatus(imageView231, cypressSingleton.rainbow);
                                    setStatus(imageView232, cypressSingleton.slash);
                                    setStatus(imageView233, cypressSingleton.upperBowen);
                                    setStatus(imageView234, cypressSingleton.upperRainbow);
                                    setStatus(imageView235, cypressSingleton.bowenWestGlades);
                                    setStatus(imageView236, cypressSingleton.cratorGlades);
                                    setStatus(imageView237, cypressSingleton.darksideGlades);
                                    setStatus(imageView238, cypressSingleton.elevatorGlades);
                                    setStatus(imageView239, cypressSingleton.gibsonGlades);
                                    setStatus(imageView240, cypressSingleton.LTDGlades);
                                    setStatus(imageView241, cypressSingleton.underTheVolcanoGlades);

                                    ImageView imageView243 = findViewById(R.id.imageView243);
                                    setStatus(imageView243, cypressSingleton.sunrisePark);

                                    TextView textView154 = findViewById(R.id.textView154);
                                    textView154.setText("Runs Open: " + cypressSingleton.runsOpenLionsExpress + "/24");
                                    TextView textView418 = findViewById(R.id.textView418);
                                    textView418.setText("Terrain Parks Open: " + cypressSingleton.terrainParksOpenLionsExpress + "/1");
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
