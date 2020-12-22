package com.skiapp.vancityskiapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;



public class BrocktonChair extends AppCompatActivity {

    private SeymourSingleton seymourSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brockton_chair);

        new Thread() {
            public void run() {
                runOnUiThread( new Runnable()
                {
                    public void run() {
                        try {
                            seymourSingleton = SeymourSingleton.getInstance(new ResultListener() {
                                @Override
                                public void onResultFetched() {
                                    ImageView imageView328 = findViewById(R.id.imageView328);
                                    switch(seymourSingleton.brocktonChair) {
                                        case "open":
                                            imageView328.setImageResource(R.drawable.greencheck);
                                            break;
                                        case "closed":
                                            imageView328.setImageResource(R.drawable.redx);
                                            break;
                                        case "standby":
                                            imageView328.setImageResource(R.drawable.triangle);
                                            break;
                                    }
                                    TextView textView352 = findViewById(R.id.textView352);
                                    textView352.setText("Runs Open: " + seymourSingleton.brocktonChairRunsOpen + "/11");
                                    ImageView imageView329 = findViewById(R.id.imageView329);
                                    setStatus(imageView329, seymourSingleton.brocktonGully);
                                    ImageView imageView341 = findViewById(R.id.imageView341);
                                    setStatus(imageView341, seymourSingleton.backdoor);
                                    ImageView imageView342 =  findViewById(R.id.imageView342);
                                    setStatus(imageView342, seymourSingleton.exit22);
                                    ImageView imageView343 = findViewById(R.id.imageView343);
                                    setStatus(imageView343, seymourSingleton.hangTen);
                                    ImageView imageView344 = findViewById(R.id.imageView344);
                                    setStatus(imageView344, seymourSingleton.maverick);
                                    ImageView imageView345 = findViewById(R.id.imageView345);
                                    setStatus(imageView345, seymourSingleton.sammyJ);
                                    ImageView imageView346 = findViewById(R.id.imageView346);
                                    setStatus(imageView346, seymourSingleton.sammysExpress);
                                    ImageView imageView347 = findViewById(R.id.imageView347);
                                    setStatus(imageView347, seymourSingleton.cliffHouse);
                                    ImageView imageView348 = findViewById(R.id.imageView348);
                                    setStatus(imageView348, seymourSingleton.scooter);
                                    ImageView imageView349 = findViewById(R.id.imageView349);
                                    setStatus(imageView349, seymourSingleton.sternsStairway);
                                    ImageView imageView350 = findViewById(R.id.imageView350);
                                    setStatus(imageView350, seymourSingleton.sunshineRidge);
                                }
                            }, getApplicationContext());
                            seymourSingleton.startThread();
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
            case "Open":
                image.setImageResource(R.drawable.greencheck);
                return;
            case "Closed":
                image.setImageResource(R.drawable.redx);
                return;
            case "Standby":
                image.setImageResource(R.drawable.triangle);
                return;
            case "?":
                image.setImageResource(R.drawable.question_mark);
                return;
        }
    }
}
