

package com.Creation.App;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class MyRecyclerViewAdapter1 extends RecyclerView.Adapter<MyRecyclerViewAdapter1.DataObjectHolder> {
    private ArrayList<DataObject1> mDataset1;
    private OnListItemClick onListItemClick;

    public MyRecyclerViewAdapter1(ArrayList<DataObject1> myDataset , OnListItemClick onListItemClick) {
        ArrayList<DataObject1> myDataset1 = null;
        mDataset1 = myDataset1;
        this.onListItemClick = onListItemClick;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row1, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.state.setText(mDataset1.get(position).getmText1());
        holder.total.setText(mDataset1.get(position).getmText2());
    }


    @Override
    public int getItemCount() {
        return mDataset1.size();
    }


    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView state;
        TextView total;

        public DataObjectHolder(View itemView) {
            super(itemView);
            state = (TextView) itemView.findViewById(R.id.nameret);
            total = (TextView) itemView.findViewById(R.id.UANid);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onListItemClick.onItemClick(getAdapterPosition());

        }
    }


    public interface OnListItemClick{
        void onItemClick(int position);
    }
}





