package com.skiapp.vancityskiapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class EagleExpress extends AppCompatActivity {

    private CypressSingleton cypressSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eagle_express);
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            cypressSingleton = CypressSingleton.getInstance(new ResultListener() {
                                @Override
                                public void onResultFetched() {
                                    ImageView imageView54 = findViewById(R.id.imageView54);
                                    switch(cypressSingleton.eagleExpress) {
                                        case "open":
                                            imageView54.setImageResource(R.drawable.greencheck);
                                            break;
                                        case "closed":
                                            imageView54.setImageResource(R.drawable.redx);
                                            break;
                                        case "standby":
                                            imageView54.setImageResource(R.drawable.triangle);
                                            break;
                                    }
                                    ImageView imageView135 = findViewById(R.id.imageView135);
                                    ImageView imageView161 = findViewById(R.id.imageView161);
                                    ImageView imageView162 = findViewById(R.id.imageView162);
                                    ImageView imageView163 = findViewById(R.id.imageView163);
                                    ImageView imageView164 = findViewById(R.id.imageView164);
                                    ImageView imageView165 = findViewById(R.id.imageView165);
                                    ImageView imageView166 = findViewById(R.id.imageView166);
                                    ImageView imageView167 = findViewById(R.id.imageView167);
                                    ImageView imageView168 = findViewById(R.id.imageView168);
                                    ImageView imageView169 = findViewById(R.id.imageView169);
                                    ImageView imageView170 = findViewById(R.id.imageView170);
                                    ImageView imageView171 = findViewById(R.id.imageView171);
                                    ImageView imageView172 = findViewById(R.id.imageView172);

                                    setStatus(imageView135, cypressSingleton.panorama);
                                    setStatus(imageView161, cypressSingleton.windjammer);
                                    setStatus(imageView162, cypressSingleton.jaseyJay);
                                    setStatus(imageView163, cypressSingleton.lowerFork);
                                    setStatus(imageView164, cypressSingleton.mcIvors);
                                    setStatus(imageView165, cypressSingleton.unrun);
                                    setStatus(imageView166, cypressSingleton.upperFork);
                                    setStatus(imageView167, cypressSingleton.bHip);
                                    setStatus(imageView168, cypressSingleton.blowBy);
                                    setStatus(imageView169, cypressSingleton.hoseSide);
                                    setStatus(imageView170, cypressSingleton.lowerTrumpeter);
                                    setStatus(imageView171, cypressSingleton.trumpeter);
                                    setStatus(imageView172, cypressSingleton.detentionGlades);


                                    ImageView imageView176 = findViewById(R.id.imageView176);
                                    ImageView imageView177 = findViewById(R.id.imageView177);
                                    ImageView imageView178 = findViewById(R.id.imageView178);
                                    setStatus(imageView176, cypressSingleton.skatePark);
                                    setStatus(imageView177, cypressSingleton.stompingGrounds);
                                    setStatus(imageView178, cypressSingleton.district);

                                    TextView textView134 = findViewById(R.id.textView134);
                                    textView134.setText("Runs Open: " + cypressSingleton.runsOpenEagleExpress + "/13");
                                    TextView textView417 = findViewById(R.id.textView417);
                                    textView417.setText("Terrain Parks Open: " + cypressSingleton.terrainParksOpenEagleExpress + "/3");
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
