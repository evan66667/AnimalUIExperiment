package com.example.animaluiexperiment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button b1 = findViewById(R.id.btn_exp1);
        Button b2 = findViewById(R.id.btn_exp2);
        Button b3 = findViewById(R.id.btn_exp3);
        Button b4 = findViewById(R.id.btn_exp4);
        Button b5 = findViewById(R.id.btn_exp5);

        b1.setOnClickListener(v->startActivity(new Intent(this,MainActivity.class)));
        b2.setOnClickListener(v->startActivity(new Intent(this,Exp2Activity.class)));
        b3.setOnClickListener(v->startActivity(new Intent(this,Exp3Activity.class)));
        b4.setOnClickListener(v->startActivity(new Intent(this,Exp4Activity.class)));
        b5.setOnClickListener(v->startActivity(new Intent(this,Exp5Activity.class)));
    }
}