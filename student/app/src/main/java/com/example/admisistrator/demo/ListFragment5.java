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


public class ListFragment5 extends BaseFragment {
    EditText toPerson;
    RecyclerView recyclerView;
    EditText message;
    Button sendMessageButton;
    Button refreshButton;
    JSONObject jsonObjectSend;
    JSONObject jsonObjectRefresh;

    @Override
    int getLayoutId() {
        return R.layout.fragment5;
    }

    public ListFragment5(){
    }

    public static ListFragment5 newInstance(Bundle bundle){
        ListFragment5 listFragment5 = new ListFragment5();
        listFragment5.setArguments(bundle);
        return listFragment5;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView5);
        toPerson = (EditText)view.findViewById(R.id.to_person_edit);
        message = (EditText)view.findViewById(R.id.message_edit);
        sendMessageButton = (Button)view.findViewById(R.id.send_message_button);
        refreshButton = (Button)view.findViewById(R.id.refresh5_button);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(sendMessage).start();
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(refreshMessage).start();
            }
        });
    }


    Runnable sendMessage = new Runnable() {
        @Override
        public void run() {
            Message msg = new Message();
            msg.what = Constant.MSG_CREATE_MESSAGE;
            jsonObjectSend = JsonUtil.getJSON(HttpUtil.doGet(UrlFactory.getCreateMessageUrl(getArguments().getString(Constant.USERNAME), message.getText().toString(), toPerson.getText().toString())));
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
            msg.what = Constant.MSG_REFRESH_MESSAGE;
            jsonObjectRefresh = JsonUtil.getJSON(HttpUtil.doGet(UrlFactory.getRefreshMessageUrl(getArguments().getString(Constant.USERNAME))));
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
            if(msg.what == Constant.MSG_CREATE_MESSAGE){
                try {
                    if(jsonObjectSend.getString(Constant.RES_CODE).equals(Constant.CREATE_MESSAGE_SUCCESS)) {
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
            if(msg.what == Constant.MSG_REFRESH_MESSAGE){
                try {
                    if(jsonObjectRefresh.getString(Constant.RES_CODE).equals(Constant.REFRESH_MESSAGE_SUCCESS)) {
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
        ArrayList<MessageInfo> datas5 = new ArrayList<>();
        try{
            for(int i = 0; i < jsonObjectRefresh.getInt(Constant.MESSAGE_COUNT); i++) {
                datas5.add(new MessageInfo(jsonObjectRefresh.getJSONObject(Constant.MESSAGE_OBJECT + i).getString(Constant.FROM),
                        jsonObjectRefresh.getJSONObject(Constant.MESSAGE_OBJECT + i).getString(Constant.CONTENT), jsonObjectRefresh.getJSONObject(Constant.MESSAGE_OBJECT + i).getString(Constant.TO)));
            }
        } catch (JSONException jse) {
            jse.printStackTrace();
        }
        recyclerView.setAdapter(new ListAdapter5(getContext(), datas5));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
