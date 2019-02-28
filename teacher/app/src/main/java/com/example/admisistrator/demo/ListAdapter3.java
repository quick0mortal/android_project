package com.example.admisistrator.demo;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListAdapter3 extends RecyclerView.Adapter<ListAdapter3.Holder> {
    private int itemCount = 1;
    private LayoutInflater inflater;
    private ArrayList<Project> data;

    public ListAdapter3(Context context, ArrayList<Project> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        itemCount = data.size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {


        return new Holder(inflater.inflate(R.layout.list_item3, parent, false));
    }

    @Override
    public void onBindViewHolder(ListAdapter3.Holder holder, int position) {
        holder.projectName.setText(data.get(position).getProjectName());
        holder.person1.setText(data.get(position).getName1());
        holder.person2.setText(data.get(position).getName2());
        holder.person3.setText(data.get(position).getName3());
        holder.person4.setText(data.get(position).getName4());
        holder.progressBar1.setProgress(Integer.parseInt(data.get(position).getName1Proccess()));
        holder.progressBar2.setProgress(Integer.parseInt(data.get(position).getName2Proccess()));
        holder.progressBar3.setProgress(Integer.parseInt(data.get(position).getName3Proccess()));
        holder.progressBar4.setProgress(Integer.parseInt(data.get(position).getName4Proccess()));
        holder.description.setText(data.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    static class Holder extends RecyclerView.ViewHolder {

        TextView projectName;
        TextView person1;
        TextView person2;
        TextView person3;
        TextView person4;
        ImageView person1_img;
        ImageView person2_img;
        ImageView person3_img;
        ImageView person4_img;
        ProgressBar progressBar1;
        ProgressBar progressBar2;
        ProgressBar progressBar3;
        ProgressBar progressBar4;
        EditText description;
        Button ok;

        public Holder(View itemView) {
            super(itemView);
            projectName = (TextView)itemView.findViewById(R.id.project_name);
            person1 = (TextView) itemView.findViewById(R.id.person1_name);
            person2 = (TextView) itemView.findViewById(R.id.person2_name);
            person3 = (TextView) itemView.findViewById(R.id.person3_name);
            person4 = (TextView) itemView.findViewById(R.id.person4_name);
            person1_img = (ImageView) itemView.findViewById(R.id.person1_img);
            person2_img = (ImageView) itemView.findViewById(R.id.person2_img);
            person3_img = (ImageView) itemView.findViewById(R.id.person3_img);
            person4_img = (ImageView) itemView.findViewById(R.id.person4_img);
            progressBar1 = (ProgressBar) itemView.findViewById(R.id.process1_bar);
            progressBar2 = (ProgressBar) itemView.findViewById(R.id.process2_bar);
            progressBar3 = (ProgressBar) itemView.findViewById(R.id.process3_bar);
            progressBar4 = (ProgressBar) itemView.findViewById(R.id.process4_bar);
            description = (EditText) itemView.findViewById(R.id.teacher_description);

        }
    }
}
