package com.example.admisistrator.demo;

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

public class NewTask extends AppCompatActivity {
    Button backButton;
    EditText projectNameEdit;
    EditText personName;
    EditText deadlineEdit;
    EditText taskEdit;
    Button okButton;

    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_task_layout);

        backButton = (Button) findViewById(R.id.back_to_main_button);
        projectNameEdit = (EditText) findViewById(R.id.project_name_for_task);
        personName = (EditText) findViewById(R.id.name);
        deadlineEdit = (EditText) findViewById(R.id.deadline_edit);
        taskEdit = (EditText) findViewById(R.id.task_edit);
        okButton = (Button) findViewById(R.id.task_ok_Button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewTask.this, MainActivity.class);
                startActivity(intent);
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(createTask).start();
            }
        });
    }

    Runnable createTask = new Runnable() {
        @Override
        public void run() {
            Message msg = new Message();
            msg.what = Constant.MSG_CREATE_TASK;
//            jsonObject = JsonUtil.getJSON(HttpUtil.doGet(UrlFactory.getEditTaskUrl(projectNameEdit.getText().toString(), getIntent().getStringExtra(Constant.USERNAME), taskEdit.getText().toString(), deadlineEdit.getText().toString())));
//            if (jsonObject != null) {
//                handler.sendMessage(msg);
//            } else {
//
//            }
            handler.sendMessage(msg);
        }
    };

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == Constant.MSG_CREATE_TASK){
//                try {
//                    if(jsonObject.getString(Constant.RES_CODE).equals(Constant.CREATE_PROJECT_SUCCESS)) {
//                        Toast.makeText(NewTask.this, jsonObject.getString(Constant.RES_MESSAGE), Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(NewTask.this, jsonObject.getString(Constant.RES_MESSAGE), Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                Toast.makeText(NewTask.this, "创建任务成功", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
