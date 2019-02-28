package com.example.admisistrator.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class NewProject extends AppCompatActivity {

    Button backButton;

    EditText projectNameEdit;
    EditText name1;
    EditText name2;
    EditText name3;
    EditText name4;
    Button okButton;

    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_team_layout);

        backButton = (Button) findViewById(R.id.back_to_main_button);
        projectNameEdit = (EditText) findViewById(R.id.project_name_edit);
        name1 = (EditText) findViewById(R.id.name1_eidt);
        name2 = (EditText) findViewById(R.id.name2_eidt);
        name3 = (EditText) findViewById(R.id.name3_eidt);
        name4 = (EditText) findViewById(R.id.name4_eidt);
        okButton = (Button) findViewById(R.id.ok_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewProject.this, MainActivity.class);
                startActivity(intent);
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new Thread(createProject).start();
                if(name3.getText().toString().equals("a")){
                    Toast.makeText(NewProject.this, "输入的队员不存在", Toast.LENGTH_SHORT).show();
                } else if (name4.getText().toString().equals("aa")){
                    Toast.makeText(NewProject.this, "输入的老师不存在", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NewProject.this, "创建队伍成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    Runnable createProject = new Runnable() {
        @Override
        public void run() {
            Message msg = new Message();
            msg.what = Constant.MSG_CREATE_PROJECT;
            jsonObject = JsonUtil.getJSON(HttpUtil.doGet(UrlFactory.getCreateProjectUrl(projectNameEdit.getText().toString(),
                    name1.getText().toString(), name2.getText().toString(), name3.getText().toString(), name4.getText().toString())));
            if (jsonObject != null) {
                handler.sendMessage(msg);
            } else {

            }
        }
    };

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == Constant.MSG_CREATE_PROJECT){
                try {
                    if(jsonObject.getString(Constant.RES_CODE).equals(Constant.CREATE_PROJECT_SUCCESS)) {
                        Toast.makeText(NewProject.this, jsonObject.getString(Constant.RES_MESSAGE), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(NewProject.this, jsonObject.getString(Constant.RES_MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
