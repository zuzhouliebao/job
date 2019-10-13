package com.example.heartbeat.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heartbeat.R;
import com.example.heartbeat.utils.MD5Utils;
import com.example.heartbeat.utils.SharedUtils;
import com.example.heartbeat.utils.StatusUtils;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusUtils.setImmersionMode(this);
        setContentView(R.layout.activity_login);
        StatusUtils.initToolbar(this, "登录", true, false);  // 初始化toolbar

        initView();  // 初始化界面控件
        initData();  // 初始化传入的数据
    }
    private void initData() {
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        if (!TextUtils.isEmpty(username)) {
            etUsername.setText(username);
        }
    }

    private void initView() {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

        TextView tvRegister = findViewById(R.id.tv_register);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        TextView tvForgetPwd = findViewById(R.id.tv_forget);
        tvForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, FindPwdActivity.class);
                startActivity(intent);
            }
        });

        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });
    }

    private void login() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        password = MD5Utils.md5(password);
        String spPwd = SharedUtils.readValue(this, username);

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(spPwd)) {
            Toast.makeText(LoginActivity.this, "请先注册", Toast.LENGTH_SHORT).show();
        } else if (!spPwd.equals(password)) {
            Toast.makeText(LoginActivity.this, "输入的密码不正确", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            // 保存登录的状态和用户名
            SharedUtils.saveStrValue(this, "loginUser", username);
            SharedUtils.saveBooleanValue(this, "isLogin", true);

            // 返回到我的界面
            Intent intent = new Intent();
            intent.putExtra("isLogin", true);
            intent.putExtra("loginUser", username);
            setResult(RESULT_OK, intent);
            LoginActivity.this.finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String username = data.getStringExtra("username");
            etUsername.setText(username);
//                etUsername.setSelection(username.length());
        }
    }
}
