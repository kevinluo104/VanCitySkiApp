package com.skiapp.vancityskiapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Arrays;

import javax.net.ssl.SSLHandshakeException;

public class CypressSingleton extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    private static CypressSingleton instance = null;
    private static ResultListener listener;
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private Document cypressWeather;
    public String conditions = "";
    public String temperature = "";
    public String fortyEightHrSnow = "48hSnow";
    public String sevenDaySnow = "7DaySnow";
    public String twentyFourHrSnow = "24hSnow";
    public String seasonSnow = "SeasonSnow";
    public String snowConditions = "";
    public String eagleExpress = "";
    public String lionsExpress = "";
    public String ravenRidge = "";
    public String easyRider = "";
    public String skyChair = "";
    public String midwayChair = "";
    public int runsOpen = 0;
    public int liftsOpen = 0;
    public int runsOpenEagleExpress = 0;
    public int terrainParksOpenEagleExpress = 0;
    public int runsOpenLionsExpress = 0;
    public int terrainParksOpenLionsExpress = 0;
    public int runsOpenRavenRidge = 0;
    public int runsOpenEasyRider = 0;
    public int terrainParksOpenEasyRider = 0;
    public int runsOpenSkyChair = 0;
    public int runsOpenMidwayChair = 0;

    public int terrainParksOpen = 0;
    private int counter = 0;
    public String panorama = "";
    public String windjammer = "";
    public String upperFork = "";
    public String lowerFork = "";
    public String jaseyJay = "";
    public String mcIvors = "";
    public String unrun = "";
    public String trumpeter = "";
    public String lowerTrumpeter = "";
    public String hoseSide = "";
    public String bHip = "";
    public String blowBy = "";
    public String detentionGlades = "";
    public String skatePark = "";
    public String district = "";
    public String stompingGrounds = "";

    public String collins = "";
    public String horizon = "";
    public String humptyDumpty = "";
    public String catTrackUpper = "";
    public String primaryPower = "";
    public String horizonByPass = "";
    public String lowerBowen = "";
    public String hutch = "";
    public String catTrackLower = "";
    public String upperBowen = "";
    public String bowenFace = "";
    public String gibsons = "";
    public String upperRainbow = "";
    public String bowenWest = "";
    public String rainbow = "";
    public String slash = "";
    public String moons = "";
    public String LTDGlades = "";
    public String elevatorGlades = "";
    public String darksideGlades = "";
    public String cratorGlades = "";
    public String bowenWestGlades = "";
    public String underTheVolcanoGlades = "";
    public String gibsonGlades = "";
    public String sunrisePark = "";

    public String collinsPark = "";


    public String bennys = "";
    public String crazyRaven = "";
    public String lowerCoyote7 = "";
    public String threeBears = "";
    public String rideout = "";
    public String bilodeau = "";
    public String upperCoyote7 = "";
    public String firstSun = "";
    public String shoreGlades = "";
    public String shoreLine = "";
    public String backOnBlack = "";
    public String meteor = "";
    public String blackFly = "";


    public String runway = "";
    public String steezyRider = "";
    public String gnarlysDen = "";

    public String T33 = "";
    public String horseflyCanyon = "";
    public String glades = "";
    public String tomcat = "";
    public String ripcord = "";
    public String topGun = "";

    public String shuttle = "";
    public String blaster = "";
    public String hutRun = "";
    public String webbSite = "";


    private CypressSingleton() {
        startThread();
    }

    public static CypressSingleton getInstance(ResultListener listener, Context context) {
        CypressSingleton.listener = listener;
        CypressSingleton.context = context;
        if (instance == null) {
            instance = new CypressSingleton();
        }
        return instance;
    }

    public void startThread() {
        final long startTime = System.currentTimeMillis();
        new Thread() {
            public void run() {
                try {
                    cypressWeather = Jsoup.connect("https://cypressmountain.com/downhill-conditions-and-cams").get();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            try {
                                conditions = cypressWeather.select(("#conditions_current_e62ddfa83ed0c6d03d9e2b03ad704a26 > div > div > div > ul > li:nth-child(2) > div > ul > li:nth-child(1) > ul > li:nth-child(2) > span")).get(0).ownText();

                                String[] arr = conditions.split(" ");
                                StringBuffer sb = new StringBuffer();


                                for (int i = 0; i < arr.length; i++) {
                                    sb.append(Character.toUpperCase(arr[i].charAt(0)))
                                            .append(arr[i].substring(1)).append(" ");
                                }
                                conditions = sb.toString().trim();
                                System.out.println("arr22222: " + conditions);
                                if (conditions.equals("Rain/snow"))
                                    conditions = "Rain/Snow";
                            } catch (Exception ignored) {

                            }

                            try {
                                temperature = cypressWeather.select("span.js-measurement").get(0).ownText();
                                System.out.println("TEMPRAUTE" + temperature);
                                twentyFourHrSnow = cypressWeather.select("span.js-measurement").get(1).ownText() + "cm";
                                fortyEightHrSnow = cypressWeather.select("span.js-measurement").get(2).ownText() + "cm";
                                sevenDaySnow = cypressWeather.select("span.js-measurement").get(3).ownText()+ "cm";
                                seasonSnow = cypressWeather.select("span.js-measurement").get(4).ownText() + "cm";
                                System.out.println("SEAOSN" + seasonSnow);

                             //   Element firstCondition = cypressWeather.select("span.text.text_24.text_30Md.mix-text_bold.mix-text_spaceBreak.mix-text_color1.mix-text_alignCenter.mix-text_alignLeftMd.mix-text_lessStrict").first();
                            //    Element secondCondition = cypressWeather.select("span.text.text_24.text_30Md.mix-text_bold.mix-text_spaceBreak.mix-text_color1.mix-text_alignCenter.mix-text_alignLeftMd.mix-text_lessStrict").get(1);
                            //    snowConditions = firstCondition.ownText() + " " + secondCondition.ownText();

                                if (counter == 0) {

                                    eagleExpress = setChairliftStatus(eagleExpress, 0);
                                    lionsExpress = setChairliftStatus(lionsExpress, 1);
                                    ravenRidge = setChairliftStatus(ravenRidge, 3);
                                    easyRider = setChairliftStatus(easyRider, 2);
                                    skyChair = setChairliftStatus(skyChair, 4);
                                    midwayChair = setChairliftStatus(midwayChair, 5);


                                    panorama = setRunStatusEagleExpress(panorama, 0);
                                    windjammer = setRunStatusEagleExpress(windjammer, 1);
                                    upperFork = setRunStatusEagleExpress(upperFork, 2);
                                    lowerFork = setRunStatusEagleExpress(lowerFork, 3);
                                    jaseyJay = setRunStatusEagleExpress(jaseyJay, 4);
                                    mcIvors = setRunStatusEagleExpress(mcIvors, 5);
                                    unrun = setRunStatusEagleExpress(unrun, 6);
                                    trumpeter = setRunStatusEagleExpress(trumpeter, 7);
                                    lowerTrumpeter = setRunStatusEagleExpress(lowerTrumpeter, 8);
                                    hoseSide = setRunStatusEagleExpress(hoseSide, 9);
                                    bHip = setRunStatusEagleExpress(bHip, 10);
                                    blowBy = setRunStatusEagleExpress(blowBy, 11);
                                    detentionGlades = setRunStatusEagleExpress(detentionGlades, 12);


                                    skatePark = setTerrainParkStatusEagleExpress(skatePark, 13);
                                    district = setTerrainParkStatusEagleExpress(district, 14);
                                    stompingGrounds = setTerrainParkStatusEagleExpress(stompingGrounds, 15);


                                    collins = setRunStatusLionsExpress(collins, 16);
                                    horizon = setRunStatusLionsExpress(horizon, 17);
                                    humptyDumpty = setRunStatusLionsExpress(humptyDumpty, 18);
                                    catTrackUpper = setRunStatusLionsExpress(catTrackUpper, 19);
                                    primaryPower = setRunStatusLionsExpress(primaryPower, 20);
                                    horizonByPass = setRunStatusLionsExpress(horizonByPass, 21);
                                    lowerBowen = setRunStatusLionsExpress(lowerBowen, 22);
                                    hutch = setRunStatusLionsExpress(hutch, 23);
                                    catTrackLower = setRunStatusLionsExpress(catTrackLower, 24);
                                    upperBowen = setRunStatusLionsExpress(upperBowen, 25);
                                    bowenFace = setRunStatusLionsExpress(bowenFace, 26);
                                    gibsons = setRunStatusLionsExpress(gibsons, 27);
                                    upperRainbow = setRunStatusLionsExpress(upperRainbow, 28);
                                    bowenWest = setRunStatusLionsExpress(bowenWest, 29);
                                    rainbow = setRunStatusLionsExpress(rainbow, 30);
                                    slash = setRunStatusLionsExpress(slash, 31);
                                    moons = setRunStatusLionsExpress(moons, 32);
                                    LTDGlades = setRunStatusLionsExpress(LTDGlades, 33);
                                    elevatorGlades = setRunStatusLionsExpress(elevatorGlades, 34);
                                    darksideGlades = setRunStatusLionsExpress(darksideGlades, 35);
                                    cratorGlades = setRunStatusLionsExpress(cratorGlades, 36);
                                    bowenWestGlades = setRunStatusLionsExpress(bowenWestGlades, 37);
                                    underTheVolcanoGlades = setRunStatusLionsExpress(underTheVolcanoGlades, 38);
                                    gibsonGlades = setRunStatusLionsExpress(gibsonGlades, 39);

                                    sunrisePark = setTerrainParkStatusLionsExpress(sunrisePark, 40);
                                    collinsPark = setTerrainParkStatusLionsExpress(collinsPark, 41);

                                    bennys = setRunStatusRavenRidge(bennys, 42);
                                    crazyRaven = setRunStatusRavenRidge(crazyRaven, 43);
                                    lowerCoyote7 = setRunStatusRavenRidge(lowerCoyote7, 44);
                                    threeBears = setRunStatusRavenRidge(threeBears, 45);
                                    rideout = setRunStatusRavenRidge(rideout, 46);
                                    bilodeau = setRunStatusRavenRidge(bilodeau, 47);
                                    upperCoyote7 = setRunStatusRavenRidge(upperCoyote7, 48);
                                    firstSun = setRunStatusRavenRidge(firstSun, 49);
                                    shoreGlades = setRunStatusRavenRidge(shoreGlades, 50);
                                    shoreLine = setRunStatusRavenRidge(shoreLine, 51);
                                    backOnBlack = setRunStatusRavenRidge(backOnBlack, 52);
                                    meteor = setRunStatusRavenRidge(meteor, 53);
                                    blackFly = setRunStatusRavenRidge(blackFly, 54);

                                    runway = setRunStatusEasyRider(runway, 55);
                                    steezyRider = setTerrainParkStatusEasyRider(steezyRider, 56);
                                    gnarlysDen = setTerrainParkStatusEasyRider(gnarlysDen, 57);

                                    T33 = setRunStatusSkyChair(T33, 58);
                                    horseflyCanyon = setRunStatusSkyChair(horseflyCanyon, 59);
                                    glades = setRunStatusSkyChair(glades, 60);
                                    tomcat = setRunStatusSkyChair(tomcat, 61);
                                    ripcord = setRunStatusSkyChair(ripcord, 62);
                                    topGun = setRunStatusSkyChair(topGun, 63);

                                    shuttle = setRunStatusMidwayChair(shuttle, 64);
                                    blaster = setRunStatusMidwayChair(blaster, 65);
                                    hutRun = setRunStatusMidwayChair(hutRun, 66);
                                    webbSite = setRunStatusMidwayChair(webbSite, 67);
                                }
                                counter++;
                                listener.onResultFetched();
                            } catch (Exception ignored) {

                            }
                        }

                    });

                } catch (SSLHandshakeException ignored) {

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

    public String setChairliftStatus(String lift, int rowNum) {

       Element first = cypressWeather.select("div.liftStatus-lift-icon img").get(rowNum);
       // Element first = cypressWeather.select(".liftStatus-lift-icon").get(rowNum);
//        switch (first.select("img").first().absUrl("src")) {
        switch (first.attr("src")) {
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/green-check.png":
                lift = "open";
                liftsOpen++;
                return lift;
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/red-x.png":
                lift = "closed";
                return lift;
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/yellow-caution.png":
                lift = "standby";
                return lift;
        }
        System.out.println("LIFT@@" + lift);
        return lift;
    }


    public String setRunStatusEagleExpress(String run, int rowNum) {

        Element first = cypressWeather.select("div.trailStatus-status img").get(rowNum);

       // Element first = cypressWeather.select(".trailStatus-status").get(rowNum);
        switch (first.attr("src")) {
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/green-check.png":
                run = "open";
                runsOpenEagleExpress++;
                runsOpen++;
                return run;
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/red-x.png":
                run = "closed";
                return run;
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        System.out.println("RUN1111" + run);
        return run;
    }

    public String setTerrainParkStatusEagleExpress(String run, int rowNum) {
        Element first = cypressWeather.select("div.trailStatus-status img").get(rowNum);
        //Element first = cypressWeather.select(".trailStatus-status").get(rowNum);
        switch (first.attr("src")) {
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/green-check.png":
                run = "open";
                terrainParksOpenEagleExpress++;
                terrainParksOpen++;
                return run;
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/red-x.png":
                run = "closed";
                return run;
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }

    public String setRunStatusLionsExpress(String run, int rowNum) {
        Element first = cypressWeather.select("div.trailStatus-status img").get(rowNum);
       // Element first = cypressWeather.select(".trailStatus-status").get(rowNum);
        switch (first.attr("src")) {
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/green-check.png":
                run = "open";
                runsOpenLionsExpress++;
                runsOpen++;
                return run;
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/red-x.png":
                run = "closed";
                return run;
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }

    public String setTerrainParkStatusLionsExpress(String run, int rowNum) {
        Element first = cypressWeather.select("div.trailStatus-status img").get(rowNum);
       // Element first = cypressWeather.select(".trailStatus-status").get(rowNum);
        switch (first.attr("src")) {
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/green-check.png":
                run = "open";
                terrainParksOpenLionsExpress++;
                terrainParksOpen++;
                return run;
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/red-x.png":
                run = "closed";
                return run;
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }


    public String setRunStatusRavenRidge(String run, int rowNum) {
        Element first = cypressWeather.select("div.trailStatus-status img").get(rowNum);
       // Element first = cypressWeather.select(".trailStatus-status").get(rowNum);
        switch (first.attr("src")) {
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/green-check.png":
                run = "open";
                runsOpenRavenRidge++;
                runsOpen++;
                return run;
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/red-x.png":
                run = "closed";
                return run;
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }

    public String setRunStatusEasyRider(String run, int rowNum) {
        Element first = cypressWeather.select("div.trailStatus-status img").get(rowNum);
        //Element first = cypressWeather.select(".trailStatus-status").get(rowNum);
        switch (first.attr("src")) {
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/green-check.png":
                run = "open";
                runsOpenEasyRider++;
                runsOpen++;
                return run;
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/red-x.png":
                run = "closed";
                return run;
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }

    public String setTerrainParkStatusEasyRider(String run, int rowNum) {
        // Element first = cypressWeather.select("li").get(rowNum);
        Element first = cypressWeather.select("div.trailStatus-status img").get(rowNum);
        switch (first.attr("src")) {
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/green-check.png":
                run = "open";
                terrainParksOpenEasyRider++;
                terrainParksOpen++;
                return run;
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/red-x.png":
                run = "closed";
                return run;
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }

    public String setRunStatusSkyChair(String run, int rowNum) {
        // Element first = cypressWeather.select("li").get(rowNum);
        Element first = cypressWeather.select("div.trailStatus-status img").get(rowNum);
        switch (first.attr("src")) {
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/green-check.png":
                run = "open";
                runsOpenSkyChair++;
                runsOpen++;
                return run;
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/red-x.png":
                run = "closed";
                return run;
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }

    public String setRunStatusMidwayChair(String run, int rowNum) {
        // Element first = cypressWeather.select("li").get(rowNum);
        Element first = cypressWeather.select("div.trailStatus-status img").get(rowNum);
        switch (first.attr("src")) {
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/green-check.png":
                run = "open";
                runsOpenMidwayChair++;
                runsOpen++;
                return run;
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/red-x.png":
                run = "closed";
                return run;
            case "https://cypressmountaincdn.azureedge.net/Images/Cypress/Icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }


    public void openError() {
        Intent i = new Intent(context, Error.class);
        i.putExtra("ErrorFrom", "Cypress");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }


}





