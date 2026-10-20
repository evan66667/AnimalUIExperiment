package com.example.animaluiexperiment;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Exp5Activity extends AppCompatActivity {
    private List<Animal> animalList;
    private AnimalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp5);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        Button btnAdd = findViewById(R.id.btn_add);
        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        animalList = new ArrayList<>();
        //初始化动物数据
        animalList.add(new Animal("Lion",R.drawable.lion));
        animalList.add(new Animal("Tiger",R.drawable.tiger));
        animalList.add(new Animal("Monkey",R.drawable.monkey));

        adapter = new AnimalAdapter(animalList);
        recyclerView.setAdapter(adapter);

        //点击列表项，Toast显示动物名称
        adapter.setOnItemClickListener(pos -> {
            Animal item = animalList.get(pos);
            Toast.makeText(Exp5Activity.this,"点击："+item.name,Toast.LENGTH_SHORT).show();
        });

        //新增记录按钮
        btnAdd.setOnClickListener(v->{
            animalList.add(new Animal("Dog",R.drawable.dog));
            adapter.notifyItemInserted(animalList.size()-1);
        });
    }
}