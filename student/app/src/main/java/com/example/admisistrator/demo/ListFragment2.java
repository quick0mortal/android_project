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
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListFragment2 extends BaseFragment {
    EditText username;
    RecyclerView recyclerView;
    EditText requestEdit;
    Button sendRequestButton;
    Button refreshButton;
    JSONObject jsonObjectSend;
    JSONObject jsonObjectRefresh;

    @Override
    int getLayoutId() {
        return R.layout.fragment2;
    }

    public ListFragment2(){
    }

    public static ListFragment2 newInstance(Bundle bundle){
        ListFragment2 listFragment2 = new ListFragment2();
        listFragment2.setArguments(bundle);
        return listFragment2;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView2);
        requestEdit = (EditText)view.findViewById(R.id.request_team_edit);
        sendRequestButton = (Button)view.findViewById(R.id.send_request_team_button);
        refreshButton = (Button)view.findViewById(R.id.refresh2_button);

        sendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(sendRequestMessage).start();
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(refreshMessage).start();
            }
        });
    }


    Runnable sendRequestMessage = new Runnable() {
        @Override
        public void run() {
            Message msg = new Message();
            msg.what = Constant.MSG_SEND_REQUREMENT;
            jsonObjectSend = JsonUtil.getJSON(HttpUtil.doGet(UrlFactory.getSendRequirementUrl(getArguments().getString(Constant.USERNAME), requestEdit.getText().toString())));
            if (jsonObjectSend != null) {
                handler.sendMessage(msg);
            } else {

            }
        }
    };

    Runnable refreshMessage = new Runnable() {
        @Override
        public void run() {
            Message msg = new Message();
            msg.what = Constant.MSG_REFRESH_INFO;
            jsonObjectRefresh = JsonUtil.getJSON(HttpUtil.doGet(UrlFactory.getRequirementInfoUrl("1")));
            System.err.println(jsonObjectRefresh.toString());
            if (jsonObjectRefresh != null) {
                handlerRefresh.sendMessage(msg);
            } else {

            }
        }
    };




    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == Constant.MSG_SEND_REQUREMENT){
                try {
                    if(jsonObjectSend.getString(Constant.RES_CODE).equals(Constant.SEND_REQUIREMENT_SUCCESS)) {
                        Toast.makeText(getContext(), jsonObjectSend.getString(Constant.RES_MESSAGE), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), jsonObjectSend.getString(Constant.RES_MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    Handler handlerRefresh = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            if(msg.what == Constant.MSG_REFRESH_INFO){
                try {
                    if(jsonObjectRefresh.getString(Constant.RES_CODE).equals(Constant.REFRESH_INFORMATION_SUCCESS)) {
                        Toast.makeText(getContext(), jsonObjectRefresh.getString(Constant.RES_MESSAGE), Toast.LENGTH_SHORT).show();
                        setContent();
                    } else {
                        Toast.makeText(getContext(), jsonObjectRefresh.getString(Constant.RES_MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private void setContent() {
        ArrayList<RequirementInfo> datas1 = new ArrayList<>();
        try{
            for(int i = 0; i < jsonObjectRefresh.getInt(Constant.REQUIREMENT_COUNT); i++) {
                datas1.add(new RequirementInfo(jsonObjectRefresh.getJSONObject(Constant.REQUIREMENT_OBJECT + i).getString(Constant.USERNAME),
                        jsonObjectRefresh.getJSONObject(Constant.REQUIREMENT_OBJECT + i).getString(Constant.REQUIREMENT_MESSAGE)));
            }
        } catch (JSONException jse) {
            jse.printStackTrace();
        }
        recyclerView.setAdapter(new ListAdapter2(getContext(), datas1));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
