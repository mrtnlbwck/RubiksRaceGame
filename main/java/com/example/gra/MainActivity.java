package com.example.gra;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Przycisk otwierający nową grę */
        Button buttonPlayGame = findViewById(R.id.button_play_game);
        buttonPlayGame.setOnClickListener(onClickListener);
        /** Przycisk otwierający wyniki */
        Button buttonResultsGame = findViewById(R.id.button_results_game);
        buttonResultsGame.setOnClickListener(onClickListener);
        /** Przycisk otwierający wyniki */
        Button buttonRulesGame = findViewById(R.id.button_rules_game);
        buttonRulesGame.setOnClickListener(onClickListener);

    }

    /** Słuchacz dodający metody otwierjące dane aktywności */
    View.OnClickListener onClickListener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.button_play_game:
                        newGame();
                        startActivityForResult(new Intent(MainActivity.this,GameActivity.class),REQUEST_CODE);
                        break;
                    case R.id.button_results_game:
                        results();
                        break;
                    case R.id.button_rules_game:
                        rules();
                        break;
                    default:
                        break;
                }
            }
        };

    /** Metoda otwierajca nową grę */
    private void newGame () {
        GameActivity.timeCount = 0;
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
        finishAndRemoveTask();
    }

    /** Metoda otwierająca zasady */
    private void rules () {
        Intent intent = new Intent(MainActivity.this, RulesActivity.class);
        startActivity(intent);
        finish();
    }

    /** Metoda otwierająca wyniki */
    private void results () {
        Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
        startActivity(intent);
        finish();
    }

}