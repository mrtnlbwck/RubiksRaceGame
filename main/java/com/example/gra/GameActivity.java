package com.example.gra;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private int emptyX = 4;
    /**zmienna określająca położenie x pustego pola**/
    private int emptyY = 4;
    /**zmienna określająca położenie y pustego pola**/
    public static RelativeLayout group;
    public static Button[][] buttons;
    /** kafelki planszy 5x5 **/
    private Button[][] buttons2;
    /** kafelki planszy 3x3 **/
    private int[] tiles;
    /** zmienna pomocnicza w losowaniu kafelków planszy 5x5 **/
    private int[] tiles2;
    /** zmienna pomocnicza w losowaniu kafelków planszy 3x3 **/
    private TextView textViewSteps;
    private int stepCount = 0;
    private static TextView textViewTime;
    static Timer timer;
    public static int timeCount = 0;
    private Button buttonShuffle;
    /** przycisk do mieszania kafelków planszy 5x5 podczas gry **/
    private Button buttonStop;
    /** przycisk pauzy **/
    private static boolean isTimeRunning;
    private MyBase myBase;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        loadViews();
        loadViewsUpper();
        loadNumbers();
        loadNumbersUpper();
        generateNumbers();
        generateNumbersUpper();
        loadDataToViews();
        loadDataToViewsUpper();

    }


    /** przypisywanie wylosowanego koloru do przycisków planszy 5x5 */
    private void loadDataToViews() {
        emptyX = 4;
        emptyY = 4;
        for (int i = 0; i < 24; i++) {
            if (tiles[i] < 5) {
                buttons[i / 5][i % 5].setText("B");
                buttons[i / 5][i % 5].setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlue));
                buttons[i / 5][i % 5].setTextColor(ContextCompat.getColor(this, R.color.colorBlue));
            } else if (tiles[i] > 4 && tiles[i] < 9) {
                buttons[i / 5][i % 5].setText("G");
                buttons[i / 5][i % 5].setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
                buttons[i / 5][i % 5].setTextColor(ContextCompat.getColor(this, R.color.colorGreen));
            } else if (tiles[i] > 8 && tiles[i] < 13) {
                buttons[i / 5][i % 5].setText("Y");
                buttons[i / 5][i % 5].setBackgroundColor(ContextCompat.getColor(this, R.color.colorYellow));
                buttons[i / 5][i % 5].setTextColor(ContextCompat.getColor(this, R.color.colorYellow));
            } else if (tiles[i] > 12 && tiles[i] < 17) {
                buttons[i / 5][i % 5].setText("O");
                buttons[i / 5][i % 5].setBackgroundColor(ContextCompat.getColor(this, R.color.colorOrange));
                buttons[i / 5][i % 5].setTextColor(ContextCompat.getColor(this, R.color.colorOrange));
            } else if (tiles[i] > 16 && tiles[i] < 21) {
                buttons[i / 5][i % 5].setText("R");
                buttons[i / 5][i % 5].setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed));
                buttons[i / 5][i % 5].setTextColor(ContextCompat.getColor(this, R.color.colorRed));
            } else if (tiles[i] > 20 && tiles[i] < 25) {
                buttons[i / 5][i % 5].setText("P");
                buttons[i / 5][i % 5].setBackgroundColor(ContextCompat.getColor(this, R.color.colorPurple));
                buttons[i / 5][i % 5].setTextColor(ContextCompat.getColor(this, R.color.colorPurple));
            }
        }
        buttons[emptyX][emptyY].setText("");
        buttons[emptyX][emptyY].setBackgroundColor(ContextCompat.getColor(this, R.color.colorFreeButton));
    }

    /** losowanie liczb do użycia w przypisywaniu koloró kafelków planszy 5x5 */
    private void generateNumbers() {
        int n = 24;
        Random random = new Random();
        while (n > 1) {
            int randomNum = random.nextInt(n--);
            int temp = tiles[randomNum];
            tiles[randomNum] = tiles[n];
            tiles[n] = temp;
        }
    }

    private void loadNumbers() {
        tiles = new int[25];
        for (int i = 0; i < 24; i++) {
            tiles[i] = i + 1;
        }
    }

    /**stworzenie stopera */
    static void loadTimer(){
        isTimeRunning = true;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                timeCount++;
                setTime(timeCount);
            }
        }, 1000,1000);
    }

    /**
     * liczenie i wyświetlanie czasu
     * @param timeCount
     */
    private static void setTime(int timeCount){
        int second = timeCount % 60;
        int hour = timeCount / 3600;
        int minute = (timeCount - hour*3600) / 60;

        textViewTime.setText((String.format("Czas: %02d:%02d:%02d", hour,minute,second)));
    }

    /** wyświetlanie kafelków planszy 5x5 oraz przycisków 'mieszaj' i 'pauza' */
    private void loadViews() {
        group = findViewById(R.id.group);
        textViewSteps = findViewById(R.id.text_view_steps);
        textViewTime = findViewById(R.id.text_view_time);
        buttonShuffle = findViewById(R.id.button_shuffle);
        buttonStop = findViewById(R.id.button_stop);

        loadTimer();
        buttons = new Button[5][5];

        for (int i = 0; i < 25; i++) {
            buttons[i / 5][i % 5] = (Button) group.getChildAt(i);
        }
        buttonShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateNumbers();
                loadDataToViews();
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTimeRunning){
                    timer.cancel();
                    isTimeRunning = false;
                    Intent intent = new Intent(GameActivity.this, StopActivity.class);
                    startActivity(intent);
                    for (int i = 0; i < group.getChildCount(); i++) {
                        buttons[i/5][i%5].setClickable(false);

                    }
                }
            }
        });
    }

    /** metoda określająca przemieszczanie się kafelków */
    @SuppressLint("ResourceType")
    public void buttonClick(View view) {
        Button button = (Button) view;
        int x = button.getTag().toString().charAt(0) - '0';
        int y = button.getTag().toString().charAt(1) - '0';

        if ((Math.abs(emptyX - x) == 1 && emptyY == y) || (Math.abs(emptyY - y) == 1 && emptyX == x)) {
            /** wyznaczanie niebieskich kafelków */
            if (button.getText() == "B") {
                buttons[emptyX][emptyY].setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlue));
                buttons[emptyX][emptyY].setTextColor(ContextCompat.getColor(this, R.color.colorBlue));
            }
            /** wyznaczanie czerwonych kafelków */
            if (button.getText() == "R") {
                buttons[emptyX][emptyY].setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed));
                buttons[emptyX][emptyY].setTextColor(ContextCompat.getColor(this, R.color.colorRed));
            }
            /** wyznaczanie zielonych kafelków */
            if (button.getText() == "G") {
                buttons[emptyX][emptyY].setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
                buttons[emptyX][emptyY].setTextColor(ContextCompat.getColor(this, R.color.colorGreen));
            }
            /** wyznaczanie żółtych kafelków */
            if (button.getText() == "Y") {
                buttons[emptyX][emptyY].setBackgroundColor(ContextCompat.getColor(this, R.color.colorYellow));
                buttons[emptyX][emptyY].setTextColor(ContextCompat.getColor(this, R.color.colorYellow));
            }
            /** wyznaczanie pomarańczowych kafelków */
            if (button.getText() == "O") {
                buttons[emptyX][emptyY].setBackgroundColor(ContextCompat.getColor(this, R.color.colorOrange));
                buttons[emptyX][emptyY].setTextColor(ContextCompat.getColor(this, R.color.colorOrange));
            }
            /** wyznaczanie fioletowych kafelków */
            if (button.getText() == "P") {
                buttons[emptyX][emptyY].setBackgroundColor(ContextCompat.getColor(this, R.color.colorPurple));
                buttons[emptyX][emptyY].setTextColor(ContextCompat.getColor(this, R.color.colorPurple));
            }
            /** ustawianie pustego pola */
            buttons[emptyX][emptyY].setText(button.getText().toString());
            button.setText("");
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.colorFreeButton));
            emptyX = x;
            emptyY = y;
            /** podliczenie ruchów */
            stepCount++;
            /** wyświetlanie ruchów */
            textViewSteps.setText("Ruchy: " + stepCount);
            /** sprawdzenie wygranej */
            checkWin();
        }
    }

    /** sprawdzanie wygranej */
    private void checkWin(){
        boolean isWin = false;
            if(
            (buttons2[0][0].getText().equals(buttons[1][1].getText()))&&
            (buttons2[0][1].getText().equals(buttons[1][2].getText()))&&
            (buttons2[0][2].getText().equals(buttons[1][3].getText()))&&
            (buttons2[1][0].getText().equals(buttons[2][1].getText()))&&
            (buttons2[1][1].getText().equals(buttons[2][2].getText()))&&
            (buttons2[1][2].getText().equals(buttons[2][3].getText()))&&
            (buttons2[2][0].getText().equals(buttons[3][1].getText()))&&
            (buttons2[2][1].getText().equals(buttons[3][2].getText()))&&
            (buttons2[2][2].getText().equals(buttons[3][3].getText()))){
                isWin=true;
            };
        if(isWin) {
            Toast.makeText(this, "Wygrana!\nRuchy: " +stepCount , Toast.LENGTH_SHORT).show();
            for (int i = 0; i < 25; i++) {
                buttons[i / 5][i % 5].setClickable(false);
            }
            timer.cancel();
            buttonShuffle.setClickable(false);
            buttonStop.setClickable(false);
            player = MediaPlayer.create(GameActivity.this, R.raw.sound);
            player.start();
            saveData();
            endGame();
        }

    }

    /** zapisywanie danych **/
    private void saveData(){
        myBase = new MyBase(GameActivity.this);
        myBase.saveLastStep(stepCount);
        myBase.saveLastTime(timeCount);
        if (myBase.getBestStep()!=0){
            if(myBase.getBestStep()>stepCount)
                myBase.saveBestStep(stepCount);
        }else
            myBase.saveBestStep(timeCount);
        if (myBase.getBestTime()!=0){
            if(myBase.getBestTime()>timeCount)
                myBase.saveBestTime(timeCount);
        }else
            myBase.saveBestTime(timeCount);
    }

    /** Otwieranie okna dialogowego po wygranej grze */
    private void endGame(){
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        builder.setMessage("Ilość ruchów = " + stepCount);
        builder.setTitle("Wygrana!");
        builder.setCancelable(false);
        /** Przejście do menu głównego */
        builder.setNegativeButton("Menu główne", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        /** Otwarcie nowej gry */
        builder.setPositiveButton("Zagraj jeszcze raz", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                GameActivity.timeCount = 0;
                Intent intent = new Intent(GameActivity.this, GameActivity.class);
                startActivity(intent);
               // finish();
            }
        });

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                builder.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(ResultsActivity.REQUEST_CODE);
    }

    /** przypisywanie wylosowanego koloru do przycisków planszy 3x3*/
    private void loadDataToViewsUpper(){
        for (int i = 0; i < 9; i++) {
            /** wyznaczanie niebieskich kafelków */
            if (tiles2[i] < 5) {
                buttons2[i / 3][i % 3].setText("B");
                buttons2[i / 3][i % 3].setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlue));
                buttons2[i / 3][i % 3].setTextColor(ContextCompat.getColor(this, R.color.colorBlue));
                /** wyznaczanie zielonych kafelków */
            } else if (tiles2[i] > 4 && tiles2[i] < 9) {
                buttons2[i / 3][i % 3].setText("G");
                buttons2[i / 3][i % 3].setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
                buttons2[i / 3][i % 3].setTextColor(ContextCompat.getColor(this, R.color.colorGreen));
                /** wyznaczanie żółtych kafelków */
            } else if (tiles2[i] > 8 && tiles2[i] < 13) {
                buttons2[i / 3][i % 3].setText("Y");
                buttons2[i / 3][i % 3].setBackgroundColor(ContextCompat.getColor(this, R.color.colorYellow));
                buttons2[i / 3][i % 3].setTextColor(ContextCompat.getColor(this, R.color.colorYellow));
                /** wyznaczanie pomarańczowych kafelków */
            } else if (tiles2[i] > 12 && tiles2[i] < 17) {
                buttons2[i / 3][i % 3].setText("O");
                buttons2[i / 3][i % 3].setBackgroundColor(ContextCompat.getColor(this, R.color.colorOrange));
                buttons2[i / 3][i % 3].setTextColor(ContextCompat.getColor(this, R.color.colorOrange));
                /** wyznaczanie czerwonych kafelków */
            } else if (tiles2[i] > 16 && tiles2[i] < 21) {
                buttons2[i / 3][i % 3].setText("R");
                buttons2[i / 3][i % 3].setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed));
                buttons2[i / 3][i % 3].setTextColor(ContextCompat.getColor(this, R.color.colorRed));
                /** wyznaczanie fioletowych kafelków */
            } else if (tiles2[i] > 20 && tiles2[i] < 25) {
                buttons2[i / 3][i % 3].setText("P");
                buttons2[i / 3][i % 3].setBackgroundColor(ContextCompat.getColor(this, R.color.colorPurple));
                buttons2[i / 3][i % 3].setTextColor(ContextCompat.getColor(this, R.color.colorPurple));
            }
        }

    }

    /** losowanie liczb do użycia w przypisywaniu kolorów kafelków planszy 3x3 **/
    private void generateNumbersUpper() {
        int n = 24;
        Random random = new Random();
        while (n > 1) {
            int randomNum = random.nextInt(n--);
            int temp = tiles2[randomNum];
            tiles2[randomNum] = tiles2[n];
            tiles2[n] = temp;
        }
    }

    private void loadNumbersUpper() {
        tiles2 = new int[25];
        for (int i = 0; i < 24; i++) {
            tiles2[i] = i + 1;
        }
    }

    /** Przypisanie przycisków to macierzy przycisków **/
    private void loadViewsUpper() {
        buttons2 = new Button[3][3];
        buttons2[0][0]= findViewById(R.id.button1);
        buttons2[0][1]= findViewById(R.id.button2);
        buttons2[0][2]= findViewById(R.id.button3);
        buttons2[1][0]= findViewById(R.id.button4);
        buttons2[1][1]= findViewById(R.id.button5);
        buttons2[1][2]= findViewById(R.id.button6);
        buttons2[2][0]= findViewById(R.id.button7);
        buttons2[2][1]= findViewById(R.id.button8);
        buttons2[2][2]= findViewById(R.id.button9);

        }
}