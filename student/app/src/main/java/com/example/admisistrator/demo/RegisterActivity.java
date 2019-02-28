package com.example.admisistrator.demo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button signup;
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_layout);

        username = (EditText)findViewById(R.id.etSgAccount);
        password = (EditText)findViewById(R.id.etSgPassword);
        signup = (Button)findViewById(R.id.btnSign);
    }



    // 发起HTTP请求
    public void onRegister(View v){
        new Thread(request).start();
    }

    Runnable request = new Runnable() {
        @Override
        public void run() {
            Message msg = new Message();
            msg.what = Constant.MSG_REGISTER;
            jsonObject = JsonUtil.getJSON(HttpUtil.doGet(UrlFactory.getRegiserUrl(username.getText().toString(), password.getText().toString())));
            if (jsonObject != null) {
                handler.sendMessage(msg);
            }
        }
    };


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            if(msg.what == Constant.MSG_REGISTER){
                try {
                    Toast.makeText(RegisterActivity.this, jsonObject.getString(Constant.RES_MESSAGE), Toast.LENGTH_SHORT).show();
                    if (jsonObject.getString(Constant.RES_CODE).equals(Constant.REGISTER_SUCCESS)) {
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
