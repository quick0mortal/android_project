package com.example.admisistrator.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListFragment4 extends BaseFragment {

    RecyclerView recyclerView4;
    Button createButton;
    Button refreshButton;
    JSONObject jsonObject;

    @Override
    int getLayoutId() {
        return R.layout.fragment4;
    }

    public ListFragment4(){
    }

    public static ListFragment4 newInstance(Bundle bundle){
        ListFragment4 listFragment4 = new ListFragment4();
        listFragment4.setArguments(bundle);
        return listFragment4;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView4 = (RecyclerView)view.findViewById(R.id.recyclerView4);
        refreshButton = (Button)view.findViewById(R.id.refresh4_button);
        createButton = (Button)view.findViewById(R.id.new_task_button);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(refreshTaskInfo).start();
            }
        });
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NewTask.class);
                i.putExtra(Constant.USERNAME, getArguments().getString(Constant.USERNAME));
                startActivity(i);
            }
        });
    }


    Runnable refreshTaskInfo = new Runnable() {
        @Override
        public void run() {
            Message msg = new Message();
            msg.what = Constant.MSG_REFRESH_TASK;
            jsonObject = JsonUtil.getJSON(HttpUtil.doGet(UrlFactory.getTaskInfoUrl(getArguments().getString(Constant.USERNAME))));
            if (jsonObject != null) {
                handler.sendMessage(msg);
            } else {

            }
        }
    };



    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            if(msg.what == Constant.MSG_REFRESH_TASK){
                try {
                    if(jsonObject.getString(Constant.RES_CODE).equals(Constant.REFRESH_TASK_SUCCESS)) {
                        Toast.makeText(getContext(), jsonObject.getString(Constant.RES_MESSAGE), Toast.LENGTH_SHORT).show();
                        setContent();
                    } else {
                        Toast.makeText(getContext(), jsonObject.getString(Constant.RES_MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private void setContent() {
        ArrayList<Task> datas4 = new ArrayList<>();
        try{
            for(int i = 0; i < jsonObject.getInt(Constant.TASK_COUNT); i++) {
                datas4.add(new Task(jsonObject.getJSONObject(Constant.TASK_OBJECT + i).getString(Constant.PROJECT_NAME),
                        jsonObject.getJSONObject(Constant.TASK_OBJECT + i).getString(Constant.PERSON_NAME),
                        jsonObject.getJSONObject(Constant.TASK_OBJECT + i).getString(Constant.TASK_CONTENT),
                        jsonObject.getJSONObject(Constant.TASK_OBJECT + i).getString(Constant.DEADLINE),
                        jsonObject.getJSONObject(Constant.TASK_OBJECT + i).getString(Constant.IS_FINISH)));
            }
        } catch (JSONException jse) {
            jse.printStackTrace();
        }
        recyclerView4.setAdapter(new ListAdapter4(getContext(), datas4));
        recyclerView4.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView4.setItemAnimator(new DefaultItemAnimator());
    }
}
