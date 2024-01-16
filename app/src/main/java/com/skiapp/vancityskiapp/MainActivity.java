package com.skiapp.vancityskiapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.appupdate.AppUpdateOptions;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //private InAppUpdateManager inAppUpdateManager;
    private Document vanWeather;
    private Calendar cal = Calendar.getInstance();
    private int hour = cal.get(Calendar.HOUR_OF_DAY);
    private String degreeSymbol = "°C";
    private Document hourlyVan;
    private Button cypressReportButton;
    private Button grouseReportButton;
    private Button seymourReportButton;
    private Button refreshButton;
    private Button FAQButton;
    private GrouseSingleton grouseSingleton;
    private CypressSingleton cypressSingleton;
    private SeymourSingleton seymourSingleton;
    private int timeInMillis = 6000;
    private int REQUEST_CODE = 11;
    private String TAG = "unsuccessful";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(MainActivity.this);
//        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
//        appUpdateInfoTask.addOnSuccessListener(result -> {
//            if (result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && result.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
//                try {
//                    appUpdateManager.startUpdateFlowForResult(result, AppUpdateType.IMMEDIATE, MainActivity.this, REQUEST_CODE);
//                } catch (IntentSender.SendIntentException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        final long startTime = System.currentTimeMillis();
        final TextView loadingDataText = findViewById(R.id.textView233);
        loadingDataText.setText("Loading data...");
        loadingDataText.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDataText.setVisibility(View.INVISIBLE);
            }
        }, timeInMillis);

        final TextView welcomeText = findViewById(R.id.textView234);
        welcomeText.setText("Welcome! Please check for updates in the app store when available!");
        welcomeText.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                welcomeText.setVisibility(View.INVISIBLE);
            }
        }, timeInMillis);

        final ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                constraintLayout.setVisibility(View.VISIBLE);
            }
        }, timeInMillis);

        final ConstraintLayout constraintLayout2 = findViewById(R.id.constraintLayout2);
        constraintLayout2.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                constraintLayout2.setVisibility(View.VISIBLE);
            }
        }, timeInMillis);

        final ConstraintLayout constraintLayout3 = findViewById(R.id.constraintLayout3);
        constraintLayout3.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                constraintLayout3.setVisibility(View.VISIBLE);
            }
        }, timeInMillis);

        final ConstraintLayout mainConstraintLayout = findViewById(R.id.mainConstraintLayout);
        mainConstraintLayout.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mainConstraintLayout.setVisibility(View.VISIBLE);
            }
        }, timeInMillis);

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Time:");
        System.out.println(totalTime);

        new Thread() {
            public void run() {
                try {
                    TextView errorText = findViewById(R.id.textView257);
                    errorText.setText("");
                    cypressReportButton = findViewById(R.id.button);
                    grouseReportButton = findViewById(R.id.button2);
                    seymourReportButton = findViewById(R.id.button3);

                    cypressReportButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            openCypress();
                        }
                    });
                    grouseReportButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            openGrouse();
                        }
                    });
                    seymourReportButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            openSeymour();
                        }
                    });
                    refreshButton = findViewById(R.id.button18);
                    refreshButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            refresh();
                        }
                    });
                    FAQButton = findViewById(R.id.button19);
                    FAQButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            openFAQ();
                        }
                    });
                    vanWeather = Jsoup.connect("https://weather.gc.ca/city/pages/bc-74_metric_e.html").userAgent("Mozilla").get();  // VANCOUVER WEATHER
                    hourlyVan = Jsoup.connect("https://weather.gc.ca/forecast/hourly/bc-74_metric_e.html").get(); // VANCOUVER HOURLY WEATHER
                    System.out.println("HIOII22III");

                    runOnUiThread(new Runnable() {
                        public void run() {
                            ImageView image = findViewById(R.id.imageView);
                            vancouverWeather(vanWeather.select("img.center-block.mrgn-tp-md").first().absUrl("src"), image);
                            TextView vanTemp = findViewById(R.id.textView);
                            vanTemp.setText(vanWeather.select("#mainContent > details.panel.panel-default.wxo-obs.hidden-details-print-close > div.hidden-xs.row.no-gutters > div.col-sm-2.brdr-rght.text-center.currcond-height.hidden-print > p > span").first().text());
                            TextView vanConditions = findViewById(R.id.textView10);
                            vanConditions.setText(vanWeather.select("#mainContent > details.panel.panel-default.wxo-obs.hidden-details-print-close > div.hidden-xs.row.no-gutters > div.col-sm-10.text-center.currcond-height.hidden-print > div:nth-child(2) > dl > dd:nth-child(2) > span").first().text());
                            ArrayList<String> tempList = setHourlyTemp(hourlyVan, hour);
                            TextView van6amTemp = findViewById(R.id.textView23);
                            TextView van9amTemp = findViewById(R.id.textView24);
                            TextView van12pmTemp = findViewById(R.id.textView25);
                            TextView van3pmTemp = findViewById(R.id.textView26);
                            TextView van6pmTemp = findViewById(R.id.textView19);
                            TextView van9pmTemp = findViewById(R.id.textView27);
                            TextView van12amTemp = findViewById(R.id.textView28);
                            TextView van3amTemp = findViewById(R.id.textView30);
                            ImageView sixAmPic = findViewById(R.id.imageView6);
                            ImageView nineAmPic = findViewById(R.id.imageView7);
                            ImageView twelvePmPic = findViewById(R.id.imageView8);
                            ImageView threePmPic = findViewById(R.id.imageView9);
                            ImageView sixPmPic = findViewById(R.id.imageView10);
                            ImageView ninePmPic = findViewById(R.id.imageView11);
                            ImageView twelveAmPic = findViewById(R.id.imageView12);
                            ImageView threeAmPic = findViewById(R.id.imageView13);

                            TextView firstHourSlot = findViewById(R.id.textView11);
                            TextView secondHourSlot = findViewById(R.id.textView12);
                            TextView thirdHourSlot = findViewById(R.id.textView13);
                            TextView fourthHourSlot = findViewById(R.id.textView14);
                            TextView fifthHourSlot = findViewById(R.id.textView15);
                            TextView sixthHourSlot = findViewById(R.id.textView16);
                            TextView seventhHourSlot = findViewById(R.id.textView17);
                            TextView eighthHourSlot = findViewById(R.id.textView18);
                            setHourlyVanTimeSlot(firstHourSlot, secondHourSlot, thirdHourSlot, fourthHourSlot, fifthHourSlot, sixthHourSlot, seventhHourSlot, eighthHourSlot);
                            for (String s: tempList) {
                                System.out.println("HEREEEEE: " + s);
                            }
                            setVanTempHourly(tempList, van6amTemp, van9amTemp, van12pmTemp, van3pmTemp, van6pmTemp, van9pmTemp, van12amTemp, van3amTemp);
                            setVanIcon(tempList, sixAmPic, nineAmPic, twelvePmPic, threePmPic, sixPmPic, ninePmPic, twelveAmPic, threeAmPic);


                            try {
                                grouseSingleton = GrouseSingleton.getInstance(new ResultListener() {
                                    @Override
                                    public void onResultFetched() {
                                        System.out.println("GROUSE");
                                        TextView grouseTemp = findViewById(R.id.textView5);
                                        grouseTemp.setText(grouseSingleton.temperature);
                                        TextView grouseVisibility = findViewById(R.id.textView22);

                                        switch (grouseSingleton.visibility) {
                                            case "Limited Visibility":
                                                grouseVisibility.setText("Visibility: Limited");
                                                break;
                                            case "Variable Visibility":
                                                grouseVisibility.setText("Visibility: Variable");
                                                break;
                                            case "Unlimited Visibility":
                                                grouseVisibility.setText("Visibility: Unlimited");
                                                break;
                                        }
                                        TextView grouseOvernightSnow = findViewById(R.id.textView36);
                                        grouseOvernightSnow.setText("New Snow: " + grouseSingleton.overnightSnow);
                                        TextView grouseWeather = findViewById(R.id.textView32);
                                        grouseWeather.setText(grouseSingleton.weather);
                                        TextView grouseRunsOpen = findViewById(R.id.textView313);
                                        grouseRunsOpen.setText("Runs Open: " + grouseSingleton.runsOpen + "/34");
                                        TextView grouseLiftsOpen = findViewById(R.id.textView34);
                                        grouseLiftsOpen.setText("Lifts Open: " + grouseSingleton.liftsOpen + "/6");
                                        ImageView grouseCondition = findViewById(R.id.imageView4);
                                        setGrousePic(grouseSingleton.picture, grouseCondition);
                                    }
                                }, getApplicationContext());
                                grouseSingleton.startThread();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                final TextView mainErrorText = findViewById(R.id.textView255);
                                mainErrorText.setText("Refresh at bottom of screen if conditions don't show up!");
                                cypressSingleton = CypressSingleton.getInstance(new ResultListener() {
                                    @Override
                                    public void onResultFetched() {

                                        TextView cypressConditions = findViewById(R.id.textView31);
                                        String upperS = cypressSingleton.conditions.substring(0, 1).toUpperCase() + cypressSingleton.conditions.substring(1);
                                        cypressConditions.setText(upperS);
                                        TextView cypressTemp = findViewById(R.id.textView6);
                                        cypressTemp.setText(cypressSingleton.temperature + "°C");
                                        TextView cypressRunsOpen = findViewById(R.id.textView21);
                                        cypressRunsOpen.setText("Runs Open: " + cypressSingleton.runsOpen + "/61");
                                        TextView cypressLiftsOpen = findViewById(R.id.textView29);
                                        cypressLiftsOpen.setText("Lifts Open: " + cypressSingleton.liftsOpen + "/6");
                                        TextView cypressNewSnow = findViewById(R.id.textView35);
                                        cypressNewSnow.setText("New Snow: " + cypressSingleton.twentyFourHrSnow);
                                        ImageView cypressWeather = findViewById(R.id.imageView2);
                                        setCypressPic(cypressSingleton.conditions, cypressWeather);
                                      /*  if (cypressSingleton.conditions.equals("Rain/snow"))
                                            cypressSingleton.conditions = "Rain/Snow";*/

                                        //    TextView tv7 = findViewById(R.id.cypressVisibility);
                                        //  tv7.setText("Refresh at bottom of screen if nothing shows up!");
                                        mainErrorText.setText("");
                                    }
                                }, getApplicationContext());
                                cypressSingleton.startThread();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            TextView cypressVisibility = findViewById(R.id.textView7);
                            cypressVisibility.setText("");
                            try {
                                seymourSingleton = SeymourSingleton.getInstance(new ResultListener() {
                                    @Override
                                    public void onResultFetched() {
                                        TextView seymourVisibility = findViewById(R.id.textView40);
                                        seymourVisibility.setText("Visibility: " + seymourSingleton.visibility);
                                        TextView seymourRunsOpen = findViewById(R.id.textView41);
                                        seymourRunsOpen.setText("Runs Open: " + seymourSingleton.runsOpen + "/42");
                                        TextView seymourLiftsOpen = findViewById(R.id.textView42);
                                        seymourLiftsOpen.setText("Lifts Open: " + seymourSingleton.liftsOpen + "/4");
                                        TextView seymour24HrSnow = findViewById(R.id.textView43);
                                        seymour24HrSnow.setText("New Snow: " + seymourSingleton.twentyFourHrSnow);

                                        ImageView seymourWeather = findViewById(R.id.imageView5);
                                        setSeymourPic(seymourSingleton.conditions, seymourWeather);
                                        TextView seymourTemp = findViewById(R.id.textView8);
                                        seymourTemp.setText(seymourSingleton.temperature);
                                        TextView seymourConditions = findViewById(R.id.textView33);
                                        seymourConditions.setText(seymourSingleton.conditions);
                                    }
                                }, getApplicationContext());
                                seymourSingleton.startThread();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    while (System.currentTimeMillis() - startTime <= 5000) {
                        run();
                    }
                    openError();
                }
            }
        }.start();
    }





    @Override
    protected void onStart() {
        super.onStart();
    }




    @Override
    protected void onStop() {
        super.onStop();
    }

    public void vancouverWeather(String condition, ImageView image) {
        if (condition.equals("https://weather.gc.ca/weathericons/12.gif")) {   // LIGHT RAIN
            image.setImageResource(R.drawable.chance_of_showers);
            return;
        }
        if (condition.equals("https://weather.gc.ca/weathericons/19.gif")) { // THUNDERSTORMS WITH LIGHT RAIN NIGHT    SHOWERS AT TIMES HEAVY. RISK OF THUNDERSTORMS.
            image.setImageResource(R.drawable.thunderstorm_w_light_rain);
            return;
        }
        if (condition.equals("https://weather.gc.ca/weathericons/23.gif")) { // HAZE
            image.setImageResource(R.drawable.haze);
            return;
        }
        if (condition.equals("https://weather.gc.ca/weathericons/44.gif")) { // SMOKE
            image.setImageResource(R.drawable.smoke);
            return;
        }
        if (condition.equals("https://weather.gc.ca/weathericons/13.gif")) {  // RAIN
            image.setImageResource(R.drawable.rain);
            return;
        }
        if (condition.equals("https://weather.gc.ca/weathericons/24.gif") || condition.equals("https://weather.gc.ca/weathericons/small/24.png")) {  // MIST OR FOG
            image.setImageResource(R.drawable.mist);
            return;
        }
        if (condition.equals("https://weather.gc.ca/weathericons/10.gif")) {  // OVERCAST
            image.setImageResource(R.drawable.overcast);
            return;
        }
        if (condition.equals("https://weather.gc.ca/weathericons/16.gif")) {  // LIGHT SNOW
            image.setImageResource(R.drawable.light_snow);
            return;
        }
        if (condition.equals("https://weather.gc.ca/weathericons/17.gif")) {  // SNOW
            image.setImageResource(R.drawable.snow);
            return;
        }
        if (condition.equals("https://weather.gc.ca/weathericons/15.gif")) {  // WET SNOW MIXED WITH RAIN
            image.setImageResource(R.drawable.wet_snow_mixed_with_rain);
            return;
        }
        if (condition.equals("https://weather.gc.ca/weathericons/28.gif")) {  // LIGHT DRIZZLE
            image.setImageResource(R.drawable.light_drizzle);
            return;
        }
        if (condition.equals("https://weather.gc.ca/weathericons/06.gif")) {  // LIGHT RAINSHOWER
            image.setImageResource(R.drawable.chance_of_showers_day);
            return;
        }
        if (condition.equals("https://weather.gc.ca/weathericons/18.gif")) { // SNOW AT TIMES HEAVY / SNOW AT TIMES HEAVY. RISK OF FREEZING RAIN
            image.setImageResource(R.drawable.snow_at_times_heavy);
            return;
        }

        if (hour >= 8 && hour < 16) {
            switch (condition) {
                case "https://weather.gc.ca/weathericons/03.gif":  // MOSTLY CLOUDY
                    image.setImageResource(R.drawable.mainly_cloudy_day);
                    return;
                case "https://weather.gc.ca/weathericons/02.gif": // PARTLY CLOUDY
                    image.setImageResource(R.drawable.partly_cloudy);
                    return;
                case "https://weather.gc.ca/weathericons/01.gif": // MAINLY CLOUDY
                    image.setImageResource(R.drawable.mainly_sunny);
                    return;
                case "https://weather.gc.ca/weathericons/00.gif": // SUNNY
                    image.setImageResource(R.drawable.sunny);
                    return;
            }
        } else {
            switch (condition) {
                case "https://weather.gc.ca/weathericons/33.gif":   //MOSTLY CLOUDY AFTER >= 10PM
                case "https://weather.gc.ca/weathericons/03.gif":  // MOSTLY CLOUDY
                    image.setImageResource(R.drawable.mainly_cloudy_night);
                    return;
                case "https://weather.gc.ca/weathericons/31.gif": // MAINLY CLEAR NIGHT
                    image.setImageResource(R.drawable.mainly_clear_night);
                    return;
                case "https://weather.gc.ca/weathericons/30.gif": // CLEAR NIGHT
                    image.setImageResource(R.drawable.clear);
                    return;
                case "https://weather.gc.ca/weathericons/32.gif": // PARTLY CLOUDY
                    image.setImageResource(R.drawable.cloudy_periods);
                    return;
                case "https://weather.gc.ca/weathericons/39.gif": // THUNDERSTORMS NIGHT
                    image.setImageResource(R.drawable.thunderstorm_night);
                    return;
                case "https://weather.gc.ca/weathericons/38.gif": // NO NAME YET (CHANCE OF FLURRIES NIGHT)
                    image.setImageResource(R.drawable.chance_of_flurries_night);
                    return;
                case "https://weather.gc.ca/weathericons/37.gif": // NO NAME YET (CHANCE OF MIX OF SNOW AND RAIN NIGHT)
                    image.setImageResource(R.drawable.chance_of_mix_of_snow_and_rain_night);
                    return;
            }
        }
    }

    public void setHourlyVanTimeSlot(TextView first, TextView second, TextView third, TextView fourth, TextView fifth, TextView sixth, TextView seventh, TextView eighth) {
        switch (hour) {
            case 0:
            case 22:
            case 23:
                first.setText("12am");
                second.setText("3am");
                third.setText("6am");
                fourth.setText("9am");
                fifth.setText("12pm");
                sixth.setText("3pm");
                seventh.setText("6pm");
                eighth.setText("9pm");
                break;
            case 1:
            case 2:
            case 3:
                first.setText("3am");
                second.setText("6am");
                third.setText("9am");
                fourth.setText("12pm");
                fifth.setText("3pm");
                sixth.setText("6pm");
                seventh.setText("9pm");
                eighth.setText("12am");
                break;
            case 4:
            case 5:
            case 6:
                first.setText("6am");
                second.setText("9am");
                third.setText("12pm");
                fourth.setText("3pm");
                fifth.setText("6pm");
                sixth.setText("9pm");
                seventh.setText("12am");
                eighth.setText("3am");
                break;
            case 7:
            case 8:
            case 9:
                first.setText("9am");
                second.setText("12pm");
                third.setText("3pm");
                fourth.setText("6pm");
                fifth.setText("9pm");
                sixth.setText("12am");
                seventh.setText("3am");
                eighth.setText("6am");
                break;
            case 10:
            case 11:
            case 12:
                first.setText("12pm");
                second.setText("3pm");
                third.setText("6pm");
                fourth.setText("9pm");
                fifth.setText("12am");
                sixth.setText("3am");
                seventh.setText("6am");
                eighth.setText("9am");
                break;
            case 13:
            case 14:
            case 15:
                first.setText("3pm");
                second.setText("6pm");
                third.setText("9pm");
                fourth.setText("12am");
                fifth.setText("3am");
                sixth.setText("6am");
                seventh.setText("9am");
                eighth.setText("12pm");
                break;
            case 16:
            case 17:
            case 18:
                first.setText("6pm");
                second.setText("9pm");
                third.setText("12am");
                fourth.setText("3am");
                fifth.setText("6am");
                sixth.setText("9am");
                seventh.setText("12pm");
                eighth.setText("3pm");
                break;
            case 19:
            case 20:
            case 21:
                first.setText("9pm");
                second.setText("12am");
                third.setText("3am");
                fourth.setText("6am");
                fifth.setText("9am");
                sixth.setText("12pm");
                seventh.setText("3pm");
                eighth.setText("6pm");
                break;
        }
    }

    public void setVanTempHourly(ArrayList<String> listOfTemp, TextView first, TextView second, TextView third, TextView fourth, TextView fifth, TextView sixth, TextView seventh, TextView eighth) {
        switch (hour) {
            case 0:
                first.setText(listOfTemp.get(0) + "°C");
                second.setText(listOfTemp.get(1) + degreeSymbol);
                third.setText(listOfTemp.get(2) + degreeSymbol);
                fourth.setText(listOfTemp.get(3) + degreeSymbol);
                fifth.setText(listOfTemp.get(4) + degreeSymbol);
                sixth.setText(listOfTemp.get(5) + degreeSymbol);
                seventh.setText(listOfTemp.get(6) + degreeSymbol);
                eighth.setText(listOfTemp.get(7) + degreeSymbol);
            case 23:
            case 22:
                first.setText(listOfTemp.get(0) + "°C");
                second.setText(listOfTemp.get(1) + degreeSymbol);
                third.setText(listOfTemp.get(2) + degreeSymbol);
                fourth.setText(listOfTemp.get(3) + degreeSymbol);
                fifth.setText(listOfTemp.get(4) + degreeSymbol);
                sixth.setText(listOfTemp.get(5) + degreeSymbol);
                seventh.setText(listOfTemp.get(6) + degreeSymbol);
                eighth.setText(listOfTemp.get(7) + degreeSymbol);
                break;
            case 1:
            case 2:
                first.setText(listOfTemp.get(1) + "°C");
                second.setText(listOfTemp.get(2) + degreeSymbol);
                third.setText(listOfTemp.get(3) + degreeSymbol);
                fourth.setText(listOfTemp.get(4) + degreeSymbol);
                fifth.setText(listOfTemp.get(5) + degreeSymbol);
                sixth.setText(listOfTemp.get(6) + degreeSymbol);
                seventh.setText(listOfTemp.get(7) + degreeSymbol);
                eighth.setText(listOfTemp.get(0) + degreeSymbol);
                break;
            case 3:
                first.setText(listOfTemp.get(1) + "°C");
                second.setText(listOfTemp.get(2) + degreeSymbol);
                third.setText(listOfTemp.get(3) + degreeSymbol);
                fourth.setText(listOfTemp.get(4) + degreeSymbol);
                fifth.setText(listOfTemp.get(5) + degreeSymbol);
                sixth.setText(listOfTemp.get(6) + degreeSymbol);
                seventh.setText(listOfTemp.get(7) + degreeSymbol);
                eighth.setText(listOfTemp.get(0) + degreeSymbol);
                break;
            case 4:
            case 5:
                first.setText(listOfTemp.get(2) + "°C");
                second.setText(listOfTemp.get(3) + degreeSymbol);
                third.setText(listOfTemp.get(4) + degreeSymbol);
                fourth.setText(listOfTemp.get(5) + degreeSymbol);
                fifth.setText(listOfTemp.get(6) + degreeSymbol);
                sixth.setText(listOfTemp.get(7) + degreeSymbol);
                seventh.setText(listOfTemp.get(0) + degreeSymbol);
                eighth.setText(listOfTemp.get(1) + degreeSymbol);
                break;
            case 6:
                first.setText(listOfTemp.get(2) + "°C");
                second.setText(listOfTemp.get(3) + degreeSymbol);
                third.setText(listOfTemp.get(4) + degreeSymbol);
                fourth.setText(listOfTemp.get(5) + degreeSymbol);
                fifth.setText(listOfTemp.get(6) + degreeSymbol);
                sixth.setText(listOfTemp.get(7) + degreeSymbol);
                seventh.setText(listOfTemp.get(0) + degreeSymbol);
                eighth.setText(listOfTemp.get(1) + degreeSymbol);
                break;
            case 7:
            case 8:
                first.setText(listOfTemp.get(3) + "°C");
                second.setText(listOfTemp.get(4) + degreeSymbol);
                third.setText(listOfTemp.get(5) + degreeSymbol);
                fourth.setText(listOfTemp.get(6) + degreeSymbol);
                fifth.setText(listOfTemp.get(7) + degreeSymbol);
                sixth.setText(listOfTemp.get(0) + degreeSymbol);
                seventh.setText(listOfTemp.get(1) + degreeSymbol);
                eighth.setText(listOfTemp.get(2) + degreeSymbol);
                break;
            case 9:
                first.setText(listOfTemp.get(3) + "°C");
                second.setText(listOfTemp.get(4) + degreeSymbol);
                third.setText(listOfTemp.get(5) + degreeSymbol);
                fourth.setText(listOfTemp.get(6) + degreeSymbol);
                fifth.setText(listOfTemp.get(7) + degreeSymbol);
                sixth.setText(listOfTemp.get(0) + degreeSymbol);
                seventh.setText(listOfTemp.get(1) + degreeSymbol);
                eighth.setText(listOfTemp.get(2) + degreeSymbol);
                break;
            case 10:
            case 11:
                first.setText(listOfTemp.get(4) + "°C");
                second.setText(listOfTemp.get(5) + degreeSymbol);
                third.setText(listOfTemp.get(6) + degreeSymbol);
                fourth.setText(listOfTemp.get(7) + degreeSymbol);
                fifth.setText(listOfTemp.get(0) + degreeSymbol);
                sixth.setText(listOfTemp.get(1) + degreeSymbol);
                seventh.setText(listOfTemp.get(2) + degreeSymbol);
                eighth.setText(listOfTemp.get(3) + degreeSymbol);
                break;
            case 12:
                first.setText(listOfTemp.get(4) + "°C");
                second.setText(listOfTemp.get(5) + degreeSymbol);
                third.setText(listOfTemp.get(6) + degreeSymbol);
                fourth.setText(listOfTemp.get(7) + degreeSymbol);
                fifth.setText(listOfTemp.get(0) + degreeSymbol);
                sixth.setText(listOfTemp.get(1) + degreeSymbol);
                seventh.setText(listOfTemp.get(2) + degreeSymbol);
                eighth.setText(listOfTemp.get(3) + degreeSymbol);
                break;
            case 13:
            case 14:
                first.setText(listOfTemp.get(5) + "°C");
                second.setText(listOfTemp.get(6) + degreeSymbol);
                third.setText(listOfTemp.get(7) + degreeSymbol);
                fourth.setText(listOfTemp.get(0) + degreeSymbol);
                fifth.setText(listOfTemp.get(1) + degreeSymbol);
                sixth.setText(listOfTemp.get(2) + degreeSymbol);
                seventh.setText(listOfTemp.get(3) + degreeSymbol);
                eighth.setText(listOfTemp.get(4) + degreeSymbol);
                break;
            case 15:
//                for (String s: listOfTemp) {
//                    System.out.println("HEREEEEE: " + s);
//                }
                first.setText(listOfTemp.get(5) + "°C");
                second.setText(listOfTemp.get(6) + degreeSymbol);
                third.setText(listOfTemp.get(7) + degreeSymbol);
                fourth.setText(listOfTemp.get(0) + degreeSymbol);
                fifth.setText(listOfTemp.get(1) + degreeSymbol);
                sixth.setText(listOfTemp.get(2) + degreeSymbol);
                seventh.setText(listOfTemp.get(3) + degreeSymbol);
                eighth.setText(listOfTemp.get(4) + degreeSymbol);
                break;
            case 16:
            case 17:
                first.setText(listOfTemp.get(6) + "°C");
                second.setText(listOfTemp.get(7) + degreeSymbol);
                third.setText(listOfTemp.get(0) + degreeSymbol);
                fourth.setText(listOfTemp.get(1) + degreeSymbol);
                fifth.setText(listOfTemp.get(2) + degreeSymbol);
                sixth.setText(listOfTemp.get(3) + degreeSymbol);
                seventh.setText(listOfTemp.get(4) + degreeSymbol);
                eighth.setText(listOfTemp.get(5) + degreeSymbol);
                break;
            case 18:
                first.setText(listOfTemp.get(6) + "°C");
                second.setText(listOfTemp.get(7) + degreeSymbol);
                third.setText(listOfTemp.get(0) + degreeSymbol);
                fourth.setText(listOfTemp.get(1) + degreeSymbol);
                fifth.setText(listOfTemp.get(2) + degreeSymbol);
                sixth.setText(listOfTemp.get(3) + degreeSymbol);
                seventh.setText(listOfTemp.get(4) + degreeSymbol);
                eighth.setText(listOfTemp.get(5) + degreeSymbol);
                break;
            case 19:
            case 20:
                first.setText(listOfTemp.get(7) + "°C");
                second.setText(listOfTemp.get(0) + degreeSymbol);
                third.setText(listOfTemp.get(1) + degreeSymbol);
                fourth.setText(listOfTemp.get(2) + degreeSymbol);
                fifth.setText(listOfTemp.get(3) + degreeSymbol);
                sixth.setText(listOfTemp.get(4) + degreeSymbol);
                seventh.setText(listOfTemp.get(5) + degreeSymbol);
                eighth.setText(listOfTemp.get(6) + degreeSymbol);
                break;
            case 21:
                first.setText(listOfTemp.get(7) + "°C");
                second.setText(listOfTemp.get(0) + degreeSymbol);
                third.setText(listOfTemp.get(1) + degreeSymbol);
                fourth.setText(listOfTemp.get(2) + degreeSymbol);
                fifth.setText(listOfTemp.get(3) + degreeSymbol);
                sixth.setText(listOfTemp.get(4) + degreeSymbol);
                seventh.setText(listOfTemp.get(5) + degreeSymbol);
                eighth.setText(listOfTemp.get(6) + degreeSymbol);

        }
    }

    public void setVanIcon(ArrayList<String> condList, ImageView first, ImageView second, ImageView third, ImageView fourth, ImageView fifth, ImageView sixth, ImageView seventh, ImageView eighth) {
        switch (hour) {
            case 0:
            case 23:
            case 22:
                assignNightIcon(condList.get(8), first);
                assignNightIcon(condList.get(9), second);
                assignNightIcon(condList.get(10), third);
                assignDayIcon(condList.get(11), fourth);
                assignDayIcon(condList.get(12), fifth);
                assignDayIcon(condList.get(13), sixth);
                assignNightIcon(condList.get(14), seventh);
                assignNightIcon(condList.get(15), eighth);
                break;
            case 1:
            case 3:
            case 2:
                assignNightIcon(condList.get(9), first);
                assignNightIcon(condList.get(10), second);
                assignDayIcon(condList.get(11), third);
                assignDayIcon(condList.get(12), fourth);
                assignDayIcon(condList.get(13), fifth);
                assignNightIcon(condList.get(14), sixth);
                assignNightIcon(condList.get(15), seventh);
                assignNightIcon(condList.get(8), eighth);
                break;
            case 4:
            case 6:
            case 5:
                assignNightIcon(condList.get(10), first);
                assignDayIcon(condList.get(11), second);
                assignDayIcon(condList.get(12), third);
                assignDayIcon(condList.get(13), fourth);
                assignNightIcon(condList.get(14), fifth);
                assignNightIcon(condList.get(15), sixth);
                assignNightIcon(condList.get(8), seventh);
                assignNightIcon(condList.get(9), eighth);
                break;
            case 7:
            case 9:
            case 8:
                assignDayIcon(condList.get(11), first);
                assignDayIcon(condList.get(12), second);
                assignDayIcon(condList.get(13), third);
                assignNightIcon(condList.get(14), fourth);
                assignNightIcon(condList.get(15), fifth);
                assignNightIcon(condList.get(8), sixth);
                assignNightIcon(condList.get(9), seventh);
                assignNightIcon(condList.get(10), eighth);
                break;
            case 10:
            case 12:
            case 11:
                assignDayIcon(condList.get(12), first);
                assignDayIcon(condList.get(13), second);
                assignNightIcon(condList.get(14), third);
                assignNightIcon(condList.get(15), fourth);
                assignNightIcon(condList.get(8), fifth);
                assignNightIcon(condList.get(9), sixth);
                assignNightIcon(condList.get(10), seventh);
                assignDayIcon(condList.get(11), eighth);
                break;
            case 13:
            case 15:
            case 14:
                assignDayIcon(condList.get(13), first);
                assignNightIcon(condList.get(14), second);
                assignNightIcon(condList.get(15), third);
                assignNightIcon(condList.get(8), fourth);
                assignNightIcon(condList.get(9), fifth);
                assignNightIcon(condList.get(10), sixth);
                assignDayIcon(condList.get(11), seventh);
                assignDayIcon(condList.get(12), eighth);
                break;
            case 16:
            case 18:
            case 17:
                assignNightIcon(condList.get(14), first);
                assignNightIcon(condList.get(15), second);
                assignNightIcon(condList.get(8), third);
                assignNightIcon(condList.get(9), fourth);
                assignNightIcon(condList.get(10), fifth);
                assignDayIcon(condList.get(11), sixth);
                assignDayIcon(condList.get(12), seventh);
                assignDayIcon(condList.get(13), eighth);
                break;
            case 19:
            case 21:
            case 20:
                assignNightIcon(condList.get(15), first);
                assignNightIcon(condList.get(8), second);
                assignNightIcon(condList.get(9), third);
                assignNightIcon(condList.get(10), fourth);
                assignDayIcon(condList.get(11), fifth);
                assignDayIcon(condList.get(12), sixth);
                assignDayIcon(condList.get(13), seventh);
                assignNightIcon(condList.get(14), eighth);
                break;
        }
    }

    public void assignDayIcon(String imageLink, View image) {
        ImageView imageView = findViewById(image.getId());
        switch (imageLink) {
            case "https://weather.gc.ca/weathericons/24.gif": // MIST OR FOG
            case "https://weather.gc.ca/weathericons/small/24.png":
                imageView.setImageResource(R.drawable.mist);
                return;
            case "https://weather.gc.ca/weathericons/15.gif": // WET SNOW MIXED WITH RAIN
            case "https://weather.gc.ca/weathericons/small/15.png":
                imageView.setImageResource(R.drawable.wet_snow_mixed_with_rain);
                return;
            case "https://weather.gc.ca/weathericons/03.gif":  // MAIN PAGE MOSTLY CLOUDY
            case "https://weather.gc.ca/weathericons/small/03.png": // MAINLY CLOUDY
                imageView.setImageResource(R.drawable.mainly_cloudy_day);
                return;
            case "https://weather.gc.ca/weathericons/small/12.png":
            case "https://weather.gc.ca/weathericons/12.gif":
                imageView.setImageResource(R.drawable.chance_of_showers); // CHANCE OF SHOWERS // RAIN  // SHOWERS  // SHOWERS AT TIMES HEAVY
                return;
            case "https://weather.gc.ca/weathericons/small/10.png": // CLOUDY
                imageView.setImageResource(R.drawable.cloudy);
                return;
            case "https://weather.gc.ca/weathericons/small/00.png": //SUNNY
            case "https://weather.gc.ca/weathericons/00.gif":
                imageView.setImageResource(R.drawable.sunny);
                return;
            case "https://weather.gc.ca/weathericons/small/02.png": // MIX OF SUN AND CLOUD
                imageView.setImageResource(R.drawable.a_mix_of_sun_and_cloud);
                return;
            case "https://weather.gc.ca/weathericons/small/01.png": // MAINLY SUNNY
            case "https://weather.gc.ca/weathericons/01.gif":
                imageView.setImageResource(R.drawable.mainly_sunny);
                return;
            case "https://weather.gc.ca/weathericons/02.gif": // PARTLY CLOUDY
                imageView.setImageResource(R.drawable.partly_cloudy);
                return;
            case "https://weather.gc.ca/weathericons/small/06.png": // CHANCE OF SHOWERS DAY
                imageView.setImageResource(R.drawable.chance_of_showers_day);
                return;
            case "https://weather.gc.ca/weathericons/small/09.png": // CHANCE OF SHOWERS DAY RISK OF THUNDERSTORMS
                imageView.setImageResource(R.drawable.choshowers_riskothunderst);
                return;
            case "https://weather.gc.ca/weathericons/small/13.png": // RAIN  HEAVY RAIN
                imageView.setImageResource(R.drawable.heavy_rain);
                return;
            case "https://weather.gc.ca/weathericons/small/19.png":
            case "https://weather.gc.ca/weathericons/19.gif": // THUNDERSTORM WITH LIGHT RAIN NIGHT   SHOWERS AT TIMES HEAVY. RISK OF THUNDERSTORMS.     SHOWERS. RISK OF THUNDERSTORMS.
                imageView.setImageResource(R.drawable.thunderstorm_w_light_rain);
                return;
            case "https://weather.gc.ca/weathericons/small/44.png": // SMOKE
                imageView.setImageResource(R.drawable.smoke);
                return;
            case "https://weather.gc.ca/weathericons/small/23.png": // HAZE
                imageView.setImageResource(R.drawable.haze);
                return;
            case "https://weather.gc.ca/weathericons/10.gif": // CLOUDY
                imageView.setImageResource(R.drawable.overcast);
                return;
            case "https://weather.gc.ca/weathericons/small/08.png": // CHANCE OF FLURRIES DAY
                imageView.setImageResource(R.drawable.chance_of_flurries);
                return;
            case "https://weather.gc.ca/weathericons/small/16.png": // PERIODS OF LIGHT SNOW
                imageView.setImageResource(R.drawable.periods_of_light_snow);
                return;
            case "https://weather.gc.ca/weathericons/16.gif" :  // LIGHT SNOW
                imageView.setImageResource(R.drawable.light_snow);
                return;
            case "https://weather.gc.ca/weathericons/17.gif":
            case "https://weather.gc.ca/weathericons/small/17.png": // SNOW
                imageView.setImageResource(R.drawable.snow);
                return;
            case "https://weather.gc.ca/weathericons/28.gif":
            case "https://weather.gc.ca/weathericons/small/28.png":  // LIGHT DRIZZLE
                imageView.setImageResource(R.drawable.light_drizzle);
                return;
            case "https://weather.gc.ca/weathericons/small/18.png": // SNOW AT TIMES HEAVY / SNOW AT TIMES HEAVY. RISK OF FREEZING RAIN
            case "https://weather.gc.ca/weathericons/18.gif":
                imageView.setImageResource(R.drawable.snow_at_times_heavy);
            }
        }


    public void assignNightIcon(String imageLink, View image) {
        ImageView imageView = findViewById(image.getId());
        switch (imageLink) {
            case "https://weather.gc.ca/weathericons/24.gif": // MIST OR FOG
            case "https://weather.gc.ca/weathericons/small/24.png":
                imageView.setImageResource(R.drawable.mist);
                return;
            case "https://weather.gc.ca/weathericons/28.gif":
            case "https://weather.gc.ca/weathericons/small/28.png": // LIGHT DRIZZLE
                imageView.setImageResource(R.drawable.light_drizzle);
                return;
            case "https://weather.gc.ca/weathericons/15.gif": // WET SNOW MIXED WITH RAIN
            case "https://weather.gc.ca/weathericons/small/15.png":
                imageView.setImageResource(R.drawable.wet_snow_mixed_with_rain);
                return;
            case "https://weather.gc.ca/weathericons/small/32.png":   // PARTLY CLOUDY NIGHT
            case "https://weather.gc.ca/weathericons/32.gif":
                imageView.setImageResource(R.drawable.cloudy_periods);
                return;
            case "https://weather.gc.ca/weathericons/small/12.png":
            case "https://weather.gc.ca/weathericons/12.gif":
                imageView.setImageResource(R.drawable.chance_of_showers); // CHANCE OF SHOWERS  // RAIN  // SHOWERS  // SHOWERS AT TIMES HEAVY
                return;
            case "https://weather.gc.ca/weathericons/small/10.png": // CLOUDY
                imageView.setImageResource(R.drawable.cloudy);
                return;
            case "https://weather.gc.ca/weathericons/33.gif":
            case "https://weather.gc.ca/weathericons/03.gif":  // MOSTLY CLOUDY
            case "https://weather.gc.ca/weathericons/small/33.png":
                imageView.setImageResource(R.drawable.mainly_cloudy_night);
                return;
            case "https://weather.gc.ca/weathericons/small/30.png": //CLEAR
                imageView.setImageResource(R.drawable.clear);
                return;
            case "https://weather.gc.ca/weathericons/31.gif": // MAINLY CLEAR NIGHT
                imageView.setImageResource(R.drawable.mainly_clear_night);
                return;
            case "https://weather.gc.ca/weathericons/30.gif": // CLEAR
                imageView.setImageResource(R.drawable.clear);
                return;
            case "https://weather.gc.ca/weathericons/small/31.png": // A FEW CLOUDS
                imageView.setImageResource(R.drawable.a_few_clouds);
                return;
            case "https://weather.gc.ca/weathericons/small/36.png": // CHANCE OF SHOWERS NIGHT
                imageView.setImageResource(R.drawable.chance_of_showers_night);
                return;
            case "https://weather.gc.ca/weathericons/small/13.png": // RAIN  HEAVY RAIN
                imageView.setImageResource(R.drawable.heavy_rain);
                return;
            case "https://weather.gc.ca/weathericons/small/39.png": // CHANCE OF SHOWERS. RISK OF THUNDERSTORMS NIGHT
            case "https://weather.gc.ca/weathericons/39.gif":
                imageView.setImageResource(R.drawable.thunderstorm_night);
                return;
            case "https://weather.gc.ca/weathericons/small/19.png":
            case "https://weather.gc.ca/weathericons/19.gif": // THUNDERSTORM WITH LIGHT RAIN NIGHT  SHOWERS AT TIMES HEAVY. RISK OF THUNDERSTORMS.   SHOWERS. RISK OF THUNDERSTORMS.
                imageView.setImageResource(R.drawable.thunderstorm_w_light_rain);
                return;
            case "https://weather.gc.ca/weathericons/small/44.png": // SMOKE
                imageView.setImageResource(R.drawable.smoke);
                return;
            case "https://weather.gc.ca/weathericons/small/23.png": // HAZE
                imageView.setImageResource(R.drawable.haze);
                return;
            case "https://weather.gc.ca/weathericons/10.gif": // CLOUDY
                imageView.setImageResource(R.drawable.overcast);
                return;
            case "https://weather.gc.ca/weathericons/small/16.png": // PERIODS OF LIGHT SNOW
                imageView.setImageResource(R.drawable.periods_of_light_snow);
                return;
            case "https://weather.gc.ca/weathericons/38.gif":
            case "https://weather.gc.ca/weathericons/small/38.png": // CHANCE OF FLURRIES
                imageView.setImageResource(R.drawable.chance_of_flurries_night);
                return;
            case "https://weather.gc.ca/weathericons/16.gif" :  // LIGHT SNOW
                imageView.setImageResource(R.drawable.light_snow);
                return;
            case "https://weather.gc.ca/weathericons/small/37.png":
            case "https://weather.gc.ca/weathericons/37.gif": // NO NAME YET (CHANCE OF MIX OF SNOW AND RAIN NIGHT)
                imageView.setImageResource(R.drawable.chance_of_mix_of_snow_and_rain_night);
                return;
            case "https://weather.gc.ca/weathericons/17.gif":
            case "https://weather.gc.ca/weathericons/small/17.png": // SNOW
                imageView.setImageResource(R.drawable.snow);
                return;
            case "https://weather.gc.ca/weathericons/small/18.png": // SNOW AT TIMES HEAVY / SNOW AT TIMES HEAVY. RISK OF FREEZING RAIN
            case "https://weather.gc.ca/weathericons/18.gif":
                imageView.setImageResource(R.drawable.snow_at_times_heavy);
        }
    }

    public ArrayList<String> setHourlyTemp(Document d, int hour) {
        ArrayList<String> hourlyTemps = new ArrayList<>();
        String threeAm = null;
        String sixAm = null;
        String nineAm = null;
        String twelvePm = null;
        String threePm = null;
        String sixPm = null;
        String ninePm = null;
        String twelveAm = null;
        String threeAmCond = null;
        String sixAmCond = null;
        String nineAmCond = null;
        String twelvePmCond = null;
        String threePmCond = null;
        String sixPmCond = null;
        String ninePmCond = null;
        String twelveAmCond = null;
        Elements elements1 = d.select("tbody tr");

        try {
            switch (hour) {
                case 0:
                    twelveAm = vanWeather.select("span[data-v-0e2dfac5]").get(3).text();
                    int degreeSymbolIndex = twelveAm.indexOf("°");
                    twelveAm = twelveAm.substring(0, degreeSymbolIndex).trim();
                    threeAm = elements1.get(3).select("td.text-center").get(1).text();
                    sixAm = elements1.get(6).select("td.text-center").get(1).text();
                    nineAm = elements1.get(9).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(12).select("td.text-center").get(1).text();
                    threePm = elements1.get(15).select("td.text-center").get(1).text();
                    sixPm = elements1.get(18).select("td.text-center").get(1).text();
                    ninePm = elements1.get(21).select("td.text-center").get(1).text();
                    twelveAmCond = vanWeather.select("img.center-block.mrgn-tp-md").first().absUrl("src");
                    threeAmCond = elements1.get(3).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(6).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(9).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(12).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(15).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(18).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(21).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 1:
                    twelveAm = elements1.get(24).select("td.text-center").get(1).text();
                    threeAm = elements1.get(2).select("td.text-center").get(1).text();
                    sixAm = elements1.get(5).select("td.text-center").get(1).text();
                    nineAm = elements1.get(8).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(11).select("td.text-center").get(1).text();
                    threePm = elements1.get(14).select("td.text-center").get(1).text();
                    sixPm = elements1.get(17).select("td.text-center").get(1).text();
                    ninePm = elements1.get(20).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(24).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(2).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(5).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(8).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(11).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(14).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(17).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(20).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 2:
                    twelveAm = elements1.get(23).select("td.text-center").get(1).text();
                    threeAm = elements1.get(1).select("td.text-center").get(1).text();
                    sixAm = elements1.get(4).select("td.text-center").get(1).text();
                    nineAm = elements1.get(7).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(10).select("td.text-center").get(1).text();
                    threePm = elements1.get(13).select("td.text-center").get(1).text();
                    sixPm = elements1.get(16).select("td.text-center").get(1).text();
                    ninePm = elements1.get(19).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(23).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(1).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(4).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(7).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(10).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(13).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(16).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(19).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 3:
                    twelveAm = elements1.get(22).select("td.text-center").get(1).text();
                    threeAm = vanWeather.select("span[data-v-0e2dfac5]").get(3).text();
                    degreeSymbolIndex = threeAm.indexOf("°");
                    threeAm = threeAm.substring(0, degreeSymbolIndex).trim();
                    sixAm = elements1.get(3).select("td.text-center").get(1).text();
                    nineAm = elements1.get(6).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(9).select("td.text-center").get(1).text();
                    threePm = elements1.get(12).select("td.text-center").get(1).text();
                    sixPm = elements1.get(15).select("td.text-center").get(1).text();
                    ninePm = elements1.get(18).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(22).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = vanWeather.select("img.center-block.mrgn-tp-md").first().absUrl("src");
                    sixAmCond = elements1.get(3).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(6).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(9).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(12).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(15).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(18).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 4:
                    twelveAm = elements1.get(20).select("td.text-center").get(1).text();
                    threeAm = elements1.get(23).select("td.text-center").get(1).text();
                    sixAm = elements1.get(2).select("td.text-center").get(1).text();
                    nineAm = elements1.get(5).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(8).select("td.text-center").get(1).text();
                    threePm = elements1.get(11).select("td.text-center").get(1).text();
                    sixPm = elements1.get(14).select("td.text-center").get(1).text();
                    ninePm = elements1.get(17).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(20).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(23).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(2).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(5).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(8).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(11).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(14).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(17).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 5:
                    twelveAm = elements1.get(20).select("td.text-center").get(1).text();
                    threeAm = elements1.get(23).select("td.text-center").get(1).text();
                    sixAm = elements1.get(1).select("td.text-center").get(1).text();
                    nineAm = elements1.get(4).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(7).select("td.text-center").get(1).text();
                    threePm = elements1.get(10).select("td.text-center").get(1).text();
                    sixPm = elements1.get(13).select("td.text-center").get(1).text();
                    ninePm = elements1.get(16).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(20).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(23).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(1).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(4).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(7).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(10).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(13).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(16).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 6:
                    twelveAm = elements1.get(19).select("td.text-center").get(1).text();
                    threeAm = elements1.get(22).select("td.text-center").get(1).text();
                    sixAm = vanWeather.select("span[data-v-0e2dfac5]").get(3).text();
                    degreeSymbolIndex = sixAm.indexOf("°");
                    sixAm = sixAm.substring(0, degreeSymbolIndex).trim();
                    nineAm = elements1.get(3).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(6).select("td.text-center").get(1).text();
                    threePm = elements1.get(9).select("td.text-center").get(1).text();
                    sixPm = elements1.get(12).select("td.text-center").get(1).text();
                    ninePm = elements1.get(15).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(19).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(22).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = vanWeather.select("img.center-block.mrgn-tp-md").first().absUrl("src");
                    nineAmCond = elements1.get(3).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(6).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(9).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(12).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(15).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 7:
                    twelveAm = elements1.get(18).select("td.text-center").get(1).text();
                    threeAm = elements1.get(21).select("td.text-center").get(1).text();
                    sixAm = elements1.get(24).select("td.text-center").get(1).text();
                    nineAm = elements1.get(2).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(5).select("td.text-center").get(1).text();
                    threePm = elements1.get(8).select("td.text-center").get(1).text();
                    sixPm = elements1.get(11).select("td.text-center").get(1).text();
                    ninePm = elements1.get(14).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(18).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(21).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(24).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(2).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(5).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(8).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(11).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(14).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 8:
                    twelveAm = elements1.get(17).select("td.text-center").get(1).text();
                    threeAm = elements1.get(20).select("td.text-center").get(1).text();
                    sixAm = elements1.get(23).select("td.text-center").get(1).text();
                    nineAm = elements1.get(1).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(4).select("td.text-center").get(1).text();
                    threePm = elements1.get(7).select("td.text-center").get(1).text();
                    sixPm = elements1.get(10).select("td.text-center").get(1).text();
                    ninePm = elements1.get(13).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(17).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(20).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(23).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(1).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(4).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(7).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(10).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(13).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 9:
                    twelveAm = elements1.get(16).select("td.text-center").get(1).text();
                    threeAm = elements1.get(19).select("td.text-center").get(1).text();
                    sixAm = elements1.get(22).select("td.text-center").get(1).text();
                    nineAm = vanWeather.select("span[data-v-0e2dfac5]").get(3).text();
                    degreeSymbolIndex = nineAm.indexOf("°");
                    nineAm = nineAm.substring(0, degreeSymbolIndex).trim();
                    twelvePm = elements1.get(3).select("td.text-center").get(1).text();
                    threePm = elements1.get(6).select("td.text-center").get(1).text();
                    sixPm = elements1.get(9).select("td.text-center").get(1).text();
                    ninePm = elements1.get(12).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(16).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(19).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(22).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = vanWeather.select("img.center-block.mrgn-tp-md").first().absUrl("src");
                    twelvePmCond = elements1.get(3).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(6).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(9).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(12).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 10:
                    twelveAm = elements1.get(15).select("td.text-center").get(1).text();
                    threeAm = elements1.get(18).select("td.text-center").get(1).text();
                    sixAm = elements1.get(21).select("td.text-center").get(1).text();
                    nineAm = elements1.get(24).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(2).select("td.text-center").get(1).text();
                    threePm = elements1.get(5).select("td.text-center").get(1).text();
                    sixPm = elements1.get(8).select("td.text-center").get(1).text();
                    ninePm = elements1.get(11).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(15).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(18).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(21).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(24).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(2).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(5).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(8).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(11).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 11:
                    twelveAm = elements1.get(14).select("td.text-center").get(1).text();
                    threeAm = elements1.get(17).select("td.text-center").get(1).text();
                    sixAm = elements1.get(20).select("td.text-center").get(1).text();
                    nineAm = elements1.get(23).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(1).select("td.text-center").get(1).text();
                    threePm = elements1.get(4).select("td.text-center").get(1).text();
                    sixPm = elements1.get(7).select("td.text-center").get(1).text();
                    ninePm = elements1.get(10).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(14).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(17).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(20).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(23).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(1).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(4).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(7).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(10).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 12:
                    twelveAm = elements1.get(13).select("td.text-center").get(1).text();
                    threeAm = elements1.get(16).select("td.text-center").get(1).text();
                    sixAm = elements1.get(19).select("td.text-center").get(1).text();
                    nineAm = elements1.get(22).select("td.text-center").get(1).text();
                    twelvePm = vanWeather.select("span[data-v-0e2dfac5]").get(3).text();
                    degreeSymbolIndex = twelvePm.indexOf("°");
                    twelvePm = twelvePm.substring(0, degreeSymbolIndex).trim();
                    threePm = elements1.get(3).select("td.text-center").get(1).text();
                    sixPm = elements1.get(6).select("td.text-center").get(1).text();
                    ninePm = elements1.get(9).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(14).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(17).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(20).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(23).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = vanWeather.select("img.center-block.mrgn-tp-md").first().absUrl("src");
                    //threePmCond = elements1.get(2).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(3).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(7).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(10).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 13:
                    twelveAm = elements1.get(12).select("td.text-center").get(1).text();
                    threeAm = elements1.get(15).select("td.text-center").get(1).text();
                    sixAm = elements1.get(18).select("td.text-center").get(1).text();
                    nineAm = elements1.get(21).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(24).select("td.text-center").get(1).text();
                    threePm = elements1.get(2).select("td.text-center").get(1).text();
                    sixPm = elements1.get(5).select("td.text-center").get(1).text();
                    ninePm = elements1.get(8).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(12).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(15).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(18).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(21).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(24).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(2).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(5).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(8).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 14:
                    twelveAm = elements1.get(11).select("td.text-center").get(1).text();
                    threeAm = elements1.get(14).select("td.text-center").get(1).text();
                    sixAm = elements1.get(17).select("td.text-center").get(1).text();
                    nineAm = elements1.get(20).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(23).select("td.text-center").get(1).text();
                    threePm = elements1.get(1).select("td.text-center").get(1).text();
                    sixPm = elements1.get(4).select("td.text-center").get(1).text();
                    ninePm = elements1.get(7).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(11).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(14).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(17).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(20).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(23).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(1).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(4).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(7).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 15:
                    twelveAm = elements1.get(10).select("td.text-center").get(1).text();
                    threeAm = elements1.get(13).select("td.text-center").get(1).text();
                    sixAm = elements1.get(16).select("td.text-center").get(1).text();
                    nineAm = elements1.get(19).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(22).select("td.text-center").get(1).text();
                    threePm = vanWeather.select("span[data-v-0e2dfac5]").get(3).text();
                    degreeSymbolIndex = threePm.indexOf("°");
                    threePm = threePm.substring(0, degreeSymbolIndex).trim();
                    sixPm = elements1.get(3).select("td.text-center").get(1).text();
                    ninePm = elements1.get(6).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(10).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(13).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(16).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(19).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(22).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = vanWeather.select("img.center-block.mrgn-tp-md").first().absUrl("src");
                    System.out.println("done");
                    System.out.println(threePmCond);
                    sixPmCond = elements1.get(3).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(6).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 16:
                    twelveAm = elements1.get(9).select("td.text-center").get(1).text();
                    threeAm = elements1.get(12).select("td.text-center").get(1).text();
                    sixAm = elements1.get(15).select("td.text-center").get(1).text();
                    nineAm = elements1.get(18).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(21).select("td.text-center").get(1).text();
                    threePm = elements1.get(24).select("td.text-center").get(1).text();
                    sixPm = elements1.get(2).select("td.text-center").get(1).text();
                    ninePm = elements1.get(5).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(9).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(12).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(15).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(18).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(21).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(24).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(2).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(5).select("span.pull-left img.media-object").first().absUrl("src");
                    System.out.println("start");
                    System.out.println(ninePmCond);
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 17:
                    twelveAm = elements1.get(8).select("td.text-center").get(1).text();
                    threeAm = elements1.get(11).select("td.text-center").get(1).text();
                    sixAm = elements1.get(14).select("td.text-center").get(1).text();
                    nineAm = elements1.get(17).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(20).select("td.text-center").get(1).text();
                    threePm = elements1.get(23).select("td.text-center").get(1).text();
                    sixPm = elements1.get(1).select("td.text-center").get(1).text();
                    ninePm = elements1.get(4).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(8).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(11).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(14).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(17).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(20).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(23).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(1).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(4).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 18:
                    twelveAm = elements1.get(7).select("td.text-center").get(1).text();
                    threeAm = elements1.get(10).select("td.text-center").get(1).text();
                    sixAm = elements1.get(13).select("td.text-center").get(1).text();
                    nineAm = elements1.get(16).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(19).select("td.text-center").get(1).text();
                    threePm = elements1.get(22).select("td.text-center").get(1).text();
                    sixPm = vanWeather.select("span[data-v-0e2dfac5]").get(3).text();
                    degreeSymbolIndex = sixPm.indexOf("°");
                    sixPm = sixPm.substring(0, degreeSymbolIndex).trim();
                    ninePm = elements1.get(3).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(7).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(10).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(13).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(16).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(19).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(22).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = vanWeather.select("img.center-block.mrgn-tp-md").first().absUrl("src");
                    ninePmCond = elements1.get(3).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 19:
                    twelveAm = elements1.get(6).select("td.text-center").get(1).text();
                    threeAm = elements1.get(9).select("td.text-center").get(1).text();
                    sixAm = elements1.get(12).select("td.text-center").get(1).text();
                    nineAm = elements1.get(15).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(18).select("td.text-center").get(1).text();
                    threePm = elements1.get(21).select("td.text-center").get(1).text();
                    sixPm = elements1.get(24).select("td.text-center").get(1).text();
                    ninePm = elements1.get(2).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(6).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(9).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(12).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(15).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(18).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(21).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(24).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(2).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 20:
                    twelveAm = elements1.get(5).select("td.text-center").get(1).text();
                    threeAm = elements1.get(8).select("td.text-center").get(1).text();
                    sixAm = elements1.get(11).select("td.text-center").get(1).text();
                    nineAm = elements1.get(14).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(17).select("td.text-center").get(1).text();
                    threePm = elements1.get(20).select("td.text-center").get(1).text();
                    sixPm = elements1.get(23).select("td.text-center").get(1).text();
                    ninePm = elements1.get(1).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(5).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(8).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(11).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(14).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(17).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(20).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(23).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(1).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 21:
                    twelveAm = elements1.get(4).select("td.text-center").get(1).text();
                    threeAm = elements1.get(7).select("td.text-center").get(1).text();
                    sixAm = elements1.get(10).select("td.text-center").get(1).text();
                    nineAm = elements1.get(13).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(16).select("td.text-center").get(1).text();
                    threePm = elements1.get(19).select("td.text-center").get(1).text();
                    sixPm = elements1.get(22).select("td.text-center").get(1).text();
                    ninePm = vanWeather.select("span[data-v-0e2dfac5]").get(3).text();
                    degreeSymbolIndex = ninePm.indexOf("°");
                    ninePm = ninePm.substring(0, degreeSymbolIndex).trim();
                    twelveAmCond = elements1.get(4).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(7).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(10).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(13).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(16).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(19).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(22).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = vanWeather.select("img.center-block.mrgn-tp-md").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 22:
                    twelveAm = elements1.get(3).select("td.text-center").get(1).text();   //DONE
                    threeAm = elements1.get(6).select("td.text-center").get(1).text();
                    sixAm = elements1.get(9).select("td.text-center").get(1).text();
                    nineAm = elements1.get(12).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(15).select("td.text-center").get(1).text();
                    threePm = elements1.get(18).select("td.text-center").get(1).text();
                    sixPm = elements1.get(21).select("td.text-center").get(1).text();
                    ninePm = elements1.get(24).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(3).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(6).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(9).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(12).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(15).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(18).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(21).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(24).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 23:
                    twelveAm = elements1.get(1).select("td.text-center").get(1).text();
                    threeAm = elements1.get(4).select("td.text-center").get(1).text();
                    sixAm = elements1.get(7).select("td.text-center").get(1).text();
                    nineAm = elements1.get(10).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(13).select("td.text-center").get(1).text();
                    threePm = elements1.get(16).select("td.text-center").get(1).text();
                    sixPm = elements1.get(19).select("td.text-center").get(1).text();
                    ninePm = elements1.get(22).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(1).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(4).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(7).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(10).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(13).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(16).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(19).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(22).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
            }
        } catch (IndexOutOfBoundsException e) { // met note exists
            switch (hour) {
                case 0:
                    twelveAm = vanWeather.select("span[data-v-0e2dfac5]").get(3).text();
                    int degreeSymbolIndex = twelveAm.indexOf("°");
                    twelveAm = twelveAm.substring(0, degreeSymbolIndex).trim();
                    threeAm = elements1.get(4).select("td.text-center").get(1).text();
                    sixAm = elements1.get(7).select("td.text-center").get(1).text();
                    nineAm = elements1.get(10).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(13).select("td.text-center").get(1).text();
                    threePm = elements1.get(16).select("td.text-center").get(1).text();
                    sixPm = elements1.get(19).select("td.text-center").get(1).text();
                    ninePm = elements1.get(22).select("td.text-center").get(1).text();
                    twelveAmCond = vanWeather.select("img.center-block.mrgn-tp-md").first().absUrl("src");
                    threeAmCond = elements1.get(4).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(7).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(10).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(13).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(16).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(19).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(22).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 1:
                    twelveAm = elements1.get(25).select("td.text-center").get(1).text();
                    threeAm = elements1.get(3).select("td.text-center").get(1).text();
                    sixAm = elements1.get(6).select("td.text-center").get(1).text();
                    nineAm = elements1.get(9).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(12).select("td.text-center").get(1).text();
                    threePm = elements1.get(15).select("td.text-center").get(1).text();
                    sixPm = elements1.get(18).select("td.text-center").get(1).text();
                    ninePm = elements1.get(21).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(25).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(3).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(6).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(9).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(12).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(15).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(18).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(21).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 2:
                    twelveAm = elements1.get(24).select("td.text-center").get(1).text();
                    threeAm = elements1.get(2).select("td.text-center").get(1).text();
                    sixAm = elements1.get(5).select("td.text-center").get(1).text();
                    nineAm = elements1.get(8).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(11).select("td.text-center").get(1).text();
                    threePm = elements1.get(14).select("td.text-center").get(1).text();
                    sixPm = elements1.get(17).select("td.text-center").get(1).text();
                    ninePm = elements1.get(20).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(25).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(3).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(6).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(9).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(12).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(15).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(18).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(21).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 3:
                    twelveAm = elements1.get(23).select("td.text-center").get(1).text();
                    threeAm = vanWeather.select("span[data-v-0e2dfac5]").get(3).text();
                    degreeSymbolIndex = threeAm.indexOf("°");
                    threeAm = threeAm.substring(0, degreeSymbolIndex).trim();
                    sixAm = elements1.get(4).select("td.text-center").get(1).text();
                    nineAm = elements1.get(7).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(10).select("td.text-center").get(1).text();
                    threePm = elements1.get(13).select("td.text-center").get(1).text();
                    sixPm = elements1.get(16).select("td.text-center").get(1).text();
                    ninePm = elements1.get(19).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(23).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = vanWeather.select("img.center-block.mrgn-tp-md").first().absUrl("src");
                    sixAmCond = elements1.get(4).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(7).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(10).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(13).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(16).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(19).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 4:
                    twelveAm = elements1.get(22).select("td.text-center").get(1).text();
                    threeAm = elements1.get(25).select("td.text-center").get(1).text();
                    sixAm = elements1.get(3).select("td.text-center").get(1).text();
                    nineAm = elements1.get(6).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(9).select("td.text-center").get(1).text();
                    threePm = elements1.get(12).select("td.text-center").get(1).text();
                    sixPm = elements1.get(15).select("td.text-center").get(1).text();
                    ninePm = elements1.get(18).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(22).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(25).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(3).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(6).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(9).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(12).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(15).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(18).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 5:
                    twelveAm = elements1.get(21).select("td.text-center").get(1).text();
                    threeAm = elements1.get(24).select("td.text-center").get(1).text();
                    sixAm = elements1.get(2).select("td.text-center").get(1).text();
                    nineAm = elements1.get(5).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(8).select("td.text-center").get(1).text();
                    threePm = elements1.get(11).select("td.text-center").get(1).text();
                    sixPm = elements1.get(14).select("td.text-center").get(1).text();
                    ninePm = elements1.get(17).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(21).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(24).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(2).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(5).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(8).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(11).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(14).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(17).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 6:
                    twelveAm = elements1.get(20).select("td.text-center").get(1).text();
                    threeAm = elements1.get(23).select("td.text-center").get(1).text();
                    sixAm = vanWeather.select("span[data-v-0e2dfac5]").get(3).text();
                    degreeSymbolIndex = sixAm.indexOf("°");
                    sixAm = sixAm.substring(0, degreeSymbolIndex).trim();
                    nineAm = elements1.get(4).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(7).select("td.text-center").get(1).text();
                    threePm = elements1.get(10).select("td.text-center").get(1).text();
                    sixPm = elements1.get(13).select("td.text-center").get(1).text();
                    ninePm = elements1.get(16).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(20).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(23).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = vanWeather.select("img.center-block.mrgn-tp-md").first().absUrl("src");
                    nineAmCond = elements1.get(4).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(7).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(10).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(13).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(16).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 7:
                    twelveAm = elements1.get(19).select("td.text-center").get(1).text();
                    threeAm = elements1.get(22).select("td.text-center").get(1).text();
                    sixAm = elements1.get(25).select("td.text-center").get(1).text();
                    nineAm = elements1.get(3).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(6).select("td.text-center").get(1).text();
                    threePm = elements1.get(9).select("td.text-center").get(1).text();
                    sixPm = elements1.get(12).select("td.text-center").get(1).text();
                    ninePm = elements1.get(15).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(19).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(22).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(25).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(3).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(6).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(9).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(12).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(15).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 8:
                    twelveAm = elements1.get(18).select("td.text-center").get(1).text();
                    threeAm = elements1.get(21).select("td.text-center").get(1).text();
                    sixAm = elements1.get(24).select("td.text-center").get(1).text();
                    nineAm = elements1.get(2).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(5).select("td.text-center").get(1).text();
                    threePm = elements1.get(8).select("td.text-center").get(1).text();
                    sixPm = elements1.get(11).select("td.text-center").get(1).text();
                    ninePm = elements1.get(14).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(18).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(21).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(24).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(2).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(5).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(8).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(11).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(14).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 9:
                    twelveAm = elements1.get(17).select("td.text-center").get(1).text();
                    threeAm = elements1.get(20).select("td.text-center").get(1).text();
                    sixAm = elements1.get(23).select("td.text-center").get(1).text();
                    nineAm = vanWeather.select("span[data-v-0e2dfac5]").get(3).text();
                    degreeSymbolIndex = nineAm.indexOf("°");
                    nineAm = nineAm.substring(0, degreeSymbolIndex).trim();
                    twelvePm = elements1.get(4).select("td.text-center").get(1).text();
                    threePm = elements1.get(7).select("td.text-center").get(1).text();
                    sixPm = elements1.get(10).select("td.text-center").get(1).text();
                    ninePm = elements1.get(13).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(17).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(20).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(23).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = vanWeather.select("img.center-block.mrgn-tp-md").first().absUrl("src");
                    twelvePmCond = elements1.get(4).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(7).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(10).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(13).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 10:
                    twelveAm = elements1.get(16).select("td.text-center").get(1).text();
                    threeAm = elements1.get(19).select("td.text-center").get(1).text();
                    sixAm = elements1.get(22).select("td.text-center").get(1).text();
                    nineAm = elements1.get(25).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(3).select("td.text-center").get(1).text();
                    threePm = elements1.get(6).select("td.text-center").get(1).text();
                    sixPm = elements1.get(9).select("td.text-center").get(1).text();
                    ninePm = elements1.get(12).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(16).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(19).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(22).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(25).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(3).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(6).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(9).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(12).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 11:
                    twelveAm = elements1.get(15).select("td.text-center").get(1).text();
                    threeAm = elements1.get(18).select("td.text-center").get(1).text();
                    sixAm = elements1.get(21).select("td.text-center").get(1).text();
                    nineAm = elements1.get(24).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(2).select("td.text-center").get(1).text();
                    threePm = elements1.get(5).select("td.text-center").get(1).text();
                    sixPm = elements1.get(8).select("td.text-center").get(1).text();
                    ninePm = elements1.get(11).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(15).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(18).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(21).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(24).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(2).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(5).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(8).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(11).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 12:
                    twelveAm = elements1.get(14).select("td.text-center").get(1).text();
                    threeAm = elements1.get(17).select("td.text-center").get(1).text();
                    sixAm = elements1.get(20).select("td.text-center").get(1).text();
                    nineAm = elements1.get(23).select("td.text-center").get(1).text();
                    twelvePm = vanWeather.select("span[data-v-0e2dfac5]").get(3).text();
                    degreeSymbolIndex = twelvePm.indexOf("°");
                    twelvePm = twelvePm.substring(0, degreeSymbolIndex).trim();
                    threePm = elements1.get(4).select("td.text-center").get(1).text();
                    sixPm = elements1.get(7).select("td.text-center").get(1).text();
                    ninePm = elements1.get(10).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(14).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(17).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(20).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(23).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = vanWeather.select("img.center-block.mrgn-tp-md").first().absUrl("src");
                    //threePmCond = elements1.get(2).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(4).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(7).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(10).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 13:
                    twelveAm = elements1.get(13).select("td.text-center").get(1).text();
                    threeAm = elements1.get(16).select("td.text-center").get(1).text();
                    sixAm = elements1.get(19).select("td.text-center").get(1).text();
                    nineAm = elements1.get(22).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(25).select("td.text-center").get(1).text();
                    threePm = elements1.get(3).select("td.text-center").get(1).text();
                    sixPm = elements1.get(6).select("td.text-center").get(1).text();
                    ninePm = elements1.get(9).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(13).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(16).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(19).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(22).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(25).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(3).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(6).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(9).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 14:
                    twelveAm = elements1.get(12).select("td.text-center").get(1).text();
                    threeAm = elements1.get(15).select("td.text-center").get(1).text();
                    sixAm = elements1.get(18).select("td.text-center").get(1).text();
                    nineAm = elements1.get(21).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(24).select("td.text-center").get(1).text();
                    threePm = elements1.get(2).select("td.text-center").get(1).text();
                    sixPm = elements1.get(5).select("td.text-center").get(1).text();
                    ninePm = elements1.get(8).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(12).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(15).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(18).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(21).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(24).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(2).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(5).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(8).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 15:
                    twelveAm = elements1.get(11).select("td.text-center").get(1).text();
                    threeAm = elements1.get(14).select("td.text-center").get(1).text();
                    sixAm = elements1.get(17).select("td.text-center").get(1).text();
                    nineAm = elements1.get(20).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(23).select("td.text-center").get(1).text();
                    threePm = vanWeather.select("span[data-v-0e2dfac5]").get(3).text();
                    degreeSymbolIndex = threePm.indexOf("°");
                    threePm = threePm.substring(0, degreeSymbolIndex).trim();
                    sixPm = elements1.get(4).select("td.text-center").get(1).text();
                    ninePm = elements1.get(7).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(11).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(14).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(17).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(20).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(23).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = vanWeather.select("img.center-block.mrgn-tp-md").first().absUrl("src");
                    sixPmCond = elements1.get(4).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(7).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 16:
                    twelveAm = elements1.get(10).select("td.text-center").get(1).text();
                    threeAm = elements1.get(13).select("td.text-center").get(1).text();
                    sixAm = elements1.get(16).select("td.text-center").get(1).text();
                    nineAm = elements1.get(19).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(22).select("td.text-center").get(1).text();
                    threePm = elements1.get(25).select("td.text-center").get(1).text();
                    sixPm = elements1.get(3).select("td.text-center").get(1).text();
                    ninePm = elements1.get(6).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(10).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(13).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(16).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(19).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(22).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(25).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(3).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(6).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 17:
                    twelveAm = elements1.get(9).select("td.text-center").get(1).text();
                    threeAm = elements1.get(12).select("td.text-center").get(1).text();
                    sixAm = elements1.get(15).select("td.text-center").get(1).text();
                    nineAm = elements1.get(18).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(21).select("td.text-center").get(1).text();
                    threePm = elements1.get(24).select("td.text-center").get(1).text();
                    sixPm = elements1.get(2).select("td.text-center").get(1).text();
                    ninePm = elements1.get(5).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(9).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(12).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(15).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(18).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(21).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(24).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(2).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(5).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 18:
                    twelveAm = elements1.get(8).select("td.text-center").get(1).text();
                    threeAm = elements1.get(11).select("td.text-center").get(1).text();
                    sixAm = elements1.get(14).select("td.text-center").get(1).text();
                    nineAm = elements1.get(17).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(20).select("td.text-center").get(1).text();
                    threePm = elements1.get(23).select("td.text-center").get(1).text();
                    sixPm = vanWeather.select("span[data-v-0e2dfac5]").get(3).text();
                    degreeSymbolIndex = sixPm.indexOf("°");
                    sixPm = sixPm.substring(0, degreeSymbolIndex).trim();
                    ninePm = elements1.get(4).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(8).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(11).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(14).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(17).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(20).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(23).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = vanWeather.select("img.center-block.mrgn-tp-md").first().absUrl("src");
                    ninePmCond = elements1.get(4).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 19:
                    twelveAm = elements1.get(7).select("td.text-center").get(1).text();
                    threeAm = elements1.get(10).select("td.text-center").get(1).text();
                    sixAm = elements1.get(13).select("td.text-center").get(1).text();
                    nineAm = elements1.get(16).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(19).select("td.text-center").get(1).text();
                    threePm = elements1.get(22).select("td.text-center").get(1).text();
                    sixPm = elements1.get(25).select("td.text-center").get(1).text();
                    ninePm = elements1.get(3).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(7).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(10).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(13).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(16).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(19).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(22).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(25).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(3).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 20:
                    twelveAm = elements1.get(6).select("td.text-center").get(1).text();
                    threeAm = elements1.get(9).select("td.text-center").get(1).text();
                    sixAm = elements1.get(12).select("td.text-center").get(1).text();
                    nineAm = elements1.get(15).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(18).select("td.text-center").get(1).text();
                    threePm = elements1.get(21).select("td.text-center").get(1).text();
                    sixPm = elements1.get(24).select("td.text-center").get(1).text();
                    ninePm = elements1.get(2).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(6).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(9).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(12).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(15).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(18).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(21).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(24).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(2).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 21:
                    twelveAm = elements1.get(5).select("td.text-center").get(1).text();
                    threeAm = elements1.get(8).select("td.text-center").get(1).text();
                    sixAm = elements1.get(11).select("td.text-center").get(1).text();
                    nineAm = elements1.get(14).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(17).select("td.text-center").get(1).text();
                    threePm = elements1.get(20).select("td.text-center").get(1).text();
                    sixPm = elements1.get(23).select("td.text-center").get(1).text();
                    ninePm = vanWeather.select("span[data-v-0e2dfac5]").get(3).text();
                    degreeSymbolIndex = ninePm.indexOf("°");
                    ninePm = ninePm.substring(0, degreeSymbolIndex).trim();
                    twelveAmCond = elements1.get(5).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(8).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(11).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(14).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(17).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(20).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(23).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = vanWeather.select("img.center-block.mrgn-tp-md").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 22:
                    twelveAm = elements1.get(4).select("td.text-center").get(1).text();
                    threeAm = elements1.get(7).select("td.text-center").get(1).text();
                    sixAm = elements1.get(10).select("td.text-center").get(1).text();
                    nineAm = elements1.get(13).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(16).select("td.text-center").get(1).text();
                    threePm = elements1.get(19).select("td.text-center").get(1).text();
                    sixPm = elements1.get(22).select("td.text-center").get(1).text();
                    ninePm = elements1.get(25).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(4).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(7).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(10).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(13).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(16).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(19).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(22).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(25).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
                case 23:
                    twelveAm = elements1.get(3).select("td.text-center").get(1).text();
                    threeAm = elements1.get(6).select("td.text-center").get(1).text();
                    sixAm = elements1.get(9).select("td.text-center").get(1).text();
                    nineAm = elements1.get(12).select("td.text-center").get(1).text();
                    twelvePm = elements1.get(15).select("td.text-center").get(1).text();
                    threePm = elements1.get(18).select("td.text-center").get(1).text();
                    sixPm = elements1.get(21).select("td.text-center").get(1).text();
                    ninePm = elements1.get(24).select("td.text-center").get(1).text();
                    twelveAmCond = elements1.get(3).select("span.pull-left img.media-object").first().absUrl("src");
                    threeAmCond = elements1.get(6).select("span.pull-left img.media-object").first().absUrl("src");
                    sixAmCond = elements1.get(9).select("span.pull-left img.media-object").first().absUrl("src");
                    nineAmCond = elements1.get(12).select("span.pull-left img.media-object").first().absUrl("src");
                    twelvePmCond = elements1.get(15).select("span.pull-left img.media-object").first().absUrl("src");
                    threePmCond = elements1.get(18).select("span.pull-left img.media-object").first().absUrl("src");
                    sixPmCond = elements1.get(21).select("span.pull-left img.media-object").first().absUrl("src");
                    ninePmCond = elements1.get(24).select("span.pull-left img.media-object").first().absUrl("src");
                    hourlyTemps = addToList(twelveAm, threeAm, sixAm, nineAm, twelvePm, threePm, sixPm, ninePm, twelveAmCond, threeAmCond, sixAmCond, nineAmCond, twelvePmCond, threePmCond, sixPmCond, ninePmCond);
                    break;
            }
        }
        return hourlyTemps;
    }

    public ArrayList<String> addToList(String twelvea, String threea, String sixa, String ninea, String twelvep, String threep, String sixp, String ninep, String twelveaC, String threeaC,
                                       String sixaC, String nineaC, String twelvepC, String threepC, String sixpC, String ninepC) {
        ArrayList<String> list = new ArrayList<>();
        list.add(twelvea);
        list.add(threea);
        list.add(sixa);
        list.add(ninea);
        list.add(twelvep);
        list.add(threep);
        list.add(sixp);
        list.add(ninep);
        list.add(twelveaC);
        list.add(threeaC);
        list.add(sixaC);
        list.add(nineaC);
        list.add(twelvepC);
        list.add(threepC);
        list.add(sixpC);
        list.add(ninepC);
        return list;
    }

    public void setCypressPic(String text, ImageView image) {
        String[] arr = text.split(" ");
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0)))
                    .append(arr[i].substring(1)).append(" ");
        }
        text = sb.toString().trim();

        if (text.equals("Cloudy")) {
            image.setImageResource(R.drawable.cloudy);
            return;
        }
        if (text.equals("Clear Night")) {
            image.setImageResource(R.drawable.clear_night);
            return;
        }
        if (text.equals("Rain/Snow")) {
            image.setImageResource(R.drawable.wet_snow_mixed_with_rain);
            return;
        }
        if (text.equals("Sunny")) {
            image.setImageResource(R.drawable.sunny);
            return;
        }
        if (text.equals("Snow")) {
            image.setImageResource(R.drawable.snow);
            return;
        }
        if (text.equals("Rain")) {
            image.setImageResource(R.drawable.rain);
            return;
        }
        if (text.equals("Partly Cloudy Night")) {
            image.setImageResource(R.drawable.cloudy_periods);
            return;
        }
        if (text.equals("Snow Showers/some Snow")) {
            image.setImageResource(R.drawable.light_snow);
            return;
        }
        if (text.equals("Windy")) {
            image.setImageResource(R.drawable.windy);
            return;
        }
        if (text.equals("Freezing Rain/ice Pellets")) {
            image.setImageResource(R.drawable.ice_pellets);
            return;
        }
        if (text.equals("Cloud/sun/mixed")) {
            image.setImageResource(R.drawable.a_mix_of_sun_and_cloud);
            return;
        }
        if (hour >= 7 && hour < 20) {
            switch (text) {
            }
        } else {
            switch (text) {
            }
        }
    }


    public void setGrousePic(String text, ImageView image) {
        String[] arr = text.split(" ");
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0)))
                    .append(arr[i].substring(1)).append(" ");
        }
        text = sb.toString().trim();

        if (text.equals("High Overcast Skies")) {
            image.setImageResource(R.drawable.cloudy);
            return;
        }
        if (text.equals("Low Overcast Skies")) {
            image.setImageResource(R.drawable.cloudy);
            return;
        }
        if (text.equals("Clear Skies")) {
            image.setImageResource(R.drawable.clear_night);
            return;
        }
        if (text.equals("Sunny Skies") || text.equals("Blue Bird Skies!")) {
            image.setImageResource(R.drawable.sunny);
            return;
        }
        if (text.equals("Raining Skies")) {
            image.setImageResource(R.drawable.chance_of_showers);
            return;
        }
        if (text.equals("Mix Of Snow & Rain")) {
            image.setImageResource(R.drawable.wet_snow_mixed_with_rain);
            return;
        }
        if (text.equals("Snowing Heavily")) {
            image.setImageResource(R.drawable.snow);
            return;
        }
        if (text.equals("Lightly Snowing Skies")) {
            image.setImageResource(R.drawable.light_snow);
            return;
        }
        if (text.equals("Snowy Skies")) {
            image.setImageResource(R.drawable.snow);
            return;
        }
        if (text.equals("Flurries")) {
            image.setImageResource(R.drawable.light_snow);
            return;
        }
        if (text.equals("Mix Of Sun & Cloud")) {
            image.setImageResource(R.drawable.mainly_sunny);
            return;
        }
        if (text.equals("Lightly Raining Skies")) {
            image.setImageResource(R.drawable.chance_of_showers);
            return;
        }

        if (hour >= 8 && hour < 16) {
            switch (text) {
                case "Mainly Cloudy Skies":
                    image.setImageResource(R.drawable.mainly_cloudy_day);
                    return;
            }
        } else {
            switch (text) {
                case "Mainly Cloudy Skies":
                    image.setImageResource(R.drawable.mainly_cloudy_night);
                    return;
                case "Clear Skies":
                    image.setImageResource(R.drawable.clear_night);
                    return;
            }
        }
    }

    public void setSeymourPic(String text, ImageView image) {
        String[] arr = text.split(" ");
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0)))
                    .append(arr[i].substring(1)).append(" ");
        }
        text = sb.toString().trim();

        switch (text) {
            case "Light Rain":
                image.setImageResource(R.drawable.chance_of_showers);
                return;
            case "Sunny":
            case "Fresh Snow":
            case "Chilly":
                image.setImageResource(R.drawable.sunny);
                return;
            case "Snowing":
            case "Fresh snow":
                image.setImageResource(R.drawable.snow);
                return;
            case "Clear":
                image.setImageResource(R.drawable.clear_night);
                return;
            case "Overcast":
                image.setImageResource(R.drawable.overcast);
                return;
            case "Light Snow":
                image.setImageResource(R.drawable.light_snow);
                return;
            case "Mix Of Sun And Cloud":
                image.setImageResource(R.drawable.a_mix_of_sun_and_cloud);
                return;
            case "Raining":
                image.setImageResource(R.drawable.rain);
                return;
            case "Windy":
                image.setImageResource(R.drawable.windy);
                return;
            case "A Mix Of Sun And Snow":
                image.setImageResource(R.drawable.chance_of_flurries);
                return;
            case "Fog":
                image.setImageResource(R.drawable.fog);
                return;
            case "Clear Skies":
                if (hour < 17) {
                    image.setImageResource(R.drawable.sunny);
                } else {
                    image.setImageResource(R.drawable.clear_night);
                }
                return;
        }
    }

    public void openCypress() {
        Intent intent = new Intent(this, Cypress.class);
        startActivity(intent);
    }

    public void openGrouse() {
        Intent intent = new Intent(this, Grouse.class);
        startActivity(intent);
    }

    public void openSeymour() {
        Intent intent = new Intent(this, Seymour.class);
        startActivity(intent);
    }

    public void openError() {
        Intent intent = new Intent(this, Error.class);
        intent.putExtra("ErrorFrom", "Vancouver");
        startActivity(intent);
    }

    public void refresh() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openFAQ() {
        Intent intent = new Intent(this, FAQ.class);
        startActivity(intent);
    }
}
