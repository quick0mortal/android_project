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

public class ListFragment3 extends BaseFragment {

    RecyclerView recyclerView;
    Button newProjectButton;
    Button refreshProjectButton;
    JSONObject jsonObject;


    @Override
    int getLayoutId() {
        return R.layout.fragment3;
    }

    public ListFragment3(){
    }

    public static ListFragment3 newInstance(Bundle bundle){
        ListFragment3 listFragment3 = new ListFragment3();
        listFragment3.setArguments(bundle);
        return listFragment3;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView3);
        newProjectButton = (Button)view.findViewById(R.id.new_project_button);
        refreshProjectButton = (Button)view.findViewById(R.id.refresh3_button);

        refreshProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(refreshProject).start();
            }
        });

        newProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewProject.class);
                startActivity(intent);
            }
        });
    }


    // no
    Runnable refreshProject = new Runnable() {
        @Override
        public void run() {
            Message msg = new Message();
            msg.what = Constant.MSG_REFRESH_PROJECT;
            jsonObject = JsonUtil.getJSON(HttpUtil.doGet(UrlFactory.getProjectInfoUrl(getArguments().getString(Constant.USERNAME))));
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
            if(msg.what == Constant.MSG_REFRESH_PROJECT){
                try {
                    if(jsonObject.getString(Constant.RES_CODE).equals(Constant.REFRESH_PROJECT_SUCCESS)) {
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

    //klj;lkj;lkj;lkj;lkj;j
    private void setContent() {
        ArrayList<Project> datas2 = new ArrayList<>();
        try{
            for(int i = 0; i < jsonObject.getInt(Constant.PROJECT_COUNT); i++) {
                datas2.add(new Project(jsonObject.getJSONObject(Constant.PROJECT_OBJECT + i).getString(Constant.PROJECT_NAME),
                        jsonObject.getJSONObject(Constant.PROJECT_OBJECT + i).getString(Constant.NAME1),
                        jsonObject.getJSONObject(Constant.PROJECT_OBJECT + i).getString(Constant.NAME2),
                        jsonObject.getJSONObject(Constant.PROJECT_OBJECT + i).getString(Constant.NAME3),
                        jsonObject.getJSONObject(Constant.PROJECT_OBJECT + i).getString(Constant.NAME4),
                        jsonObject.getJSONObject(Constant.PROJECT_OBJECT + i).getString(Constant.COMMENT)));
            }
        } catch (JSONException jse) {
            jse.printStackTrace();
        }
        recyclerView.setAdapter(new ListAdapter3(getContext(), datas2));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
