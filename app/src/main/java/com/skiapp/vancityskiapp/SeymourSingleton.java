package com.skiapp.vancityskiapp;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.time.DayOfWeek;
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
    //public String trapperJohns = "Closed";

    public String lowerTrapperJohn = "Closed";

    public String upperTrapperJohn = "Closed";

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

    public String goldieMeadows = "Closed";



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
                    seymourWeather = Jsoup.connect("https://mtseymour.ca/the-mountain/todays-conditions-hours").get();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            temperature = seymourWeather.select("#block-conditions > div > div > ul > li:nth-child(2) > div.gradient.border-radius-full.t-c-green.f-size-30.lh-1.value > div > div").first().text();
                          //  int index = temperature.indexOf(".");
                          //  if (index > 0) {
                         //       temperature = temperature.substring(0, temperature.lastIndexOf(".")) + "°C";
                               // System.out.println("seymou temp:" + temperature);
                          //  }

                            conditions = seymourWeather.select("#block-conditions > div > div > ul > li:nth-child(1) > div.t-micetype.lh-3.t-t-uppercase.f-w-medium.label").first().text();
                            System.out.println("ARR3333: " + conditions);
                            String[] arr = conditions.split(" ");
                            StringBuffer sb = new StringBuffer();


                            for (int i = 0; i < arr.length; i++) {
                                sb.append(Character.toUpperCase(arr[i].charAt(0)))
                                        .append(arr[i].substring(1)).append(" ");
                            }
                            conditions = sb.toString().trim();
                            visibility = seymourWeather.select("td").get(0).ownText();
                            System.out.println("VISISIBIR" + visibility);

                            snowConditions = seymourWeather.select("td").get(7).ownText();
                            System.out.println("seymour snowcodnitons: " + snowConditions);
                            String[] arr2 = snowConditions.split(" ");
                            for (String i: arr2) {
                                System.out.println("ARR2: " + i);
                            }

//                            StringBuffer sb2 = new StringBuffer();
//
//                            for (int i = 0; i < arr2.length; i++) {
//                                sb2.append(Character.toUpperCase(arr2[i].charAt(0)))
//                                        .append(arr2[i].substring(1)).append(" ");
//                            }
                     //       snowConditions = "Conditions: " + sb2.toString().trim();
                             snowConditions = "Conditions: N/A";

                            runsOpen = Integer.parseInt(seymourWeather.select("#block-conditions > div > div > ul > li:nth-child(3) > div.gradient.border-radius-full.t-c-green.f-size-30.lh-1.value > div > div").first().text());
                            System.out.println("RUNS OPEN" + runsOpen);
                            if (hour > 21 || hour < 8)
                                runsOpen = 0;
                            twentyFourHrSnow = seymourWeather.select("#block-conditions > div > div > div:nth-child(4) > div > ul > li:nth-child(1) > div.gradient.border-radius-full.t-c-green.f-size-30.lh-1.value > div > div.f-size-30.f-w-xbold.value").get(0).ownText() + "cm";
                            fortyEightHrSnow = seymourWeather.select("#block-conditions > div > div > div:nth-child(4) > div > ul > li:nth-child(2) > div.gradient.border-radius-full.t-c-green.f-size-30.lh-1.value > div > div.f-size-30.f-w-xbold.value").get(0).ownText() + "cm";
                            sevenDaySnow = seymourWeather.select("#block-conditions > div > div > div:nth-child(4) > div > ul > li:nth-child(3) > div.gradient.border-radius-full.t-c-green.f-size-30.lh-1.value > div > div.f-size-30.f-w-xbold.value").get(0).ownText() + "cm";
                            seasonSnow = seymourWeather.select("#block-conditions > div > div > div:nth-child(4) > div > ul > li:nth-child(6) > div.gradient.border-radius-full.t-c-green.f-size-30.lh-1.value > div > div.f-size-30.f-w-xbold.value").get(0).ownText() + "cm";
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
                                brocktonChair = setChairliftStatus("Brockton");
                                if (brocktonChair.equals("closed"))
                                    brocktonChairRunsOpen = "0";
                                int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                                if (day == Calendar.MONDAY || day == Calendar.TUESDAY) {
                                    brocktonChair = "closed";
                                    brocktonChairRunsOpen = "0";
                                }

                                lodgeChair = setChairliftStatus("Lodge");
                                if (lodgeChair.equals("closed"))
                                    lodgeChairRunsOpen = "0";
                                mysteryPeakExpress = setChairliftStatus("Mystery");
                                if (mysteryPeakExpress.equals("closed"))
                                    mysteryPeakExpressRunsOpen = "0";
                                goldieMagicCarpet = setChairliftStatus("Goldie");
                                if (goldieMagicCarpet.equals("closed"))
                                    goldieMagicCarpetRunsOpen = "0";

                                enquistSnowTubePark = setTubeParkStatus("Enquist");
                                tobagganArea = setTubeParkStatus("Toboggan");



                                northlands = setTerrainParkStatus("Northlands");
                                nuthouse = setTerrainParkStatus("Nuthouse");
                                //System.out.println("nuthouse: " + nuthouse);
                                theRockstarEnergyPit = setTerrainParkStatus("Pit");

                                mushroom = setTerrainParkStatus("Mushroom");
                                rookies = setTerrainParkStatus("Rookies");
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

    public String setChairliftStatus(String liftName) {
       // System.out.println("HERE" + rowNum);
        String status = null;
        switch (liftName) {
            case "Lodge":
                status = seymourWeather.select(".field--name-field-lodge-status").get(0).ownText();
                //System.out.println("HI" + status);
                break;
            case "Mystery":
                status = seymourWeather.select(".field--name-field-mystery-status").get(0).ownText();
                break;
            case "Brockton":
                status = seymourWeather.select(".field--name-field-brockton-status").get(0).ownText();
                break;
            case "Goldie":
                status = seymourWeather.select(".field--name-field-goldie-status").get(0).ownText();

        }
        String first = seymourWeather.select(".field--name-field-lodge-status").get(0).ownText();
      //  System.out.println(rowNum + "" + first);

        // String ss = seymourWeather.select("td.rtecenter").get(22).ownText();
        // System.out.println("this is ss " + ss);
        String lift = "closed";
        switch (status) {
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

    public String setTubeParkStatus(String parkName) {
        String status = null;
        switch (parkName) {
            case "Enquist":
                status = seymourWeather.select(".field--name-field-tube-status").get(0).ownText();
                //System.out.println("HI" + status);
                break;
            case "Toboggan":
                status = seymourWeather.select(".field--name-field-toboggan-status").get(0).ownText();
                break;


        }
       // String first = seymourWeather.select("td.rtecenter").get(rowNum).ownText();
       // String tubePark = "closed";
//        switch (status) {
//            case "10:00 AM - 4:00 PM":
//                if (hour >= 10 && hour < 16) {
//                    tubePark = "open";
//                    tubeParksOpen++;
//                    return tubePark;
//                }

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
      //  }
        return status;
    }

    public String setSnowshoeTrailStatus() {
        String first = seymourWeather.select("#block-conditions > div > div > ul > li:nth-child(2) > div.gradient.border-radius-full.t-c-green.f-size-30.lh-1.value > div > div").first().text();
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

//    public String setTerrainParkMysteryPeakExpressStatus(String terrainPark, int rowNum) {
//        String first = seymourWeather.select("td.rtecenter").get(rowNum).ownText();
//        switch (first) {
//            case "Open":
//                terrainPark = "open";
//                terrainParksOpen++;
//                mysteryPeakExpressTerrainParksOpen++;
//                return terrainPark;
//            case "10:30 AM - 9:00 PM":
//                if (hour > 10 && hour < 21) {
//                    terrainPark = "open";
//                    terrainParksOpen++;
//                    mysteryPeakExpressTerrainParksOpen++;
//                    return terrainPark;
//                }
//            case "Closed":
//                terrainPark = "closed";
//                return terrainPark;
//            case "Standby":               // USED BY SEYMOUR??
//                terrainPark = "standby";
//                return terrainPark;
//        }
//        return terrainPark;
//    }

    public String setTerrainParkStatus(String terrainPark) {
       // String first = seymourWeather.select("td.rtecenter").get(rowNum).ownText();
        String status = null;
        switch (terrainPark) {
            case "Northlands":
                status = seymourWeather.select(".field--name-field-northlands-status").get(0).ownText();
                //System.out.println("HI" + status);
                break;
            case "Pit":
                status = seymourWeather.select(".field--name-field-pit-status").get(0).ownText();
                break;
            case "Rookies":
                status = seymourWeather.select(".field--name-field-rookies-status").get(0).ownText();
                break;
            case "Mushroom":
                status = seymourWeather.select(".field--name-field-mushroom-status").get(0).ownText();

        }
//        switch (first) {
//            case "Open":
//                terrainPark = "open";
//                terrainParksOpen++;
//                mysteryPeakExpressTerrainParksOpen++;
//                return terrainPark;
//            case "10:30 AM - 9:00 PM":
//                if (hour > 10 && hour < 21) {
//                    terrainPark = "open";
//                    terrainParksOpen++;
//                    mysteryPeakExpressTerrainParksOpen++;
//                    return terrainPark;
//                }
//            case "Closed":
//                terrainPark = "closed";
//                return terrainPark;
//            case "Standby":               // USED BY SEYMOUR??
//                terrainPark = "standby";
//                return terrainPark;
//        }
        return status;
    }

    public String setTerrainParkMagicCarpetStatus(int rowNum) {
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
            lowerTrapperJohn = "?";
            upperTrapperJohn = "?";
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
            goldieMeadows = "?";
        }
    }
}
