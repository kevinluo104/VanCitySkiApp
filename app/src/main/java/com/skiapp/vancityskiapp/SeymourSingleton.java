package com.skiapp.vancityskiapp;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class SeymourSingleton extends AppCompatActivity {
    private static SeymourSingleton instance = null;
    private static ResultListener listener;
    private static Context context;
    private Document seymourWeather;
    private int counter = 0;
    public String temperature = "";
    public String conditions = "";
    public String visibility = "";
    public String snowConditions = "";
    public int runsOpen = 0;
    public int liftsOpen = 0;
    public int terrainParksOpen = 0;
    public int tubeParksOpen = 0;
    public String snowshoeTrailsStatus = "";
    public String fortyEightHrSnow = "48hrSnow";
    public String twentyFourHrSnow = "24HrSnow";
    public String sevenDaySnow = "7DaySnow";
    public String seasonSnow = "SeasonSnow";
    public String brocktonChair = "";
    public String lodgeChair = "";
    public String mysteryPeakExpress = "";
    public String goldieMagicCarpet = "";
    public String enquistSnowTubePark = "";
    public String tobagganArea = "";
    public String discoverySnowshoeTrails = "";
    public String northlands = "";
    public String theRockstarEnergyPit = "";
    public String rookies = "";
    public String nuthouse = "";
    public String mushroom = "";
    public int brocktonChairRunsOpen = 0;
    public int lodgeChairRunsOpen = 0;
    public int lodgeChairTerrainParksOpen = 0;
    public int mysteryPeakExpressRunsOpen = 0;
    public int mysteryPeakExpressTerrainParksOpen = 0;
    public int goldieMagicCarpetRunsOpen = 0;
    public String brocktonGully = "Closed";
    public String backdoor = "Closed";
    public String exit22 = "Closed";
    public String hangTen = "Closed";
    public String maverick = "Closed";
    public String sammyJ = "Closed";
    public String sammysExpress = "Closed";
    public String cliffHouse = "Closed";
    public String scooter = "Closed";
    public String sternsStairway = "Closed";
    public String sunshineRidge = "Closed";

    public String lodgeConnector = "Closed";
    public String mushroomRun = "Closed";
    public String rookiesRun = "Closed";
    public String cabinTrail = "Closed";
    public String chucksPlace = "Closed";
    public String lowerUnicorn = "Closed";
    public String mistletoe = "Closed";
    public String seymour16s = "Closed";
    public String trapperJohns = "Closed";

    public String manning = "Closed";
    public String boomerang = "Closed";
    public String crowfoot = "Closed";
    public String earls = "Closed";
    public String elevatorShaft = "Closed";
    public String friendlyNuthouse = "Closed";
    public String gunBarrel = "Closed";
    public String looperExpress = "Closed";
    public String mysteryLake = "Closed";
    public String northlandsRun = "Closed";
    public String petes = "Closed";
    public String slingshot = "Closed";
    public String towerline = "Closed";
    public String velvetGully = "Closed";
    public String wonger = "Closed";
    public String devilsDrop = "Closed";
    public String noelsFlight = "Closed";
    public String nutcracker = "Closed";
    public String unicorn = "Closed";

    public String flowerBasin = "Closed";

    private SeymourSingleton() {
        startThread();
    }

    public static SeymourSingleton getInstance(ResultListener listener, Context context) {
        SeymourSingleton.listener = listener;
        SeymourSingleton.context = context;
        if (instance == null) {
            instance = new SeymourSingleton();
        }
        return instance;
    }

    public void startThread() {
        final long startTime = System.currentTimeMillis();
        new Thread() {
            public void run() {
                try {
                    seymourWeather = Jsoup.connect("https://mtseymour.ca/today").get();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            temperature = seymourWeather.select("span.currentConditionsTemperature").first().text();
                            conditions = seymourWeather.select("td").get(1).ownText();
                            visibility = seymourWeather.select("td").get(3).ownText();
                            snowConditions = "Conditions: " + seymourWeather.select("td").get(6).ownText();
                            runsOpen = Integer.parseInt(seymourWeather.select("td").get(4).ownText());
                            fortyEightHrSnow = seymourWeather.select("td").get(8).ownText();
                            twentyFourHrSnow = seymourWeather.select("td").get(7).ownText();
                            sevenDaySnow = seymourWeather.select("td").get(9).ownText();
                            seasonSnow = seymourWeather.select("td").get(13).ownText();
                            if (seymourWeather.select("td.rtecenter").get(21).ownText().equals("Open")) {
                                discoverySnowshoeTrails = "open";
                                snowshoeTrailsStatus = "Open";
                            } else {
                                discoverySnowshoeTrails = "closed";
                                snowshoeTrailsStatus = "Closed";
                            }
                            if (counter == 0) {
                                brocktonChair = setChairliftStatus(brocktonChair, 12);
                                lodgeChair = setChairliftStatus(lodgeChair, 10);
                                mysteryPeakExpress = setChairliftStatus(mysteryPeakExpress, 11);
                                goldieMagicCarpet = setChairliftStatus(goldieMagicCarpet, 13);

                                enquistSnowTubePark = setTubeParkStatus(enquistSnowTubePark, 19);
                                tobagganArea = setTubeParkStatus(tobagganArea, 20);

                                northlands = setTerrainParkMysteryPeakExpressStatus(northlands, 14);
                                nuthouse = setTerrainParkMysteryPeakExpressStatus(nuthouse, 17);
                                theRockstarEnergyPit = setTerrainParkMysteryPeakExpressStatus(theRockstarEnergyPit, 15);

                                mushroom = setTerrainParkLodgeChairStatus(mushroom, 18);
                                rookies = setTerrainParkLodgeChairStatus(rookies, 16);
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

    public String setChairliftStatus(String lift, int rowNum) {
        String first = seymourWeather.select("td.rtecenter").get(rowNum).ownText();
        switch (first) {
            case "Open":
                lift = "open";
                liftsOpen++;
                return lift;
            case "Closed":
                lift = "closed";
                return lift;
            case "Standby":               // USED BY SEYMOUR??
                lift = "standby";
                return lift;
        }
        return lift;
    }

    public String setTubeParkStatus(String tubePark, int rowNum) {
        String first = seymourWeather.select("td.rtecenter").get(rowNum).ownText();
        switch (first) {
            case "Open":
                tubePark = "open";
                tubeParksOpen++;
                return tubePark;
            case "Closed":
                tubePark = "closed";
                return tubePark;
            case "Standby":               // USED BY SEYMOUR??
                tubePark = "standby";
                return tubePark;
        }
        return tubePark;
    }

    public String setTerrainParkLodgeChairStatus(String terrainPark, int rowNum) {
        String first = seymourWeather.select("td.rtecenter").get(rowNum).ownText();
        switch (first) {
            case "Open":
                terrainPark = "open";
                terrainParksOpen++;
                lodgeChairTerrainParksOpen++;
                return terrainPark;
            case "Closed":
                terrainPark = "closed";
                return terrainPark;
            case "Standby":               // USED BY SEYMOUR??
                terrainPark = "standby";
                return terrainPark;
        }
        return terrainPark;
    }

    public String setTerrainParkMysteryPeakExpressStatus(String terrainPark, int rowNum) {
        String first = seymourWeather.select("td.rtecenter").get(rowNum).ownText();
        switch (first) {
            case "Open":
                terrainPark = "open";
                terrainParksOpen++;
                mysteryPeakExpressTerrainParksOpen++;
                return terrainPark;
            case "Closed":
                terrainPark = "closed";
                return terrainPark;
            case "Standby":               // USED BY SEYMOUR??
                terrainPark = "standby";
                return terrainPark;
        }
        return terrainPark;
    }

    public void openError() {
        Intent i = new Intent(context, Error.class);
        i.putExtra("ErrorFrom", "Seymour");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
