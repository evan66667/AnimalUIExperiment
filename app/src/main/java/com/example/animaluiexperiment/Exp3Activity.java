package com.example.animaluiexperiment;

import android.os.Bundle;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Exp3Activity extends AppCompatActivity {
    private TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp3);

        tvTest = findViewById(R.id.tv_test);
        Button btnPop = findViewById(R.id.btn_pop);
        Button btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(v -> finish());

        btnPop.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(Exp3Activity.this, v);
            popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.size_10) {
                    tvTest.setTextSize(10);
                } else if (id == R.id.size_16) {
                    tvTest.setTextSize(16);
                } else if (id == R.id.size_20) {
                    tvTest.setTextSize(20);
                } else if (id == R.id.item_normal) {
                    Toast.makeText(Exp3Activity.this, "点击普通菜单项", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.color_red) {
                    tvTest.setTextColor(0xFFFF0000);
                } else if (id == R.id.color_black) {
                    tvTest.setTextColor(0xFF000000);
                }
                return true;
            });
            popupMenu.show();
        });
    }
}