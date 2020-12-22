package com.skiapp.vancityskiapp;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.Calendar;

public class SeymourSingleton extends AppCompatActivity {
    private static SeymourSingleton instance = null;
    private static ResultListener listener;
    private Calendar cal = Calendar.getInstance();
    private int hour = cal.get(Calendar.HOUR_OF_DAY);
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
    public String brocktonChairRunsOpen = "?";
    public String lodgeChairRunsOpen = "?";
    public int lodgeChairTerrainParksOpen = 0;
    public String mysteryPeakExpressRunsOpen = "?";
    public int mysteryPeakExpressTerrainParksOpen = 0;
    public String goldieMagicCarpetRunsOpen = "?";
    public int goldieMagicCarpetTerrainParksOpen = 0;
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
                            discoverySnowshoeTrails = setSnowshoeTrailStatus(34);
                            snowshoeTrailsStatus = setSnowshoeTrailStatus(34);
                           /* if (seymourWeather.select("td.rtecenter").get(34).ownText().equals("Open")) {
                                discoverySnowshoeTrails = "open";
                                snowshoeTrailsStatus = "Open";
                            } else {
                                discoverySnowshoeTrails = "closed";
                                snowshoeTrailsStatus = "Closed";
                            }*/
                            if (counter == 0) {
                                brocktonChair = setChairliftStatus(25);
                                if (brocktonChair.equals("closed"))
                                    brocktonChairRunsOpen = "0";
                                lodgeChair = setChairliftStatus(23);
                                if (lodgeChair.equals("closed"))
                                    lodgeChairRunsOpen = "0";
                                mysteryPeakExpress = setChairliftStatus(24);
                                if (mysteryPeakExpress.equals("closed"))
                                    mysteryPeakExpressRunsOpen = "0";
                                goldieMagicCarpet = setChairliftStatus(26);
                                if (goldieMagicCarpet.equals("closed"))
                                    goldieMagicCarpetRunsOpen = "0";

                                enquistSnowTubePark = setTubeParkStatus(32);
                                tobagganArea = setTubeParkStatus(33);

                                northlands = setTerrainParkMysteryPeakExpressStatus(northlands, 27);
                                nuthouse = setTerrainParkMysteryPeakExpressStatus(nuthouse, 30);
                                System.out.println("nuthouse: " + nuthouse);
                                theRockstarEnergyPit = setTerrainParkMysteryPeakExpressStatus(theRockstarEnergyPit, 28);

                                mushroom = setTerrainParkLodgeChairStatus(31);
                                rookies = setTerrainParkLodgeChairStatus(29);
                            }
                            setBrocktonChairRuns();
                            setLodgeChairRuns();
                            setMysteryPeakExpressRuns();
                            setGoldieMagicCarpetRuns();
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

    public String setChairliftStatus(int rowNum) {
        String first = seymourWeather.select("td.rtecenter").get(rowNum).ownText();
        // String ss = seymourWeather.select("td.rtecenter").get(22).ownText();
        // System.out.println("this is ss " + ss);
        String lift = "closed";
        switch (first) {
            case "8:30 AM - 9:30 PM":
                if (hour >= 8 && hour < 22) {
                    lift = "open";
                    liftsOpen++;
                }
                return lift;
            case "9:30 AM - 4:00 PM":
                if (hour >= 9 && hour < 16) {
                    lift = "open";
                    liftsOpen++;
                }
                return lift;
            case "8:30 AM - 9:00 PM":
                if (hour >= 8 && hour < 21) {
                    lift = "open";
                    liftsOpen++;
                }
                return lift;
        }
        return lift;
    }
        /*switch (first) {
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
    }*/

    public String setTubeParkStatus(int rowNum) {
        String first = seymourWeather.select("td.rtecenter").get(rowNum).ownText();
        String tubePark = "closed";
        switch (first) {
            case "10:00 AM - 4:00 PM":
                if (hour >= 10 && hour < 16) {
                    tubePark = "open";
                    tubeParksOpen++;
                    return tubePark;
                }

       /* return lift;
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
        return tubePark;*/
        }
        return tubePark;
    }

    public String setSnowshoeTrailStatus(int rowNum) {
        String first = seymourWeather.select("td.rtecenter").get(34).ownText();
        String snowShoe = "closed";
        switch(first) {
            case "8:30AM - 4:00 PM":
                if (hour > 8 && hour < 16) {
                    snowShoe = "open";
                    snowshoeTrailsStatus = "open";
                    return snowShoe;
                }
        }
        return snowShoe;
    }

    public String setTerrainParkLodgeChairStatus(int rowNum) {
        String first = seymourWeather.select("td.rtecenter").get(rowNum).ownText();
        String terrainPark = "closed";
        switch (first) {
            case "10:30 AM - 9:00 PM":
                if (hour > 10 && hour < 21) {
                    terrainPark = "open";
                    lodgeChairTerrainParksOpen++;
                    goldieMagicCarpetTerrainParksOpen++;
                    terrainParksOpen++;
                    return terrainPark;
                }
        }
        return terrainPark;
     /*   switch (first) {
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
        return terrainPark;*/
    }

    public String setTerrainParkMysteryPeakExpressStatus(String terrainPark, int rowNum) {
        String first = seymourWeather.select("td.rtecenter").get(rowNum).ownText();
        switch (first) {
            case "Open":
                terrainPark = "open";
                terrainParksOpen++;
                mysteryPeakExpressTerrainParksOpen++;
                return terrainPark;
            case "10:30 AM - 9:00 PM":
                if (hour > 10 && hour < 21) {
                    terrainPark = "open";
                    terrainParksOpen++;
                    mysteryPeakExpressTerrainParksOpen++;
                    return terrainPark;
                }
            case "Closed":
                terrainPark = "closed";
                return terrainPark;
            case "Standby":               // USED BY SEYMOUR??
                terrainPark = "standby";
                return terrainPark;
        }
        return terrainPark;
    }

    public String setTerrainParkMagicCarpeStatus(int rowNum) {
        String first = seymourWeather.select("td.rtecenter").get(rowNum).ownText();
        String terrainPark = "closed";
        switch (first) {
            case "10:30 AM - 9:00 PM":
                if (hour > 10 && hour < 21) {
                    terrainPark = "open";
                    goldieMagicCarpetTerrainParksOpen++;
                    return terrainPark;
                }
        }
        return terrainPark;
     /*   switch (first) {
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
        return terrainPark;*/
    }

    public void openError() {
        Intent i = new Intent(context, Error.class);
        i.putExtra("ErrorFrom", "Seymour");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
    public void setBrocktonChairRuns() {
        if (!brocktonChair.equals("closed")) {
            brocktonGully = "?";
            backdoor = "?";
            exit22 ="?";
            hangTen = "?";
            maverick = "?";
            sammyJ = "?";
            sammysExpress = "?";
            cliffHouse = "?";
            scooter = "?";
            sternsStairway = "?";
        }
    }

    public void setLodgeChairRuns() {
        if (!lodgeChair.equals("closed")) {
            lodgeConnector = "?";
            mushroomRun = "?";
            rookiesRun = "?";
            cabinTrail = "?";
            chucksPlace = "?";
            lowerUnicorn = "?";
            mistletoe = "?";
            seymour16s = "?";
            trapperJohns = "?";
        }
    }

    public void setMysteryPeakExpressRuns() {
        if (!mysteryPeakExpress.equals("closed")) {
            manning = "?";
            boomerang = "?";
            crowfoot = "?";
            earls = "?";
            elevatorShaft = "?";
            friendlyNuthouse = "?";
            gunBarrel = "?";
            looperExpress = "?";
            mysteryLake = "?";
            northlandsRun = "?";
            petes = "?";
            slingshot = "?";
            towerline = "?";
            velvetGully = "?";
            wonger = "?";
            devilsDrop = "?";
            noelsFlight = "?";
            nutcracker = "?";
            unicorn = "?";
        }
    }
    public void setGoldieMagicCarpetRuns() {
        if (!goldieMagicCarpet.equals("closed")) {
            flowerBasin = "?";
            mushroomRun = "?";
            rookiesRun = "?";
        }
    }
}
