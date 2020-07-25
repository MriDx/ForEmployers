

package com.Creation.App;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class MyRecyclerViewAdapterView extends RecyclerView.Adapter<MyRecyclerViewAdapterView.DataObjectHolder> {
    private ArrayList<DataObject> mDataset;

    public MyRecyclerViewAdapterView(ArrayList<DataObject> myDataset) {
        mDataset = myDataset;

    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_attandance, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
     holder.name.setText(mDataset.get(position).getName());
       holder.t1.setText(mDataset.get(position).getT1());
        holder.t2.setText(mDataset.get(position).getT2());
        holder.t3.setText(mDataset.get(position).getT3());
        holder.t4.setText(mDataset.get(position).getT4());
        holder.t5.setText(mDataset.get(position).getT5());
        holder.t6.setText(mDataset.get(position).getT6());
        holder.t7.setText(mDataset.get(position).getT7());
        holder.t8.setText(mDataset.get(position).getT8());
        holder.t9.setText(mDataset.get(position).getT9());
        holder.t10.setText(mDataset.get(position).getT10());

    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
      TextView name;
     TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;

        public DataObjectHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textViewname);
           t1 = (TextView) itemView.findViewById(R.id.textView1);
            t2 = (TextView) itemView.findViewById(R.id.textView2);
            t3 = (TextView) itemView.findViewById(R.id.textView3);
            t4 = (TextView) itemView.findViewById(R.id.textView4);
            t5 = (TextView) itemView.findViewById(R.id.textView5);
            t6 = (TextView) itemView.findViewById(R.id.textView6);
            t7 = (TextView) itemView.findViewById(R.id.textView7);
            t8 = (TextView) itemView.findViewById(R.id.textView8);
            t9 = (TextView) itemView.findViewById(R.id.textView9);
            t10 = (TextView) itemView.findViewById(R.id.textView10);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

        }
    }



}





