package com.Creation.App;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployerListAdapter extends RecyclerView.Adapter<EmployerListAdapter.ViewHolder> {

    public List<Employyes> employyesList;

    public EmployerListAdapter(List<Employyes> employyesList) {
        this.employyesList = employyesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_employeeitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.mlistName.setText(employyesList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return employyesList == null ? 0 : employyesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View mview;

        public TextView mlistName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mview = itemView;
            mlistName = (TextView) mview.findViewById(R.id.list_name);
        }
    }
}
