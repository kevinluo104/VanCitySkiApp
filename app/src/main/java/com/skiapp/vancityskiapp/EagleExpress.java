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
                                    ImageView panorama = findViewById(R.id.imageView135);
                                    ImageView windjammer = findViewById(R.id.imageView161);
                                    ImageView jaseyJay = findViewById(R.id.imageView162);
                                    ImageView lowerFork = findViewById(R.id.imageView163);
                                    ImageView mcIvors = findViewById(R.id.imageView164);
                                    ImageView unrun = findViewById(R.id.imageView165);
                                    ImageView upperFork = findViewById(R.id.imageView166);
                                    ImageView bHip = findViewById(R.id.imageView167);
                                    ImageView blowBy = findViewById(R.id.imageView168);
                                    ImageView hoseSide = findViewById(R.id.imageView169);
                                    ImageView lowerTrumpeter = findViewById(R.id.imageView170);
                                    ImageView trumpeter = findViewById(R.id.imageView171);
                                    ImageView detentionGlades = findViewById(R.id.imageView172);

                                    setStatus(panorama, cypressSingleton.panorama);
                                    setStatus(windjammer, cypressSingleton.windjammer);
                                    setStatus(jaseyJay, cypressSingleton.jaseyJay);
                                    setStatus(lowerFork, cypressSingleton.lowerFork);
                                    setStatus(mcIvors, cypressSingleton.mcIvors);
                                    setStatus(unrun, cypressSingleton.unrun);
                                    setStatus(upperFork, cypressSingleton.upperFork);
                                    setStatus(bHip, cypressSingleton.bHip);
                                    setStatus(blowBy, cypressSingleton.blowBy);
                                    setStatus(hoseSide, cypressSingleton.hoseSide);
                                    setStatus(lowerTrumpeter, cypressSingleton.lowerTrumpeter);
                                    setStatus(trumpeter, cypressSingleton.trumpeter);
                                    setStatus(detentionGlades, cypressSingleton.detentionGlades);


                                    ImageView skatePark = findViewById(R.id.imageView176);
                                    ImageView stompingGrounds = findViewById(R.id.imageView177);
                                    ImageView district = findViewById(R.id.imageView178);
                                    setStatus(skatePark, cypressSingleton.skatePark);
                                    setStatus(stompingGrounds, cypressSingleton.stompingGrounds);
                                    setStatus(district, cypressSingleton.district);

                                    TextView runsOpen = findViewById(R.id.textView134);
                                    runsOpen.setText("Runs Open: " + cypressSingleton.runsOpenEagleExpress + "/13");
                                    TextView terrainParksOpen = findViewById(R.id.textView417);
                                    terrainParksOpen.setText("Terrain Parks Open: " + cypressSingleton.terrainParksOpenEagleExpress + "/3");
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
