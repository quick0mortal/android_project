package com.example.admisistrator.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class ListAdapter1 extends RecyclerView.Adapter<ListAdapter1.Holder> {
    private int itemCount = 1;
    private LayoutInflater inflater;
    private ArrayList<Information> datas;


    public ListAdapter1(Context context, ArrayList<Information> datas) {
        inflater = LayoutInflater.from(context);
        this.datas = datas;
        itemCount = datas.size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(R.layout.list_item1, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.infoTitle.setText(datas.get(position).getTitle());
        holder.description.setText(datas.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    static class Holder extends RecyclerView.ViewHolder {

        TextView infoTitle;
        TextView description;

        public Holder(View itemView) {
            super(itemView);
            infoTitle = (TextView)itemView.findViewById(R.id.info_title);
            description = (TextView)itemView.findViewById(R.id.info_description);
        }
    }
}
