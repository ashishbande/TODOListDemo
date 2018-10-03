package com.abande.todolistdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private List<TodoModel> todoModelList;
    private View.OnLongClickListener longClickListener;

    public RecyclerViewAdapter(List<TodoModel> borrowModelList, View.OnLongClickListener longClickListener) {
        this.todoModelList = borrowModelList;
        this.longClickListener = longClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        TodoModel todoModel = todoModelList.get(position);
        holder.userIDView.setText(""+todoModel.getId());
        holder.descTextView.setText(""+todoModel.getDescription());
        holder.statusTextView.setText(""+todoModel.getStatus());
        holder.itemView.setTag(todoModel);
        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return todoModelList.size();
    }

    public void addItems(List<TodoModel> todoModels) {
        this.todoModelList = todoModels;
        notifyDataSetChanged();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView userIDView;
        private TextView descTextView;
        private TextView statusTextView;

        RecyclerViewHolder(View view) {
            super(view);
            userIDView = view.findViewById(R.id.itemTextView);
            descTextView = view.findViewById(R.id.nameTextView);
            statusTextView = view.findViewById(R.id.dateTextView);
        }
    }
}
