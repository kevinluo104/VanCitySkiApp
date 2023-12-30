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
                                    ImageView brocktonChair = findViewById(R.id.imageView328);
                                    switch(seymourSingleton.brocktonChair) {
                                        case "open":
                                            brocktonChair.setImageResource(R.drawable.greencheck);
                                            break;
                                        case "closed":
                                            brocktonChair.setImageResource(R.drawable.redx);
                                            break;
                                        case "standby":
                                            brocktonChair.setImageResource(R.drawable.triangle);
                                            break;
                                    }
                                    TextView runsOpen = findViewById(R.id.textView352);
                                    runsOpen.setText("Runs Open: " + seymourSingleton.brocktonChairRunsOpen + "/11");
                                    ImageView brocktonGully = findViewById(R.id.imageView329);
                                    setStatus(brocktonGully, seymourSingleton.brocktonGully);
                                    ImageView backdoor = findViewById(R.id.imageView341);
                                    setStatus(backdoor, seymourSingleton.backdoor);
                                    ImageView exit22 =  findViewById(R.id.imageView342);
                                    setStatus(exit22, seymourSingleton.exit22);
                                    ImageView hangTen = findViewById(R.id.imageView343);
                                    setStatus(hangTen, seymourSingleton.hangTen);
                                    ImageView maverick = findViewById(R.id.imageView344);
                                    setStatus(maverick, seymourSingleton.maverick);
                                    ImageView sammyJ = findViewById(R.id.imageView345);
                                    setStatus(sammyJ, seymourSingleton.sammyJ);
                                    ImageView sammysExpress = findViewById(R.id.imageView346);
                                    setStatus(sammysExpress, seymourSingleton.sammysExpress);
                                    ImageView cliffHouse = findViewById(R.id.imageView347);
                                    setStatus(cliffHouse, seymourSingleton.cliffHouse);
                                    ImageView scooter = findViewById(R.id.imageView348);
                                    setStatus(scooter, seymourSingleton.scooter);
                                    ImageView sternsStairway = findViewById(R.id.imageView349);
                                    setStatus(sternsStairway, seymourSingleton.sternsStairway);
                                    ImageView sunshineRidge = findViewById(R.id.imageView350);
                                    setStatus(sunshineRidge, seymourSingleton.sunshineRidge);
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
