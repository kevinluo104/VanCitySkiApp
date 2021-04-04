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
                                    eagleExpress = setChairliftStatus(eagleExpress, 0);
                                    lionsExpress = setChairliftStatus(lionsExpress, 1);
                                    ravenRidge = setChairliftStatus(ravenRidge, 3);
                                    easyRider = setChairliftStatus(easyRider, 2);
                                    skyChair = setChairliftStatus(skyChair, 4);
                                    midwayChair = setChairliftStatus(midwayChair, 5);

                                    panorama = setRunStatusEagleExpress(panorama, 119);
                                    windjammer = setRunStatusEagleExpress(windjammer, 120);
                                    upperFork = setRunStatusEagleExpress(upperFork, 121);
                                    lowerFork = setRunStatusEagleExpress(lowerFork, 122);
                                    jaseyJay = setRunStatusEagleExpress(jaseyJay, 123);
                                    mcIvors = setRunStatusEagleExpress(mcIvors, 124);
                                    unrun = setRunStatusEagleExpress(unrun, 125);
                                    trumpeter = setRunStatusEagleExpress(trumpeter, 126);
                                    lowerTrumpeter = setRunStatusEagleExpress(lowerTrumpeter, 127);
                                    hoseSide = setRunStatusEagleExpress(hoseSide, 128);
                                    bHip = setRunStatusEagleExpress(bHip, 129);
                                    blowBy = setRunStatusEagleExpress(blowBy, 130);
                                    detentionGlades = setRunStatusEagleExpress(detentionGlades, 131);

                                    skatePark = setTerrainParkStatusEagleExpress(skatePark, 132);
                                    district = setTerrainParkStatusEagleExpress(district, 133);
                                    stompingGrounds = setTerrainParkStatusEagleExpress(stompingGrounds, 134);


                                    collins = setRunStatusLionsExpress(collins, 136);
                                    horizon = setRunStatusLionsExpress(horizon, 137);
                                    humptyDumpty = setRunStatusLionsExpress(humptyDumpty, 138);
                                    catTrackUpper = setRunStatusLionsExpress(catTrackUpper, 139);
                                    primaryPower = setRunStatusLionsExpress(primaryPower, 140);
                                    horizonByPass = setRunStatusLionsExpress(horizonByPass, 141);
                                    lowerBowen = setRunStatusLionsExpress(lowerBowen, 142);
                                    hutch = setRunStatusLionsExpress(hutch, 143);
                                    catTrackLower = setRunStatusLionsExpress(catTrackLower, 144);
                                    upperBowen = setRunStatusLionsExpress(upperBowen, 145);
                                    bowenFace = setRunStatusLionsExpress(bowenFace, 146);
                                    gibsons = setRunStatusLionsExpress(gibsons, 147);
                                    upperRainbow = setRunStatusLionsExpress(upperRainbow, 148);
                                    bowenWest = setRunStatusLionsExpress(bowenWest, 149);
                                    rainbow = setRunStatusLionsExpress(rainbow, 150);
                                    slash = setRunStatusLionsExpress(slash, 151);
                                    moons = setRunStatusLionsExpress(moons, 152);
                                    LTDGlades = setRunStatusLionsExpress(LTDGlades, 153);
                                    elevatorGlades = setRunStatusLionsExpress(elevatorGlades, 154);
                                    darksideGlades = setRunStatusLionsExpress(darksideGlades, 155);
                                    cratorGlades = setRunStatusLionsExpress(cratorGlades, 156);
                                    bowenWestGlades = setRunStatusLionsExpress(bowenWestGlades, 157);
                                    underTheVolcanoGlades = setRunStatusLionsExpress(underTheVolcanoGlades, 158);
                                    gibsonGlades = setRunStatusLionsExpress(gibsonGlades, 159);

                                    sunrisePark = setTerrainParkStatusLionsExpress(sunrisePark, 160);

                                    bennys = setRunStatusRavenRidge(bennys, 162);
                                    crazyRaven = setRunStatusRavenRidge(crazyRaven, 163);
                                    lowerCoyote7 = setRunStatusRavenRidge(lowerCoyote7, 164);
                                    threeBears = setRunStatusRavenRidge(threeBears, 165);
                                    rideout = setRunStatusRavenRidge(rideout, 166);
                                    bilodeau = setRunStatusRavenRidge(bilodeau, 167);
                                    upperCoyote7 = setRunStatusRavenRidge(upperCoyote7, 168);
                                    firstSun = setRunStatusRavenRidge(firstSun, 169);
                                    shoreGlades = setRunStatusRavenRidge(shoreGlades, 170);
                                    shoreLine = setRunStatusRavenRidge(shoreLine, 171);
                                    backOnBlack = setRunStatusRavenRidge(backOnBlack, 172);
                                    meteor = setRunStatusRavenRidge(meteor, 173);
                                    blackFly = setRunStatusRavenRidge(blackFly, 174);

                                    runway = setRunStatusEasyRider(runway, 176);
                                    steezyRider = setTerrainParkStatusEasyRider(steezyRider, 177);
                                    gnarlysDen = setTerrainParkStatusEasyRider(gnarlysDen, 178);

                                    T33 = setRunStatusSkyChair(T33, 180);
                                    horseflyCanyon = setRunStatusSkyChair(horseflyCanyon, 181);
                                    glades = setRunStatusSkyChair(glades, 182);
                                    tomcat = setRunStatusSkyChair(tomcat, 183);
                                    ripcord = setRunStatusSkyChair(ripcord, 184);
                                    topGun = setRunStatusSkyChair(topGun, 185);

                                    shuttle = setRunStatusMidwayChair(shuttle, 187);
                                    blaster = setRunStatusMidwayChair(blaster, 188);
                                    hutRun = setRunStatusMidwayChair(hutRun, 189);
                                    webbSite = setRunStatusMidwayChair(webbSite, 190);
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
      // Element first = cypressWeather.select("li").get(rowNum);
        Element first = cypressWeather.select("div.icon.icon_12.mix-icon_leader.mix-icon_alignTop.mix-icon_marginTop").get(rowNum);
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





