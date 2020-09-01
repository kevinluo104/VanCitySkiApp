package com.skiapp.vancityskiapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

import javax.net.ssl.SSLHandshakeException;

public class CypressSingleton extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    private static CypressSingleton instance = null;
    private static ResultListener listener;
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private Document cypressWeather;
    private Document pastCypressWeather;
    public String conditions = "";
    public String temperature = "";
    public String overnightSnow = "OnightSnow";
    public String sevenDaySnow = "7DaySnow";
    public String twentyFourHrSnow = "24hSnow";
    public String seasonSnow = "SeasonSnow";
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
                    pastCypressWeather = Jsoup.connect("https://web.archive.org/web/20190301170230/http://www.cypressmountain.com/downhill-conditions-and-cams").userAgent("Mozilla").get();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            conditions = cypressWeather.select(("span.text.text_14.mix-text_color7.mix-text_alignCenter.mix-text_alignLeftMd.mix-text_regular.mix-text_capitalize")).first().text();
                            temperature = cypressWeather.select("span.js-measurement").first().text();
                            overnightSnow = cypressWeather.select("span.js-measurement").get(4).text() + "cm";
                            twentyFourHrSnow = cypressWeather.select("span.js-measurement").get(5).text() + "cm";
                            sevenDaySnow = cypressWeather.select("span.js-measurement").get(6).text() + "cm";
                            seasonSnow = cypressWeather.select("span.js-measurement").get(7).text() + "cm";

                            if (counter == 0) {
                                eagleExpress = setChairliftStatus(eagleExpress, 134);
                                lionsExpress = setChairliftStatus(lionsExpress, 135);
                                ravenRidge = setChairliftStatus(ravenRidge, 136);
                                easyRider = setChairliftStatus(easyRider, 137);
                                skyChair = setChairliftStatus(skyChair, 138);
                                midwayChair = setChairliftStatus(midwayChair, 139);

                                panorama = setRunStatusEagleExpress(panorama, 144);
                                windjammer = setRunStatusEagleExpress(windjammer, 145);
                                upperFork = setRunStatusEagleExpress(upperFork, 146);
                                lowerFork = setRunStatusEagleExpress(lowerFork, 147);
                                jaseyJay = setRunStatusEagleExpress(jaseyJay, 148);
                                mcIvors = setRunStatusEagleExpress(mcIvors, 149);
                                unrun = setRunStatusEagleExpress(unrun, 150);
                                trumpeter = setRunStatusEagleExpress(trumpeter, 151);
                                lowerTrumpeter = setRunStatusEagleExpress(lowerTrumpeter, 152);
                                hoseSide = setRunStatusEagleExpress(hoseSide, 153);
                                bHip = setRunStatusEagleExpress(bHip, 154);
                                blowBy = setRunStatusEagleExpress(blowBy, 155);
                                detentionGlades = setRunStatusEagleExpress(detentionGlades, 156);

                                skatePark = setTerrainParkStatusEagleExpress(skatePark, 157);
                                district = setTerrainParkStatusEagleExpress(district, 158);
                                stompingGrounds = setTerrainParkStatusEagleExpress(stompingGrounds, 159);


                                collins = setRunStatusLionsExpress(collins, 161);
                                horizon = setRunStatusLionsExpress(horizon, 162);
                                humptyDumpty = setRunStatusLionsExpress(humptyDumpty, 163);
                                catTrackUpper = setRunStatusLionsExpress(catTrackUpper, 164);
                                primaryPower = setRunStatusLionsExpress(primaryPower, 165);
                                horizonByPass = setRunStatusLionsExpress(horizonByPass, 166);
                                lowerBowen = setRunStatusLionsExpress(lowerBowen, 167);
                                hutch = setRunStatusLionsExpress(hutch, 168);
                                catTrackLower = setRunStatusLionsExpress(catTrackLower, 169);
                                upperBowen = setRunStatusLionsExpress(upperBowen, 170);
                                bowenFace = setRunStatusLionsExpress(bowenFace, 171);
                                gibsons = setRunStatusLionsExpress(gibsons, 172);
                                upperRainbow = setRunStatusLionsExpress(upperRainbow, 173);
                                bowenWest = setRunStatusLionsExpress(bowenWest, 174);
                                rainbow = setRunStatusLionsExpress(rainbow, 175);
                                slash = setRunStatusLionsExpress(slash, 176);
                                moons = setRunStatusLionsExpress(moons, 177);
                                LTDGlades = setRunStatusLionsExpress(LTDGlades, 178);
                                elevatorGlades = setRunStatusLionsExpress(elevatorGlades, 179);
                                darksideGlades = setRunStatusLionsExpress(darksideGlades, 180);
                                cratorGlades = setRunStatusLionsExpress(cratorGlades, 181);
                                bowenWestGlades = setRunStatusLionsExpress(bowenWestGlades, 182);
                                underTheVolcanoGlades = setRunStatusLionsExpress(underTheVolcanoGlades, 183);
                                gibsonGlades = setRunStatusLionsExpress(gibsonGlades, 184);

                                sunrisePark = setTerrainParkStatusLionsExpress(sunrisePark, 185);

                                bennys = setRunStatusRavenRidge(bennys, 187);
                                crazyRaven = setRunStatusRavenRidge(crazyRaven, 188);
                                lowerCoyote7 = setRunStatusRavenRidge(lowerCoyote7, 189);
                                threeBears = setRunStatusRavenRidge(threeBears, 190);
                                rideout = setRunStatusRavenRidge(rideout, 191);
                                bilodeau = setRunStatusRavenRidge(bilodeau, 192);
                                upperCoyote7 = setRunStatusRavenRidge(upperCoyote7, 193);
                                firstSun = setRunStatusRavenRidge(firstSun, 194);
                                shoreGlades = setRunStatusRavenRidge(shoreGlades, 195);
                                shoreLine = setRunStatusRavenRidge(shoreLine, 196);
                                backOnBlack = setRunStatusRavenRidge(backOnBlack, 197);
                                meteor = setRunStatusRavenRidge(meteor, 198);
                                blackFly = setRunStatusRavenRidge(blackFly, 199);

                                runway = setRunStatusEasyRider(runway, 201);
                                steezyRider = setTerrainParkStatusEasyRider(steezyRider, 202);
                                gnarlysDen = setTerrainParkStatusEasyRider(gnarlysDen, 203);

                                T33 = setRunStatusSkyChair(T33, 205);
                                horseflyCanyon = setRunStatusSkyChair(horseflyCanyon, 206);
                                glades = setRunStatusSkyChair(glades, 207);
                                tomcat = setRunStatusSkyChair(tomcat, 208);
                                ripcord = setRunStatusSkyChair(ripcord, 209);
                                topGun = setRunStatusSkyChair(topGun, 210);

                                shuttle = setRunStatusMidwayChair(shuttle, 212);
                                blaster = setRunStatusMidwayChair(blaster, 213);
                                hutRun = setRunStatusMidwayChair(hutRun, 214);
                                webbSite = setRunStatusMidwayChair(webbSite, 215);
                            }
                            counter++;
                            listener.onResultFetched();
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
        Element first = pastCypressWeather.select("li").get(rowNum);
        switch (first.select("img").first().absUrl("src")) {
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/green-check.png":
                lift = "open";
                liftsOpen++;
                return lift;
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/red-x.png":
                lift = "closed";
                return lift;
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/yellow-caution.png":
                lift = "standby";
                return lift;
        }
        return lift;
    }


    public String setRunStatusEagleExpress(String run, int rowNum) {
        Element first = pastCypressWeather.select("li").get(rowNum);
        switch (first.select("img").first().absUrl("src")) {
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/green-check.png":
                run = "open";
                runsOpenEagleExpress++;
                runsOpen++;
                return run;
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/red-x.png":
                run = "closed";
                return run;
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }

    public String setTerrainParkStatusEagleExpress(String run, int rowNum) {
        Element first = pastCypressWeather.select("li").get(rowNum);
        switch (first.select("img").first().absUrl("src")) {
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/green-check.png":
                run = "open";
                terrainParksOpenEagleExpress++;
                terrainParksOpen++;
                return run;
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/red-x.png":
                run = "closed";
                return run;
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }

    public String setRunStatusLionsExpress(String run, int rowNum) {
        Element first = pastCypressWeather.select("li").get(rowNum);
        switch (first.select("img").first().absUrl("src")) {
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/green-check.png":
                run = "open";
                runsOpenLionsExpress++;
                runsOpen++;
                return run;
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/red-x.png":
                run = "closed";
                return run;
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }

    public String setTerrainParkStatusLionsExpress(String run, int rowNum) {
        Element first = pastCypressWeather.select("li").get(rowNum);
        switch (first.select("img").first().absUrl("src")) {
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/green-check.png":
                run = "open";
                terrainParksOpenLionsExpress++;
                terrainParksOpen++;
                return run;
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/red-x.png":
                run = "closed";
                return run;
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }


    public String setRunStatusRavenRidge(String run, int rowNum) {
        Element first = pastCypressWeather.select("li").get(rowNum);
        switch (first.select("img").first().absUrl("src")) {
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/green-check.png":
                run = "open";
                runsOpenRavenRidge++;
                runsOpen++;
                return run;
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/red-x.png":
                run = "closed";
                return run;
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }

    public String setRunStatusEasyRider(String run, int rowNum) {
        Element first = pastCypressWeather.select("li").get(rowNum);
        switch (first.select("img").first().absUrl("src")) {
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/green-check.png":
                run = "open";
                runsOpenEasyRider++;
                runsOpen++;
                return run;
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/red-x.png":
                run = "closed";
                return run;
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }

    public String setTerrainParkStatusEasyRider(String run, int rowNum) {
        Element first = pastCypressWeather.select("li").get(rowNum);
        switch (first.select("img").first().absUrl("src")) {
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/green-check.png":
                run = "open";
                terrainParksOpenEasyRider++;
                terrainParksOpen++;
                return run;
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/red-x.png":
                run = "closed";
                return run;
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }

    public String setRunStatusSkyChair(String run, int rowNum) {
        Element first = pastCypressWeather.select("li").get(rowNum);
        switch (first.select("img").first().absUrl("src")) {
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/green-check.png":
                run = "open";
                runsOpenSkyChair++;
                runsOpen++;
                return run;
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/red-x.png":
                run = "closed";
                return run;
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/yellow-caution.png":
                run = "standby";
                return run;
        }
        return run;
    }

    public String setRunStatusMidwayChair(String run, int rowNum) {
        Element first = pastCypressWeather.select("li").get(rowNum);
        switch (first.select("img").first().absUrl("src")) {
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/green-check.png":
                run = "open";
                runsOpenMidwayChair++;
                runsOpen++;
                return run;
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/red-x.png":
                run = "closed";
                return run;
            case "https://web.archive.org/web/20190301170230im_/http://www.cypressmountain.com/assets/images/Cypress/icons/yellow-caution.png":
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





