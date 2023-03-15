package com.example.gra;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RulesActivity extends AppCompatActivity {

    /** Przycisk otwierający menu główne */
    private Button buttonMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        buttonMenu = findViewById(R.id.button_menu_rules);
        /** Słuchacz otwierajcy menu główne */
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RulesActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

}