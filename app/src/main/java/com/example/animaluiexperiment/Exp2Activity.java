package com.example.animaluiexperiment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Exp2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp2);

        Button btnShowDialog = findViewById(R.id.btn_show_dialog);
        btnShowDialog.setOnClickListener(v -> showLoginDialog());

        //实验2 返回按钮
        Button btnBackExp2 = findViewById(R.id.btn_back_exp2);
        btnBackExp2.setOnClickListener(v -> finish());
    }

    private void showLoginDialog(){
        //加载弹窗布局
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_login,null);
        EditText etUser = dialogView.findViewById(R.id.et_username);
        EditText etPwd = dialogView.findViewById(R.id.et_pwd);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登录")
                .setView(dialogView)
                .setPositiveButton("确认", (dialog, which) -> {
                    String username = etUser.getText().toString();
                    String pwd = etPwd.getText().toString();
                    Toast.makeText(Exp2Activity.this,"用户名："+username+"，密码："+pwd,Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("取消",null);
        builder.create().show();
    }
}