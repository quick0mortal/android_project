package com.example.admisistrator.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;



public class ListAdapter2 extends RecyclerView.Adapter<ListAdapter2.Holder> {
    private int itemCount = 1;
    private LayoutInflater inflater;
    private ArrayList<RequirementInfo> data;


    public ListAdapter2(Context context, ArrayList<RequirementInfo> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        itemCount = data.size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(R.layout.list_item2, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.userAccount.setText(data.get(position).getUsername());
        holder.description.setText(data.get(position).getRequirementMessage());

    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    static class Holder extends RecyclerView.ViewHolder {

        TextView userAccount;
        TextView description;

        public Holder(View itemView) {
            super(itemView);
            userAccount = (TextView)itemView.findViewById(R.id.title);
            description = (TextView)itemView.findViewById(R.id.description);
        }
    }
}
