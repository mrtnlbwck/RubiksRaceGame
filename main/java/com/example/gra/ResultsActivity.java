package com.example.gra;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    /** Pole tekstowe zawierające ostanią ilość ruchów */
    public  TextView textLastStep;
    /** Pole tekstowe zawierające ostani czas */
    private TextView textLastTime;
    /** Pole tekstowe zawierające najniższą ilość ruchów */
    public  TextView textBestStep;
    /** Pole tekstowe zawierające najlepszy czas */
    private TextView textBestTime;
    private MyBase myBase;
    /** Przycisk otwierający menu główne */
    private Button buttonMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        textLastStep = findViewById(R.id.text_last_step);
        textLastTime = findViewById(R.id.text_last_time);
        textBestStep = findViewById(R.id.text_best_step);
        textBestTime = findViewById(R.id.text_best_time);
        buttonMenu = findViewById(R.id.button_menu_results);

        myBase = new MyBase(this);

        loadData();
    }

    /** Metoda wyświetlająca wyniki oraz przycisk */
    private void loadData(){
        textLastStep.setText(String.valueOf(myBase.getLastStep()));
        textBestStep.setText(String.valueOf(myBase.getBestStep()));

        int lastTime = myBase.getLastTime();
        int lastSecond = lastTime %60;
        int lastHour = lastTime/3600;
        int lastMinute = (lastTime-lastHour*3600)/60;
        textLastTime.setText(String.format("%02d:%02d:%02d",lastHour,lastMinute,lastSecond));

        int bestTime = myBase.getBestTime();
        int bestSecond = bestTime %60;
        int bestHour = bestTime/3600;
        int bestMinute = (bestTime-bestHour*3600)/60;
        textBestTime.setText(String.format("%02d:%02d:%02d",bestHour,bestMinute,bestSecond));

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){
            textLastStep.setText(String.valueOf(myBase.getLastStep()));
            textLastStep.setText(String.valueOf(myBase.getBestStep()));

            int lastTime = myBase.getLastTime();
            int lastSecond = lastTime %60;
            int lastHour = lastTime/3600;
            int lastMinute = (lastTime-lastHour*3600)/60;
            textLastTime.setText(String.format("%02d:%02d:%02d",lastHour,lastMinute,lastSecond));

            int bestTime = myBase.getBestTime();
            int bestSecond = bestTime %60;
            int bestHour = bestTime/3600;
            int bestMinute = (bestTime-bestHour*3600)/60;
            textBestTime.setText(String.format("%02d:%02d:%02d",bestHour,bestMinute,bestSecond));
        }
    }
}
