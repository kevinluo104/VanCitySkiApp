package com.skiapp.vancityskiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Error extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        TextView textView232 = findViewById(R.id.textView232);
        Intent intent = getIntent();
        String text = intent.getStringExtra("ErrorFrom");

        switch(text) {
            case "Vancouver":
                textView232.setText("It seems like the weather.gc.ca servers are down and the Vancouver weather could not be loaded. Check the servers at " + "https://weather.gc.ca/city/pages/bc-74_metric_e.html" + ". If the site is up, please contact me @ vancityskiapp@gmail.com to report the issue. Try again in a few minutes after closing the app entirely. Or try again right now by pressing the button below:");
                break;
            case "Grouse":
                textView232.setText("It seems like the Grouse Mountain servers are down and the Grouse Mountain weather could not be loaded. Check the servers at " + "https://grousemountain.com" + ". If the site is up, please contact me @ vancityskiapp@gmail.com to report the issue. Try again in a few minutes after closing the app entirely. Or try again right now by pressing the button below:");
                break;
            case "Cypress":
                textView232.setText("It seems like the Cypress Mountain servers are down and the Cypress Mountain weather could not be loaded. Check the servers at " + "https://cypressmountain.com" + ". If the site is up, please contact me @ vancityskiapp@gmail.com to report the issue. Try again in a few minutes after closing the app entirely. Or try again right now by pressing the button below:");
                break;
                case "Seymour":
                textView232.setText("It seems like the Mount Seymour servers are down and the Mount Seymour weather could not be loaded. " + System.getProperty("line.separator") +
                        "Check the servers at " + System.getProperty("line.separator") + "https://mtseymour.ca/today)" + ". If the site is up, please contact me @ vancityskiapp@gmail.com to report the issue. Try again in a few minutes after closing the app entirely." +
                        " Or try again right now by pressing the button below:");
        }
        Button button17 = findViewById(R.id.button17);
        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}