package com.example.animaluiexperiment;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Exp4Activity extends androidx.appcompat.app.AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> dataList;
    Set<Integer> selectedPosSet = new HashSet<>();
    ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp4);

        listView = findViewById(R.id.lv_exp4);
        Button btnBack = findViewById(R.id.btn_back_exp4);
        btnBack.setOnClickListener(v -> finish());

        // 测试数据 One Two Three Four Five
        dataList = new ArrayList<>();
        dataList.add("One");
        dataList.add("Two");
        dataList.add("Three");
        dataList.add("Four");
        dataList.add("Five");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);

        // 长按触发ActionMode
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            if(actionMode == null){
                actionMode = startActionMode(mActionModeCallback);
            }
            toggleSelect(position);
            return true;
        });

        // 单击：多选模式下切换选中状态
        listView.setOnItemClickListener((parent, view, position, id) -> {
            if(actionMode != null){
                toggleSelect(position);
            }
        });
    }

    // 选中/取消选中，并且更新标题文字
    private void toggleSelect(int pos){
        if(selectedPosSet.contains(pos)){
            selectedPosSet.remove(pos);
        }else{
            selectedPosSet.add(pos);
        }
        // 更新顶部标题：X selected
        if(actionMode != null){
            actionMode.setTitle(selectedPosSet.size() + " selected");
        }
    }

    // ActionMode回调
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.action_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if(item.getItemId() == R.id.menu_delete){
                // 从后往前删，防止索引错乱
                ArrayList<Integer> sorted = new ArrayList<>(selectedPosSet);
                sorted.sort((a,b)->Integer.compare(b,a));
                for(int p : sorted){
                    dataList.remove(p);
                }
                adapter.notifyDataSetChanged();
                mode.finish(); // 删除完成，关闭ActionMode
                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
            selectedPosSet.clear();
        }
    };
}