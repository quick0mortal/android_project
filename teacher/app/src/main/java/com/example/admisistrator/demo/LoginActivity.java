package com.example.admisistrator.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText userName;
    EditText userPassword;
    Button loginButton;
    Button registerButton;

    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        userName = (EditText)findViewById(R.id.userName);
        userPassword = (EditText)findViewById(R.id.pwd);
        loginButton = (Button)findViewById(R.id.login);
        registerButton = (Button)findViewById(R.id.registerbtn);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(request).start();
            }
        });
    }

    Runnable request = new Runnable() {
        @Override
        public void run() {
            Message msg = new Message();
            msg.what = Constant.MSG_LOGIN;
            jsonObject = JsonUtil.getJSON(HttpUtil.doGet(UrlFactory.getLoginUrl(userName.getText().toString(), userPassword.getText().toString())));
            if (jsonObject != null) {
                handler.sendMessage(msg);
            }
        }
    };


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            if(msg.what == Constant.MSG_LOGIN){
                try {
                    Toast.makeText(LoginActivity.this, jsonObject.getString(Constant.RES_MESSAGE), Toast.LENGTH_SHORT).show();
                    System.err.print(jsonObject.getString(Constant.RES_CODE));
                    System.err.print(Constant.LOG_IN_SUCCESS);
                    if (jsonObject.getString(Constant.RES_CODE).equals(Constant.LOG_IN_SUCCESS)) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra(Constant.USERNAME, userName.getText().toString());
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
