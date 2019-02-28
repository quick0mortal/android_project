package com.example.admisistrator.demo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import static java.security.AccessController.getContext;


public class ListAdapter4 extends RecyclerView.Adapter<ListAdapter4.Holder> {
    private int itemCount = 1;
    private LayoutInflater inflater;
    private ArrayList<Task> data;
    private JSONObject jsonObject;


    public ListAdapter4(Context context, ArrayList<Task> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        itemCount = data.size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(R.layout.list_item4, parent, false));
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        holder.taskProject.setText(data.get(position).getProjectName());
        holder.taskDeadline.setText(data.get(position).getDeadline());
        holder.taskInfo.setText(data.get(position).getTaskContent());
        holder.finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        msg.what = Constant.MSG_FINISH_TASK;
                        jsonObject = JsonUtil.getJSON(HttpUtil.doGet(UrlFactory.getFinishTaskUrl(holder.taskProject.getText().toString(), holder.taskDeadline.getText().toString(), holder.taskInfo.getText().toString())));
                        if (jsonObject != null) {
                            try {
                                if(jsonObject.getString(Constant.RES_CODE).equals(Constant.REFRESH_TASK_OK_SUCCESS)) {
                                    System.out.print(jsonObject.getString(Constant.RES_MESSAGE));
                                } else {
                                    System.out.print(jsonObject.getString(Constant.RES_MESSAGE));
                                }
                            } catch (JSONException jes) {
                                jes.printStackTrace();
                            }
                        }
                    }
                };
            }
        });
    }






    @Override
    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    static class Holder extends RecyclerView.ViewHolder {

        TextView taskProject;
        TextView taskDeadline;
        TextView taskInfo;
        Button finishButton;

        public Holder(View itemView) {
            super(itemView);
            taskProject = (TextView) itemView.findViewById(R.id.task_of_project);
            taskDeadline = (TextView)itemView.findViewById(R.id.deadline_info);
            taskInfo = (TextView)itemView.findViewById(R.id.task_info);
            finishButton = (Button) itemView.findViewById(R.id.finishi_button);
        }
    }
}
