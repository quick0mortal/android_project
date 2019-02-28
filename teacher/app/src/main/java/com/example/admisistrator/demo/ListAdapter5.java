package com.example.admisistrator.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter5 extends RecyclerView.Adapter<ListAdapter5.Holder> {
    private int itemCount = 1;
    private LayoutInflater inflater;
    private ArrayList<MessageInfo> data;


    public ListAdapter5(Context context, ArrayList<MessageInfo> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        itemCount = data.size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(R.layout.list_item5, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.from.setText(data.get(position).getFrom());
        holder.content.setText(data.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    static class Holder extends RecyclerView.ViewHolder {

        TextView from;
        TextView content;

        public Holder(View itemView) {
            super(itemView);
            from = (TextView)itemView.findViewById(R.id.message_from);
            content = (TextView)itemView.findViewById(R.id.message_content);
        }
    }
}
