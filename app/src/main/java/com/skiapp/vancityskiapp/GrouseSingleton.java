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
    public Document pastWeather;
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
                        grouseWeather = Jsoup.connect("https://www.grousemountain.com/current_conditions#weather").get();
                        pastWeather = Jsoup.connect("https://web.archive.org/web/20191203135732/https://www.grousemountain.com/current_conditions").get();
                        runOnUiThread( new Runnable()
                        {
                            public void run()
                            {
                                temperature = grouseWeather.select("h3.metric").first().text();
                                weather = grouseWeather.select("p").get(5).text();
                                visibility = grouseWeather.select("div.current_status p").first().text();
                                overnightSnow = setNewSnow(overnightSnow,1);;
                                twelveHrSnow = setNewSnow(twelveHrSnow, 0);
                                twentyFourHrSnow = setNewSnow(twentyFourHrSnow, 2);
                                fortyEightHrSnow = setNewSnow(fortyEightHrSnow, 3);
                                if (counter == 0) {
                                    greenwayChair = setOpenClosedLifts(greenwayChair, 3);
                                    olympicExpressChair = setOpenClosedLifts(olympicExpressChair, 0);
                                    screamingEagleChair = setOpenClosedLifts(screamingEagleChair, 2);
                                    peakChair = setOpenClosedLifts(peakChair, 1);
                                    magicCarpet = setOpenClosedLifts(magicCarpet, 4);
                                    chaletRoad = setOpenClosedRuns(chaletRoad, 0);
                                    paradise = setOpenClosedRuns(paradise, 1);
                                    skiWee = setOpenClosedRuns(skiWee, 2);
                                    theCut = setOpenClosedRuns(theCut, 3);
                                    blueFace = setOpenClosedRuns(blueFace, 4);
                                    centennial = setOpenClosedRuns(centennial, 5);
                                    deliverance = setOpenClosedRuns(deliverance, 6);
                                    dogleg = setOpenClosedRuns(dogleg, 7);
                                    expo = setOpenClosedRuns(expo, 8);
                                    heavensSake = setOpenClosedRuns(heavensSake, 9);
                                    lowerBuckhorn = setOpenClosedRuns(lowerBuckhorn, 10);
                                    lowerPeak = setOpenClosedRuns(lowerPeak, 11);
                                    lowerSideCut = setOpenClosedRuns(lowerSideCut, 12);
                                    paperTrail = setOpenClosedRuns(paperTrail, 13);
                                    peak = setOpenClosedRuns(peak, 14);
                                    sideCut = setOpenClosedRuns(sideCut, 15);
                                    skyline = setOpenClosedRuns(skyline, 16);
                                    tyeeChute = setOpenClosedRuns(tyeeChute, 17);
                                    upperBuckhorn = setOpenClosedRuns(upperBuckhorn, 18);
                                    blazes = setOpenClosedRuns(blazes, 19);
                                    coffin = setOpenClosedRuns(coffin, 20);
                                    hades = setOpenClosedRuns(hades, 21);
                                    inferno = setOpenClosedRuns(inferno, 22);
                                    outerLimits = setOpenClosedRuns(outerLimits, 23);
                                    devilsAdvocate = setOpenClosedRuns(devilsAdvocate, 24);
                                    purgatory = setOpenClosedRuns(purgatory, 25);
                                    peakGlades = setOpenClosedRuns(peakGlades, 26);
                                    grinderTracks = setOpenClosedRuns(grinderTracks, 27);
                                    expoGlades = setOpenClosedRuns(expoGlades, 28);
                                    coolasCorner = setOpenClosedRuns(coolasCorner, 29);
                                    chimney = setOpenClosedRuns(chimney, 30);
                                    upperBlazes = setOpenClosedRuns(upperBlazes, 31);
                                    mountainHighway = setOpenClosedRuns(mountainHighway, 32);
                                    cutJumpLine = setOpenClosedParks(cutRookiePark, 2);
                                    paradiseJibPark = setOpenClosedParks(paradiseJibPark, 1);
                                    cutRookiePark = setOpenClosedParks(cutRookiePark, 3);
                                    cutPark = setOpenClosedParks(cutPark, 0);
                                    grouseWoods = setOpenClosedParks(grouseWoods, 4);
                                    grousePark = setOpenClosedParks(grousePark, 5);
                                    blueGrouseLoop = setOpenClosedSnowshoe(blueGrouseLoop, 0);
                                    damMountainLoop = setOpenClosedSnowshoe(damMountainLoop, 1);
                                    snowshoeGrind = setOpenClosedSnowshoe(snowshoeGrind, 2);
                                    thunderbirdRidge = setOpenClosedSnowshoe(thunderbirdRidge, 3);
                                    picture = grouseWeather.select("p").get(5).text();
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

    public String setNewSnow(String text, int rowNum) {
        Element data = pastWeather.select("div.conditions-snow-report__content").get(0);
        Element status = data.select("li").get(rowNum);
        try {
            Element dcm2 = status.select("h3.metric").get(0);
            text = (dcm2.text());
        } catch (IndexOutOfBoundsException e) {
            Element dcm4 = status.select("h3.metric").get(0);
            text = (dcm4.text());
        }
        return text;
    }

    public String setOpenClosedLifts(String lift, int rowNum) {

        Element data = pastWeather.select("ul.data-table").get(2);
        Element status = data.select("li").get(rowNum);
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

    public String setOpenClosedRuns(String run, int rowNum) {

        Element data = pastWeather.select("ul.data-table").get(3);
        Element status = data.select("li").get(rowNum);
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



    public String setOpenClosedParks(String terrainPark, int rowNum) {
        Element data = pastWeather.select("ul.data-table").get(4);
        Element status = data.select("li").get(rowNum);
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

    public String setOpenClosedSnowshoe(String snowshoeTrail, int rowNum) {
        Element data = pastWeather.select("ul.data-table").get(5);
        Element status = data.select("li").get(rowNum);
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




