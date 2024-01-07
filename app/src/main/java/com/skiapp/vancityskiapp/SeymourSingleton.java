package com.skiapp.vancityskiapp;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.time.LocalTime;
import java.util.Objects;


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
    public String tobogganArea = "";
    // public int tobogganAreaOpenHour = 0;
    //  public int tobogganAreaCloseHour = 0;
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

                            conditions = seymourWeather.select("#block-conditions > div > div > ul > li:nth-child(1) > div.t-micetype.lh-3.t-t-uppercase.f-w-medium.label").first().text();
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

                            snowConditions = "Conditions: N/A";

                            runsOpen = Integer.parseInt(seymourWeather.select("#block-conditions > div > div > ul > li:nth-child(3) > div.gradient.border-radius-full.t-c-green.f-size-30.lh-1.value > div > div").first().text());
                            if (hour > 21 || hour < 8)
                                runsOpen = 0;
                            twentyFourHrSnow = seymourWeather.select("#block-conditions > div > div > div:nth-child(4) > div > ul > li:nth-child(1) > div.gradient.border-radius-full.t-c-green.f-size-30.lh-1.value > div > div.f-size-30.f-w-xbold.value").get(0).ownText() + "cm";
                            fortyEightHrSnow = seymourWeather.select("#block-conditions > div > div > div:nth-child(4) > div > ul > li:nth-child(2) > div.gradient.border-radius-full.t-c-green.f-size-30.lh-1.value > div > div.f-size-30.f-w-xbold.value").get(0).ownText() + "cm";
                            sevenDaySnow = seymourWeather.select("#block-conditions > div > div > div:nth-child(4) > div > ul > li:nth-child(3) > div.gradient.border-radius-full.t-c-green.f-size-30.lh-1.value > div > div.f-size-30.f-w-xbold.value").get(0).ownText() + "cm";
                            seasonSnow = seymourWeather.select("#block-conditions > div > div > div:nth-child(4) > div > ul > li:nth-child(6) > div.gradient.border-radius-full.t-c-green.f-size-30.lh-1.value > div > div.f-size-30.f-w-xbold.value").get(0).ownText() + "cm";
                            if (counter == 0) {
                                discoverySnowshoeTrails = setSnowshoeTrailStatus(discoverySnowshoeTrails);
                                lodgeConnector = setLodgeChairRunStatus(0);
                                System.out.println("LIODGEE" + lodgeConnector);
                                rookiesRun = setLodgeChairRunStatus(1);
                                cabinTrail = setLodgeChairRunStatus(2);
                                chucksPlace = setLodgeChairRunStatus(3);
                                lowerTrapperJohn = setLodgeChairRunStatus(4);
                                lowerUnicorn = setLodgeChairRunStatus(5);
                                mistletoe = setLodgeChairRunStatus(6);
                                seymour16s = setLodgeChairRunStatus(7);
                                upperTrapperJohn = setLodgeChairRunStatus(8);

                                boomerang = setMysteryPeakChairRunStatus(9);
                                manning = setMysteryPeakChairRunStatus(10);
                                crowfoot = setMysteryPeakChairRunStatus(11);
                                earls = setMysteryPeakChairRunStatus(12);
                                elevatorShaft = setMysteryPeakChairRunStatus(13);
                                friendlyNuthouse = setMysteryPeakChairRunStatus(14);
                                gunBarrel = setMysteryPeakChairRunStatus(15);
                                looperExpress = setMysteryPeakChairRunStatus(16);
                                mysteryLake = setMysteryPeakChairRunStatus(17);
                                northlandsRun = setMysteryPeakChairRunStatus(18);
                                petes = setMysteryPeakChairRunStatus(19);
                                slingshot = setMysteryPeakChairRunStatus(20);
                                towerline = setMysteryPeakChairRunStatus(21);
                                velvetGully = setMysteryPeakChairRunStatus(22);
                                wonger = setMysteryPeakChairRunStatus(23);
                                devilsDrop = setMysteryPeakChairRunStatus(24);
                                noelsFlight = setMysteryPeakChairRunStatus(25);
                                nutcracker = setMysteryPeakChairRunStatus(26);
                                scooter = setMysteryPeakChairRunStatus(27);
                                unicorn = setMysteryPeakChairRunStatus(28);

                                brocktonGully = setBrocktonChairRunStatus(29);
                                backdoor = setBrocktonChairRunStatus(30);
                                exit22 = setBrocktonChairRunStatus(31);
                                hangTen = setBrocktonChairRunStatus(32);
                                maverick = setBrocktonChairRunStatus(33);
                                sammyJ = setBrocktonChairRunStatus(34);
                                sammysExpress = setBrocktonChairRunStatus(35);
                                cliffHouse = setBrocktonChairRunStatus(36);
                                sternsStairway = setBrocktonChairRunStatus(37);
                                sunshineRidge = setBrocktonChairRunStatus(38);

                                flowerBasin = setGoldieCarpetRunStatus(39);
                                goldieMeadows = setGoldieCarpetRunStatus(40);
                                mushroomRun = setGoldieCarpetRunStatus(41);


                                brocktonChair = setChairliftStatus("Brockton");
                                if (brocktonChair.equals("closed"))
                                    brocktonChairRunsOpen = 0;
                                lodgeChair = setChairliftStatus("Lodge");

                                if (lodgeChair.equals("closed"))
                                    lodgeChairRunsOpen = 0;
                                mysteryPeakExpress = setChairliftStatus("Mystery");
                                if (mysteryPeakExpress.equals("closed"))
                                    mysteryPeakExpressRunsOpen = 0;
                                goldieMagicCarpet = setChairliftStatus("Goldie");
                                if (goldieMagicCarpet.equals("closed"))
                                    goldieMagicCarpetRunsOpen = 0;

                                setTubeParkStatus("Enquist");
                                setTubeParkStatus("Toboggan");


                                setTerrainParkStatus("Northlands");
                                setTerrainParkStatus("Nuthouse");
                                //System.out.println("nuthouse: " + nuthouse);
                                setTerrainParkStatus("Pit");

                                setTerrainParkStatus("Mushroom");
                                System.out.println("MUSHROOM 3: " + mushroom);
                                setTerrainParkStatus("Rookies");
                            }
//                            setBrocktonChairRuns();
//                            setLodgeChairRuns();
//                            setMysteryPeakExpressRuns();
//                            setGoldieMagicCarpetRuns();
                            counter++;
                            listener.onResultFetched();
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

    public String setChairliftStatus(String liftName) {
        // System.out.println("HERE" + rowNum);
        String status = null;
        switch (liftName) {
            case "Lodge":
                Element lodgeAccordionHeading = seymourWeather.select("tr.accordion-heading").get(0); // CLOSED
                try {
                    Element divElement = lodgeAccordionHeading.select("div.field--name-field-lodge-status").first();
                    status = divElement.text().trim();
                    return "closed";
                    //  status = divElement.text().trim(); // INCORRECTTTTT
                } catch (Exception e) {
                    Element divElement = lodgeAccordionHeading.select("td.no-wrap").first(); // GET HOUR
                    status = divElement.text().trim();
                    liftName = checkStatus(status);
                    if (Objects.equals(liftName, "open")) liftsOpen++;
                    return liftName;
                }
            case "Mystery":
                System.out.println("HEREM  MYSTERRY");
                Element mysteryAccordionHeading = seymourWeather.select("tr.accordion-heading").get(1);
                try {
                    Element divElement = mysteryAccordionHeading.select(".field--name-field-mystery-status").first();
                    status = divElement.text().trim();
                   // System.out.println("MYETSERY GOOD: " + divElement.text().trim());
                    return "closed";
                    // status = divElement.text().trim();
                } catch (Exception e) {
                    Element divElement = mysteryAccordionHeading.select("td.no-wrap").first();
                    status = divElement.text().trim();
                    liftName = checkStatus(status);
                    if (Objects.equals(liftName, "open")) liftsOpen++;
                    System.out.println("MYETSERY BAD");
                    return liftName;
                }
            case "Brockton":
                Element brocktonAccordionHeading = seymourWeather.select("tr.accordion-heading").get(2);
                try {
                    Element divElement = brocktonAccordionHeading.select(".field--name-field-brockton-status").first();
                    status = divElement.text().trim();
                    return "closed";
                    // status = divElement.text().trim();
                } catch (Exception e) {
                    Element divElement = brocktonAccordionHeading.select("td.no-wrap").first();
                    status = divElement.text().trim();
                    liftName = checkStatus(status);
                    if (Objects.equals(liftName, "open")) liftsOpen++;
                    return liftName;
                }
            case "Goldie":
                Element goldieAccordionHeading = seymourWeather.select("tr.accordion-heading").get(3);
                try {
                    Element divElement = goldieAccordionHeading.select(".field--name-field-goldie-status").first();
                    status = divElement.text().trim();
                    return "closed";
                    // status = divElement.text().trim();
                } catch (Exception e) {
                    Element divElement = goldieAccordionHeading.select("td.no-wrap").first();
                    status = divElement.text().trim();
                    liftName = checkStatus(status);
                    if (Objects.equals(liftName, "open")) liftsOpen++;
                    return liftName;
                }
        }
        return liftName;
    }

    public void setTubeParkStatus(String parkName) {
        String status = null;
        switch (parkName) {
            case "Enquist":
                Element enquistRow = seymourWeather.select("tr").get(13);
                try {
                    Element divElement = enquistRow.select(".field--name-field-tube-status").first();
                    status = divElement.text().trim();
                    enquistSnowTubePark = "closed";
                } catch (Exception e) {
                    Element divElement = enquistRow.select("td.no-wrap").first();
                    status = divElement.text().trim();
                    enquistSnowTubePark = checkStatus(status);
                    if (Objects.equals(enquistSnowTubePark, "open")) tubeParksOpen++;
                }
                break;
            case "Toboggan":
                Element tobogganRow = seymourWeather.select("tr").get(14);
                try {
                    Element divElement = tobogganRow.select(".field--name-field-toboggan-status").first();
                    status = divElement.text().trim();
                    tobogganArea = "closed";
                } catch (Exception e) {
                    Element divElement = tobogganRow.select("td.no-wrap").first();
                    status = divElement.text().trim();
                    tobogganArea = checkStatus(status);
                    if (Objects.equals(tobogganArea, "open")) tubeParksOpen++;
                }
                break;
        }
    }

    public String checkStatus(String status) {
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("h:mm a");
        }

        int start = 0;
        int end = 0;
        // Parse the start and end times
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalTime startTime = LocalTime.parse(status.split("-")[0].trim(), formatter);
            start = startTime.getHour();
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalTime endTime = LocalTime.parse(status.split("-")[1].trim(), formatter);
            end = endTime.getHour();
        }

        if (hour < start || hour > end) {
            return "closed";
        }
        return "open";
    }

    public String setSnowshoeTrailStatus(String trail) {
        String status = null;
        Element snowshoeAccordionHeading = seymourWeather.select("tr.accordion-heading").get(4);
        try {
            Element divElement = seymourWeather.select("div.field--name-field-snowshoe-status").get(0);
            String snowshoeStatus = divElement.text().trim();
            discoverySnowshoeTrails = "closed";
            return discoverySnowshoeTrails;
        } catch (Exception e) {
            Element divElement = snowshoeAccordionHeading.select("td.no-wrap").first();
            status = divElement.text().trim();
            trail = checkStatus(status);
            return trail;
        }
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
    }

    public String setLodgeChairRunStatus(int runRow) {
        Element statusDivs;
        if (hour < 17) { // Day
            statusDivs = seymourWeather.select("div.cell.d-flex.ai-center.jc-center.status").get(2 * runRow);
        } else { // Night
            statusDivs = seymourWeather.select("div.cell.d-flex.ai-center.jc-center.status").get(2 * runRow + 1);
        }
        try {
            Element openDivs = statusDivs.select("div.f-icon.icon.status.status-closed").get(0);
            String status = openDivs.text().trim();
            return "closed";
        } catch (Exception e) {
            lodgeChairRunsOpen++;
            return "open";
        }
    }


    public String setMysteryPeakChairRunStatus(int runRow) {
        Element statusDivs;
        if (hour < 17) { // Day
            statusDivs = seymourWeather.select("div.cell.d-flex.ai-center.jc-center.status").get(2 * runRow);
        } else { // Night
            statusDivs = seymourWeather.select("div.cell.d-flex.ai-center.jc-center.status").get(2 * runRow + 1);
        }
        try {
            Element openDivs = statusDivs.select("div.f-icon.icon.status.status-closed").get(0);
            String status = openDivs.text().trim();
            return "closed";
        } catch (Exception e) {
            mysteryPeakExpressRunsOpen++;
            return "open";
        }
    }


    public String setBrocktonChairRunStatus(int runRow) {
        Element statusDivs;
        if (hour < 17) { // Day
            statusDivs = seymourWeather.select("div.cell.d-flex.ai-center.jc-center.status").get(2 * runRow);
        } else { // Night
            statusDivs = seymourWeather.select("div.cell.d-flex.ai-center.jc-center.status").get(2 * runRow + 1);
        }
        try {
            Element openDivs = statusDivs.select("div.f-icon.icon.status.status-closed").get(0);
            String status = openDivs.text().trim();
            return "closed";
        } catch (Exception e) {
            brocktonChairRunsOpen++;
            return "open";
        }
    }

    public String setGoldieCarpetRunStatus(int runRow) {
        Element statusDivs;
        if (hour < 17) { // Day
            statusDivs = seymourWeather.select("div.cell.d-flex.ai-center.jc-center.status").get(2 * runRow);
        } else { // Night
            statusDivs = seymourWeather.select("div.cell.d-flex.ai-center.jc-center.status").get(2 * runRow + 1);
        }
        try {
            Element openDivs = statusDivs.select("div.f-icon.icon.status.status-closed").get(0);
            String status = openDivs.text().trim();
            return "closed";
        } catch (Exception e) {
            goldieMagicCarpetRunsOpen++;
            return "open";
        }
    }

    public void setTerrainParkStatus(String terrainPark) {
        // String first = seymourWeather.select("td.rtecenter").get(rowNum).ownText();
        String status = null;
        switch (terrainPark) {
            case "Northlands":
                Element northlandsRow = seymourWeather.select("tr").get(9);
                try { // CLOSED
                    Element divElement = northlandsRow.select(".field--name-field-northlands-status").first();
                    status = divElement.text().trim();
                    northlands = "closed";
                    return;
                } catch (Exception e) { // OPEN
                    Element divElement = northlandsRow.select("td.no-wrap").first();
                    status = divElement.text().trim();
                    northlands = checkStatus(status);
                    if (Objects.equals(northlands, "open")) {
                        mysteryPeakExpressTerrainParksOpen++;
                        terrainParksOpen++;
                    }
                }
                break;
            case "Pit":
                Element pitRow = seymourWeather.select("tr").get(10);
                try {
                    Element divElement = pitRow.select(".field--name-field-pit-status").first();
                    status = divElement.text().trim();
                    theRockstarEnergyPit = "closed";
                    return;
                } catch (Exception e) {
                    Element divElement = pitRow.select("td.no-wrap").first();
                    status = divElement.text().trim();
                    theRockstarEnergyPit = checkStatus(status);
                    if (Objects.equals(theRockstarEnergyPit, "open")) {
                        mysteryPeakExpressTerrainParksOpen++;
                        terrainParksOpen++;
                    }
                }
                break;
            case "Rookies":
                Element rookiesRow = seymourWeather.select("tr").get(11);
                try {
                    Element divElement = rookiesRow.select(".field--name-field-rookies-status").first();
                    status = divElement.text().trim();
                    rookies = "closed";
                    return;
                } catch (Exception e) {
                    Element divElement = rookiesRow.select("td.no-wrap").first();
                    status = divElement.text().trim();
                    rookies = checkStatus(status);
                    if (Objects.equals(rookies, "open")) {
                        mysteryPeakExpressTerrainParksOpen++;
                        lodgeChairTerrainParksOpen++;
                        terrainParksOpen++;
                    }
                }
                break;
            case "Mushroom":
                Element mushroomRow = seymourWeather.select("tr").get(12);
                try {
                    Element divElement = mushroomRow.select(".field--name-field-mushroom-status").first();
                    System.out.println("MUSH4!" + mushroom);
                    status = divElement.text().trim();
                    mushroom = "closed";
                    return;
                } catch (Exception e) {
                    System.out.println("MUSH2!" + mushroom);
                    Element divElement = mushroomRow.select("td.no-wrap").first();
                    status = divElement.text().trim();
                    mushroom = checkStatus(status);
                    if (Objects.equals(mushroom, "open")) {
                        lodgeChairTerrainParksOpen++;
                        goldieMagicCarpetTerrainParksOpen++;
                        terrainParksOpen++;
                    }
                    System.out.println("MUSH3!" + mushroom);
                    System.out.println("STAUASY" + status);
                }

        }
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
    }

    public void openError() {
        Intent i = new Intent(context, Error.class);
        i.putExtra("ErrorFrom", "Seymour");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}

