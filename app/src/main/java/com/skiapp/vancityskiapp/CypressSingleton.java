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
                    cypressWeather = Jsoup.connect("https://cypressmountain.com/downhill-conditions-and-cams").userAgent("Mozilla").get();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            try {
                                conditions = cypressWeather.select(("span.text.text_14.mix-text_color7.mix-text_alignCenter.mix-text_alignLeftMd.mix-text_regular.mix-text_capitalize")).first().text();
                                String[] arr = conditions.split(" ");
                                StringBuffer sb = new StringBuffer();
                                System.out.println("arr: " + Arrays.toString(arr));

                                for (int i = 0; i < arr.length; i++) {
                                    sb.append(Character.toUpperCase(arr[i].charAt(0)))
                                            .append(arr[i].substring(1)).append(" ");
                                }
                                conditions = sb.toString().trim();
                                if (conditions.equals("Rain/snow"))
                                    conditions = "Rain/Snow";
                            } catch (Exception ignored) {

                            }

                            try {
                                temperature = cypressWeather.select("span.js-measurement").first().text();
                              //  fortyEightHrSnow = cypressWeather.select("span.js-measurement").get(8).text() + "cm";
                                fortyEightHrSnow = "N/A";
                                twentyFourHrSnow = cypressWeather.select("span.js-measurement").get(8).text() + "cm";
                                sevenDaySnow = cypressWeather.select("span.js-measurement").get(9).text() + "cm";
                                seasonSnow = cypressWeather.select("span.js-measurement").get(10).text() + "cm";
                                Element firstCondition = cypressWeather.select("span.text.text_24.text_30Md.mix-text_bold.mix-text_spaceBreak.mix-text_color1.mix-text_alignCenter.mix-text_alignLeftMd.mix-text_lessStrict").first();
                                Element secondCondition = cypressWeather.select("span.text.text_24.text_30Md.mix-text_bold.mix-text_spaceBreak.mix-text_color1.mix-text_alignCenter.mix-text_alignLeftMd.mix-text_lessStrict").get(1);
                                snowConditions = firstCondition.ownText() + " " + secondCondition.ownText();

                                if (counter == 0) {
                                    eagleExpress = setChairliftStatus(eagleExpress, 112);
                                    lionsExpress = setChairliftStatus(lionsExpress, 113);
                                    ravenRidge = setChairliftStatus(ravenRidge, 117);
                                    easyRider = setChairliftStatus(easyRider, 114);
                                    skyChair = setChairliftStatus(skyChair, 115);
                                    midwayChair = setChairliftStatus(midwayChair, 116);

                                    panorama = setRunStatusEagleExpress(panorama, 122);
                                    windjammer = setRunStatusEagleExpress(windjammer, 123);
                                    upperFork = setRunStatusEagleExpress(upperFork, 124);
                                    lowerFork = setRunStatusEagleExpress(lowerFork, 125);
                                    jaseyJay = setRunStatusEagleExpress(jaseyJay, 126);
                                    mcIvors = setRunStatusEagleExpress(mcIvors, 127);
                                    unrun = setRunStatusEagleExpress(unrun, 128);
                                    trumpeter = setRunStatusEagleExpress(trumpeter, 129);
                                    lowerTrumpeter = setRunStatusEagleExpress(lowerTrumpeter, 130);
                                    hoseSide = setRunStatusEagleExpress(hoseSide, 131);
                                    bHip = setRunStatusEagleExpress(bHip, 132);
                                    blowBy = setRunStatusEagleExpress(blowBy, 133);
                                    detentionGlades = setRunStatusEagleExpress(detentionGlades, 134);

                                    skatePark = setTerrainParkStatusEagleExpress(skatePark, 135);
                                    district = setTerrainParkStatusEagleExpress(district, 136);
                                    stompingGrounds = setTerrainParkStatusEagleExpress(stompingGrounds, 137);


                                    collins = setRunStatusLionsExpress(collins, 139);
                                    horizon = setRunStatusLionsExpress(horizon, 140);
                                    humptyDumpty = setRunStatusLionsExpress(humptyDumpty, 141);
                                    catTrackUpper = setRunStatusLionsExpress(catTrackUpper, 142);
                                    primaryPower = setRunStatusLionsExpress(primaryPower, 143);
                                    horizonByPass = setRunStatusLionsExpress(horizonByPass, 144);
                                    lowerBowen = setRunStatusLionsExpress(lowerBowen, 145);
                                    hutch = setRunStatusLionsExpress(hutch, 146);
                                    catTrackLower = setRunStatusLionsExpress(catTrackLower, 147);
                                    upperBowen = setRunStatusLionsExpress(upperBowen, 148);
                                    bowenFace = setRunStatusLionsExpress(bowenFace, 149);
                                    gibsons = setRunStatusLionsExpress(gibsons, 150);
                                    upperRainbow = setRunStatusLionsExpress(upperRainbow, 151);
                                    bowenWest = setRunStatusLionsExpress(bowenWest, 152);
                                    rainbow = setRunStatusLionsExpress(rainbow, 153);
                                    slash = setRunStatusLionsExpress(slash, 154);
                                    moons = setRunStatusLionsExpress(moons, 155);
                                    LTDGlades = setRunStatusLionsExpress(LTDGlades, 156);
                                    elevatorGlades = setRunStatusLionsExpress(elevatorGlades, 157);
                                    darksideGlades = setRunStatusLionsExpress(darksideGlades, 158);
                                    cratorGlades = setRunStatusLionsExpress(cratorGlades, 159);
                                    bowenWestGlades = setRunStatusLionsExpress(bowenWestGlades, 160);
                                    underTheVolcanoGlades = setRunStatusLionsExpress(underTheVolcanoGlades, 161);
                                    gibsonGlades = setRunStatusLionsExpress(gibsonGlades, 162);

                                    sunrisePark = setTerrainParkStatusLionsExpress(sunrisePark, 163);

                                    bennys = setRunStatusRavenRidge(bennys, 165);
                                    crazyRaven = setRunStatusRavenRidge(crazyRaven, 166);
                                    lowerCoyote7 = setRunStatusRavenRidge(lowerCoyote7, 167);
                                    threeBears = setRunStatusRavenRidge(threeBears, 168);
                                    rideout = setRunStatusRavenRidge(rideout, 169);
                                    bilodeau = setRunStatusRavenRidge(bilodeau, 170);
                                    upperCoyote7 = setRunStatusRavenRidge(upperCoyote7, 171);
                                    firstSun = setRunStatusRavenRidge(firstSun, 172);
                                    shoreGlades = setRunStatusRavenRidge(shoreGlades, 173);
                                    shoreLine = setRunStatusRavenRidge(shoreLine, 174);
                                    backOnBlack = setRunStatusRavenRidge(backOnBlack, 175);
                                    meteor = setRunStatusRavenRidge(meteor, 176);
                                    blackFly = setRunStatusRavenRidge(blackFly, 177);

                                    runway = setRunStatusEasyRider(runway, 179);
                                    steezyRider = setTerrainParkStatusEasyRider(steezyRider, 180);
                                    gnarlysDen = setTerrainParkStatusEasyRider(gnarlysDen, 181);

                                    T33 = setRunStatusSkyChair(T33, 183);
                                    horseflyCanyon = setRunStatusSkyChair(horseflyCanyon, 184);
                                    glades = setRunStatusSkyChair(glades, 185);
                                    tomcat = setRunStatusSkyChair(tomcat, 186);
                                    ripcord = setRunStatusSkyChair(ripcord, 187);
                                    topGun = setRunStatusSkyChair(topGun, 188);

                                    shuttle = setRunStatusMidwayChair(shuttle, 190);
                                    blaster = setRunStatusMidwayChair(blaster, 191);
                                    hutRun = setRunStatusMidwayChair(hutRun, 192);
                                    webbSite = setRunStatusMidwayChair(webbSite, 193);
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
       Element first = cypressWeather.select("li").get(rowNum);
        switch (first.select("img").first().absUrl("src")) {
            case "https://cypressmountain.com/images/Cypress/icons/green-check.png":
                lift = "open";
                liftsOpen++;
                return lift;
            case "https://cypressmountain.com/images/Cypress/icons/red-x.png":
                lift = "closed";
                return lift;
            case "https://cypressmountain.com/images/Cypress/icons/yellow-caution.png":
                lift = "standby";
                return lift;
        }
        return lift;
    }


    public String setRunStatusEagleExpress(String run, int rowNum) {
        Element first = cypressWeather.select("li").get(rowNum);
        switch (first.select("img").first().absUrl("src")) {
            case "https://cypressmountain.com/images/Cypress/icons/green-check.png":
                run = "open";
                runsOpenEagleExpress++;
                runsOpen++;
                return run;
            case "https://cypressmountain.com/images/Cypress/icons/red-x.png":
                run = "closed";
                return run;
            case "https://cypressmountain.com/images/Cypress/icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }

    public String setTerrainParkStatusEagleExpress(String run, int rowNum) {
        Element first = cypressWeather.select("li").get(rowNum);
        switch (first.select("img").first().absUrl("src")) {
            case "https://cypressmountain.com/images/Cypress/icons/green-check.png":
                run = "open";
                terrainParksOpenEagleExpress++;
                terrainParksOpen++;
                return run;
            case "https://cypressmountain.com/images/Cypress/icons/red-x.png":
                run = "closed";
                return run;
            case "https://cypressmountain.com/images/Cypress/icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }

    public String setRunStatusLionsExpress(String run, int rowNum) {
        Element first = cypressWeather.select("li").get(rowNum);
        switch (first.select("img").first().absUrl("src")) {
            case "https://cypressmountain.com/images/Cypress/icons/green-check.png":
                run = "open";
                runsOpenLionsExpress++;
                runsOpen++;
                return run;
            case "https://cypressmountain.com/images/Cypress/icons/red-x.png":
                run = "closed";
                return run;
            case "https://cypressmountain.com/images/Cypress/icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }

    public String setTerrainParkStatusLionsExpress(String run, int rowNum) {
        Element first = cypressWeather.select("li").get(rowNum);
        switch (first.select("img").first().absUrl("src")) {
            case "https://cypressmountain.com/images/Cypress/icons/green-check.png":
                run = "open";
                terrainParksOpenLionsExpress++;
                terrainParksOpen++;
                return run;
            case "https://cypressmountain.com/images/Cypress/icons/red-x.png":
                run = "closed";
                return run;
            case "https://cypressmountain.com/images/Cypress/icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }


    public String setRunStatusRavenRidge(String run, int rowNum) {
        Element first = cypressWeather.select("li").get(rowNum);
        switch (first.select("img").first().absUrl("src")) {
            case "https://cypressmountain.com/images/Cypress/icons/green-check.png":
                run = "open";
                runsOpenRavenRidge++;
                runsOpen++;
                return run;
            case "https://cypressmountain.com/images/Cypress/icons/red-x.png":
                run = "closed";
                return run;
            case "https://cypressmountain.com/images/Cypress/icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }

    public String setRunStatusEasyRider(String run, int rowNum) {
        Element first = cypressWeather.select("li").get(rowNum);
        switch (first.select("img").first().absUrl("src")) {
            case "https://cypressmountain.com/images/Cypress/icons/green-check.png":
                run = "open";
                runsOpenEasyRider++;
                runsOpen++;
                return run;
            case "https://cypressmountain.com/images/Cypress/icons/red-x.png":
                run = "closed";
                return run;
            case "https://cypressmountain.com/images/Cypress/icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }

    public String setTerrainParkStatusEasyRider(String run, int rowNum) {
        Element first = cypressWeather.select("li").get(rowNum);
        switch (first.select("img").first().absUrl("src")) {
            case "https://cypressmountain.com/images/Cypress/icons/green-check.png":
                run = "open";
                terrainParksOpenEasyRider++;
                terrainParksOpen++;
                return run;
            case "https://cypressmountain.com/images/Cypress/icons/red-x.png":
                run = "closed";
                return run;
            case "https://cypressmountain.com/images/Cypress/icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }

    public String setRunStatusSkyChair(String run, int rowNum) {
        Element first = cypressWeather.select("li").get(rowNum);
        switch (first.select("img").first().absUrl("src")) {
            case "https://cypressmountain.com/images/Cypress/icons/green-check.png":
                run = "open";
                runsOpenSkyChair++;
                runsOpen++;
                return run;
            case "https://cypressmountain.com/images/Cypress/icons/red-x.png":
                run = "closed";
                return run;
            case "https://cypressmountain.com/images/Cypress/icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }

    public String setRunStatusMidwayChair(String run, int rowNum) {
        Element first = cypressWeather.select("li").get(rowNum);
        switch (first.select("img").first().absUrl("src")) {
            case "https://cypressmountain.com/images/Cypress/icons/green-check.png":
                run = "open";
                runsOpenMidwayChair++;
                runsOpen++;
                return run;
            case "https://cypressmountain.com/images/Cypress/icons/red-x.png":
                run = "closed";
                return run;
            case "https://cypressmountain.com/images/Cypress/icons/yellow-caution.png":
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





