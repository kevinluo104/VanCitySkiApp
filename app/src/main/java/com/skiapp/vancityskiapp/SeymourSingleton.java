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
                            int index = temperature.indexOf(".");
                            if (index > 0) {
                                temperature = temperature.substring(0, temperature.lastIndexOf(".")) + "°C";
                                System.out.println("seymou temp:" + temperature);
                            }

                            conditions = seymourWeather.select("td").get(1).ownText();
                            System.out.println("ARR: " + conditions);
                            String[] arr = conditions.split(" ");
                            StringBuffer sb = new StringBuffer();


                            for (int i = 0; i < arr.length; i++) {
                                sb.append(Character.toUpperCase(arr[i].charAt(0)))
                                        .append(arr[i].substring(1)).append(" ");
                            }
                            conditions = sb.toString().trim();
                            visibility = seymourWeather.select("td").get(3).ownText();
                            System.out.println("VISISIBIR" + visibility);

                            snowConditions = seymourWeather.select("td").get(6).ownText();
                            System.out.println("seymour snowcodnitons: " + snowConditions);
                            String[] arr2 = snowConditions.split(" ");
                            for (String i: arr2) {
                                System.out.println("ARR2: " + i);
                            }

                            StringBuffer sb2 = new StringBuffer();

                            for (int i = 0; i < arr2.length; i++) {
                                sb2.append(Character.toUpperCase(arr2[i].charAt(0)))
                                        .append(arr2[i].substring(1)).append(" ");
                            }
                            snowConditions = "Conditions: " + sb2.toString().trim();
                            // snowConditions = "Conditions: N/A";

                            runsOpen = Integer.parseInt(seymourWeather.select("td").get(4).ownText());
                            System.out.println("RUNS OPEN" + runsOpen);
                            if (hour > 21 || hour < 8)
                                runsOpen = 0;
                            fortyEightHrSnow = seymourWeather.select("td").get(8).ownText();
                            twentyFourHrSnow = seymourWeather.select("td").get(7).ownText();
                            sevenDaySnow = seymourWeather.select("td").get(9).ownText();
                            seasonSnow = seymourWeather.select("td").get(13).ownText();
                            discoverySnowshoeTrails = setSnowshoeTrailStatus();
                            snowshoeTrailsStatus = setSnowshoeTrailStatus();
                           /* if (seymourWeather.select("td.rtecenter").get(34).ownText().equals("Open")) {
                                discoverySnowshoeTrails = "open";
                                snowshoeTrailsStatus = "Open";
                            } else {
                                discoverySnowshoeTrails = "closed";
                                snowshoeTrailsStatus = "Closed";
                            }*/
                            if (counter == 0) {
                                brocktonChair = setChairliftStatus(16);
                                if (brocktonChair.equals("closed"))
                                    brocktonChairRunsOpen = "0";
                                lodgeChair = setChairliftStatus(14);
                                if (lodgeChair.equals("closed"))
                                    lodgeChairRunsOpen = "0";
                                mysteryPeakExpress = setChairliftStatus(15);
                                if (mysteryPeakExpress.equals("closed"))
                                    mysteryPeakExpressRunsOpen = "0";
                                goldieMagicCarpet = setChairliftStatus(17);
                                if (goldieMagicCarpet.equals("closed"))
                                    goldieMagicCarpetRunsOpen = "0";

                                enquistSnowTubePark = setTubeParkStatus(23);
                                tobagganArea = setTubeParkStatus(24);

                                northlands = setTerrainParkMysteryPeakExpressStatus(northlands, 18);
                                nuthouse = setTerrainParkMysteryPeakExpressStatus(nuthouse, 21);
                                System.out.println("nuthouse: " + nuthouse);
                                theRockstarEnergyPit = setTerrainParkMysteryPeakExpressStatus(theRockstarEnergyPit, 19);

                                mushroom = setTerrainParkLodgeChairStatus(22);
                                rookies = setTerrainParkLodgeChairStatus(20);
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
        System.out.println(rowNum + "" + first);

        // String ss = seymourWeather.select("td.rtecenter").get(22).ownText();
        // System.out.println("this is ss " + ss);
        String lift = "closed";
        switch (first) {
            case "8:30 AM - 9:00 PM":
                if (hour >= 8 && hour < 21) {
                    lift = "open";
                    liftsOpen++;
                }
                break;
            case "8:30 AM - 9:30 PM":
                if (hour >= 8 && hour < 22) {
                    lift = "open";
                    liftsOpen++;
                }
                break;
            case "9:30 AM - 4:00 PM":
                if (hour >= 9 && hour < 16) {
                    lift = "open";
                    liftsOpen++;
                }
                break;
            case "9:30 AM - 9:00 PM":
                if (hour >= 9 && hour < 21) {
                    lift = "open";
                    liftsOpen++;
                }
                break;
            case "9:30 AM - 9:30 PM":
                if (hour >= 9 && hour < 22) {
                    lift = "open";
                    liftsOpen++;
                }
                break;
            case "10:00 AM - 9:30 PM":
                if (hour >= 10 && hour < 22) {
                    lift = "open";
                    liftsOpen++;
                }
                break;
            case "10:30 AM - 4:00 PM":
                if (hour >= 10 && hour < 16) {
                    lift = "open";
                    liftsOpen++;
                }
                break;
            case "11:30 AM - 9:30 PM":
                if (hour >= 11 && hour < 22) {
                    lift = "open";
                    liftsOpen++;
                }
                break;
            case "12:30 PM - 4:00 PM":
                if (hour >= 12 && hour < 16) {
                    lift = "open";
                    liftsOpen++;
                }
                break;
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

    public String setSnowshoeTrailStatus() {
        String first = seymourWeather.select("td.rtecenter").get(14).ownText();
        String snowShoe = "closed";
        switch(first) {
            case "9:30 AM - 4:00 PM":
                if (hour > 9 && hour < 16) {
                    snowShoe = "open";
                    snowshoeTrailsStatus = "open";
                    return snowShoe;
                }
            case "8:30 AM - 4:00 PM":
                if (hour > 8 && hour < 16) {
                    snowShoe = "open";
                    snowshoeTrailsStatus = "open";
                    return snowShoe;
                }
            case "8:30 AM - 4:30 PM":
                if (hour > 8 && hour < 17) {
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
            sunshineRidge = "?";
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
