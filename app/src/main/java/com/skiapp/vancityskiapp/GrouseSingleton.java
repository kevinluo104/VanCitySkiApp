package com.skiapp.vancityskiapp;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;


public class GrouseSingleton extends AppCompatActivity {

    private static GrouseSingleton instance = null;
    private static Context context;
    public Document grouseWeather;
    public String temperature = "Temperature";
    public String weather = "Weather";
    public String visibility = "Visibility:";
    private static ResultListener listener;
    public String overnightSnow = "OnightSnow";
    public String twelveHrSnow = "12hSnow";
    public String twentyFourHrSnow = "24hSnow";
    public String fortyEightHrSnow = "48hSnow";
    public int liftsOpen = 0;
    public int runsOpen = 0;
    public int terrainParksOpen = 0;
    public int snowshoeTrailsOpen = 0;
    public String greenwayChair = "";
    public String olympicExpressChair = "";
    public String peakChair = "";
    public String screamingEagleChair = "";
    public String magicCarpet = "";
    public String chaletRoad = "";
    public String skiWee = "";
    public String blueFace = "";
    public String coolasCorner = "";
    public String dogleg = "";
    public String grinderTracks = "";
    public String lowerBuckhorn = "";
    public String mountainHighway = "";
    public String sideCut = "";
    public String tyeeChute = "";
    public String blazes = "";
    public String coffin = "";
    public String hades = "";
    public String lowerPeak = "";
    public String peak = "";
    public String devilsAdvocate = "";
    public String purgatory = "";
    public String paradise = "";
    public String theCut = "";
    public String centennial = "";
    public String deliverance = "";
    public String expo = "";
    public String heavensSake = "";
    public String lowerSideCut = "";
    public String paperTrail = "";
    public String skyline = "";
    public String upperBuckhorn = "";
    public String chimney = "";
    public String expoGlades = "";
    public String inferno = "";
    public String outerLimits = "";
    public String upperBlazes = "";
    public String peakGlades = "";
    public String cutJumpLine = "";
    public String paradiseJibPark = "";
    public String cutRookiePark = "";
    public String cutPark = "";
    public String grouseWoods = "";
    public String grousePark = "";
    public String blueGrouseLoop = "";
    public String damMountainLoop = "";
    public String snowshoeGrind = "";
    public String thunderbirdRidge = "";
    public String lightWalk = "";
    public String picture = "";
    private int counter = 0;


    private GrouseSingleton() {
        startThread();
    }

    public static GrouseSingleton getInstance(ResultListener listener, Context context) throws IOException {
        GrouseSingleton.listener = listener;
        GrouseSingleton.context = context;
        if (instance == null) {
            instance = new GrouseSingleton();
        }
        return instance;
        }

        public void startThread() {
            final long startTime = System.currentTimeMillis();
            new Thread() {
                public void run() {
                    try {
                        grouseWeather = Jsoup.connect("https://www.grousemountain.com/current_conditions").get();
                        runOnUiThread( new Runnable()
                        {
                            public void run()
                            {
                                temperature = grouseWeather.select("h3.metric").first().text();
                                weather = grouseWeather.select("p").get(6).text();
                                String[] arr = weather.split(" ");
                                StringBuffer sb = new StringBuffer();

                                for (int i = 0; i < arr.length; i++) {
                                    sb.append(Character.toUpperCase(arr[i].charAt(0)))
                                            .append(arr[i].substring(1)).append(" ");
                                }
                                weather = sb.toString().trim();
                                visibility = grouseWeather.select("div.current_status p").first().text();
                                System.out.println("visibility of gorouse: " + visibility);
                                overnightSnow = setNewSnow(1);;
                                twelveHrSnow = setNewSnow(0);
                                twentyFourHrSnow = setNewSnow(2);
                                fortyEightHrSnow = setNewSnow(3);
                                if (counter == 0) {
                                    greenwayChair = setOpenClosedLifts(3);
                                    olympicExpressChair = setOpenClosedLifts(0);
                                    screamingEagleChair = setOpenClosedLifts(2);
                                    peakChair = setOpenClosedLifts(1);
                                    magicCarpet = setOpenClosedLifts(4);
                                    chaletRoad = setOpenClosedRuns(0);
                                    paradise = setOpenClosedRuns(1);
                                    skiWee = setOpenClosedRuns(2);
                                    theCut = setOpenClosedRuns(3);
                                    blueFace = setOpenClosedRuns(4);
                                    centennial = setOpenClosedRuns(5);
                                    deliverance = setOpenClosedRuns(6);
                                    dogleg = setOpenClosedRuns(7);
                                    expo = setOpenClosedRuns(8);
                                    heavensSake = setOpenClosedRuns(9);
                                    lowerBuckhorn = setOpenClosedRuns(10);
                                    lowerPeak = setOpenClosedRuns(11);
                                    lowerSideCut = setOpenClosedRuns(12);
                                    paperTrail = setOpenClosedRuns(13);
                                    peak = setOpenClosedRuns(14);
                                    sideCut = setOpenClosedRuns(15);
                                    skyline = setOpenClosedRuns(16);
                                    tyeeChute = setOpenClosedRuns(17);
                                    upperBuckhorn = setOpenClosedRuns(18);
                                    blazes = setOpenClosedRuns(19);
                                    coffin = setOpenClosedRuns(20);
                                    hades = setOpenClosedRuns(21);
                                    inferno = setOpenClosedRuns(22);
                                    outerLimits = setOpenClosedRuns(23);
                                    devilsAdvocate = setOpenClosedRuns(24);
                                    purgatory = setOpenClosedRuns(25);
                                    peakGlades = setOpenClosedRuns(26);
                                    grinderTracks = setOpenClosedRuns(27);
                                    expoGlades = setOpenClosedRuns(28);
                                    coolasCorner = setOpenClosedRuns(29);
                                    chimney = setOpenClosedRuns(30);
                                    upperBlazes = setOpenClosedRuns(31);
                                    mountainHighway = setOpenClosedRuns(32);
                                   // cutJumpLine = setOpenClosedParks(2);
                                    paradiseJibPark = setOpenClosedParks(1);
                                    cutRookiePark = setOpenClosedParks(2);
                                    cutPark = setOpenClosedParks(0);
                                    grousePark = setOpenClosedParks(3);
                                    blueGrouseLoop = setOpenClosedSnowshoe(0);
                                    damMountainLoop = setOpenClosedSnowshoe(2);
                                    snowshoeGrind = setOpenClosedSnowshoe(3);
                                    thunderbirdRidge = setOpenClosedSnowshoe(4);
                                    lightWalk = setOpenClosedSnowshoe(1);
                                    picture = grouseWeather.select("p").get(6).text();
                                }
                                counter++;
                                listener.onResultFetched();
                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                        while(System.currentTimeMillis() - startTime <= 5000){
                            run();
                        }
                        openError();
                    }
                }
            }.start();
        }

    public String setNewSnow(int rowNum) {
        Element data = grouseWeather.select("div.conditions-snow-report__content").get(0);
        Element status = data.select("li").get(rowNum);
        String text;
        try {
            Element dcm2 = status.select("h3.metric").get(0);
            text = (dcm2.text());
        } catch (IndexOutOfBoundsException e) {
            Element dcm4 = status.select("h3.metric").get(0);
            text = (dcm4.text());
        }
        return text;
    }

    public String setOpenClosedLifts(int rowNum) {
        Element data = grouseWeather.select("ul.data-table").get(2);
        Element status = data.select("li").get(rowNum);
        String lift;
        try {
            Element dcm2 = status.select("span.closed").get(0);
            lift = "closed";
        } catch (IndexOutOfBoundsException e) {
            Element dcm4 = status.select("span.open").get(0);
            lift = "open";
            liftsOpen++;
        }
        return lift;
    }

    public String setOpenClosedRuns(int rowNum) {
        Element data = grouseWeather.select("ul.data-table").get(3);
        Element status = data.select("li").get(rowNum);
        String run;
        try {
            Element dcm2 = status.select("span.closed").get(0);
            run = "closed";
        } catch (IndexOutOfBoundsException e) {
            Element dcm4 = status.select("span.open").get(0);
            runsOpen++;
            run = "open";
        }
        return run;
    }



    public String setOpenClosedParks(int rowNum) {
        Element data = grouseWeather.select("ul.data-table").get(4);
        Element status = data.select("li").get(rowNum);
        String terrainPark;
        try {
            Element dcm2 = status.select("span.closed").get(0);
            terrainPark = "closed";
        } catch (IndexOutOfBoundsException e) {
            Element dcm4 = status.select("span.open").get(0);
            terrainPark = "open";
            terrainParksOpen++;
        }
        return terrainPark;
    }

    public String setOpenClosedSnowshoe(int rowNum) {
        Element data = grouseWeather.select("ul.data-table").get(5);
        Element status = data.select("li").get(rowNum);
        String snowshoeTrail;
        try {
            Element dcm2 = status.select("span.closed").get(0);
            snowshoeTrail = "closed";
        } catch (IndexOutOfBoundsException e) {
            Element dcm4 = status.select("span.open").get(0);
            snowshoeTrail = "open";
            snowshoeTrailsOpen++;
        }
        return snowshoeTrail;
    }


    public void openError() {
        Intent i = new Intent(context, Error.class);
        i.putExtra("ErrorFrom", "Grouse");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}




