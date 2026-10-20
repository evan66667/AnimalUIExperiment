package com.example.animaluiexperiment;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private List<Map<String,Object>> dataList;
    private SimpleAdapter adapter;
    private static final String CHANNEL_ID = "animal_notify";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotifyChannel();

        ListView listView = findViewById(R.id.lv_animal);
        dataList = new ArrayList<>();

        addItem("Lion",R.drawable.lion);
        addItem("Tiger",R.drawable.tiger);
        addItem("Monkey",R.drawable.monkey);
        addItem("Dog",R.drawable.dog);
        addItem("Cat",R.drawable.cat);
        addItem("Elephant",R.drawable.elephant);

        String[] from = {"name","img"};
        int[] to = {R.id.tv_name,R.id.iv_pic};
        adapter = new SimpleAdapter(this,dataList,R.layout.item_animal,from,to);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent,view,pos,id)->{
            String name = (String) dataList.get(pos).get("name");
            Toast.makeText(MainActivity.this,"选中："+name,Toast.LENGTH_SHORT).show();
            showNotification(name);
        });

        Button btnBackMain = findViewById(R.id.btn_back_main);
        btnBackMain.setOnClickListener(v -> finish());



    }

    private void addItem(String name,int imgId){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("img",imgId);
        dataList.add(map);
    }

    private void createNotifyChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel ch = new NotificationChannel(CHANNEL_ID,"动物通知",NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            nm.createNotificationChannel(ch);
        }
    }

    private void showNotification(String titleText){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(titleText)
                .setContentText("你点击了列表中的动物项")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1001,builder.build());
    }
}