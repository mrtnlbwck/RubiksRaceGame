package com.example.gra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);

        /** Przycisk powracający do gry */
        Button buttonResume = findViewById(R.id.button_resume);
        buttonResume.setOnClickListener(onClickListener);
        /** Przycisk powracający do menu głównego */
        Button buttonMenu = findViewById(R.id.button_menu);
        buttonMenu.setOnClickListener(onClickListener);
        /** Przycisk resetujący grę */
        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(onClickListener);

    }

    /** Słuchacz dodający metody otwierjące dane aktywności */
    View.OnClickListener onClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_resume:
                    resume();
                    break;
                case R.id.button_menu:
                    menu();
                    break;
                case R.id.button_reset:
                    reset();
                    break;
                default:
                    break;
            }
        }
    };

    /** Metoda kontynuująca grę */
    private void resume(){
        GameActivity.loadTimer();
        for (int i = 0; i < GameActivity.group.getChildCount(); i++) {
            GameActivity.buttons[i/5][i%5].setClickable(true);
        }
        finish();
    }

    /** Metoda powracająca do menu */
    private void menu() {
        Intent intent = new Intent(StopActivity.this, MainActivity.class);
        startActivity(intent);
        finishAndRemoveTask();
    }

    /** Metoda resetująca grę */
    private void reset() {
        GameActivity.timeCount = 0;
        Intent intent = new Intent(StopActivity.this, GameActivity.class);
        startActivity(intent);
        finishAndRemoveTask();
    }
}