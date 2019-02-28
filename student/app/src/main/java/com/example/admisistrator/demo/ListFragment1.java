package com.example.admisistrator.demo;

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

public class ListFragment1 extends BaseFragment {
    RecyclerView recyclerView1;
    Button refreshButton;
    JSONObject jsonObject;

    @Override
    int getLayoutId() {
        return R.layout.fragment1;
    }

    public ListFragment1(){
    }

    public static ListFragment1 newInstance(Bundle bundle){
        ListFragment1 listFragment1 = new ListFragment1();
        listFragment1.setArguments(bundle);
        return listFragment1;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView1 = (RecyclerView)view.findViewById(R.id.recyclerView1);
        refreshButton = (Button)view.findViewById(R.id.refresh1_button);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(refreshInfo).start();
            }
        });
    }


    Runnable refreshInfo = new Runnable() {
        @Override
        public void run() {
            Message msg = new Message();
            msg.what = Constant.MSG_REFRESH_INFO;
            jsonObject = JsonUtil.getJSON(HttpUtil.doGet(UrlFactory.getInformationUrl("1")));
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
            if(msg.what == Constant.MSG_REFRESH_INFO){
                try {
                    if(jsonObject.getString(Constant.RES_CODE).equals(Constant.REFRESH_INFORMATION_SUCCESS)) {
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
        ArrayList<Information> data = new ArrayList<>();
        try{
            for(int i = 0; i < jsonObject.getInt(Constant.INFORMATION_COUNT); i++) {
                data.add(new Information(jsonObject.getJSONObject(Constant.INFORMATION_OBJECT + i).getString(Constant.INFORMATION_TITLE),
                        jsonObject.getJSONObject(Constant.INFORMATION_OBJECT + i).getString(Constant.INFORMATION_MESSAGE)));
            }
        } catch (JSONException jse) {
            jse.printStackTrace();
        }
        recyclerView1.setAdapter(new ListAdapter1(getContext(), data));
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
    }
}
