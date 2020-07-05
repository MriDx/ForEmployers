package com.Creation.App;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmployeeViewHolder extends RecyclerView.ViewHolder {

    TextView list_name;


    public EmployeeViewHolder(@NonNull View itemView) {
        super(itemView);
        list_name = itemView.findViewById(R.id.list_name);
    }
}
