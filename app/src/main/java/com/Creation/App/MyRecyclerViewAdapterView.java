

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
        holder.t11.setText(mDataset.get(position).getT11());

        holder.t12.setText(mDataset.get(position).getT12());
        holder.t13.setText(mDataset.get(position).getT13());
        holder.t14.setText(mDataset.get(position).getT14());
        holder.t15.setText(mDataset.get(position).getT15());
        holder.t16.setText(mDataset.get(position).getT16());
        holder.t17.setText(mDataset.get(position).getT17());
        holder.t18.setText(mDataset.get(position).getT18());
        holder.t19.setText(mDataset.get(position).getT19());
        holder.t20.setText(mDataset.get(position).getT20());
        holder.t21.setText(mDataset.get(position).getT21());
        holder.t22.setText(mDataset.get(position).getT22());
        holder.t23.setText(mDataset.get(position).getT23());
        holder.t24.setText(mDataset.get(position).getT24());
        holder.t25.setText(mDataset.get(position).getT25());
        holder.t26.setText(mDataset.get(position).getT26());
        holder.t27.setText(mDataset.get(position).getT27());
        holder.t28.setText(mDataset.get(position).getT28());
        holder.t29.setText(mDataset.get(position).getT29());
        holder.t30.setText(mDataset.get(position).getT30());
        holder.t31.setText(mDataset.get(position).getT31());

    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
      TextView name;
     TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20,t21,t22,t23,t24,t25,t26,t27,t28,t29,t30,t31;

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
            t11 = (TextView) itemView.findViewById(R.id.textView11);

            t12 = (TextView) itemView.findViewById(R.id.textView12);
            t13 = (TextView) itemView.findViewById(R.id.textView13);
            t14 = (TextView) itemView.findViewById(R.id.textView14);
            t15 = (TextView) itemView.findViewById(R.id.textView15);
            t16 = (TextView) itemView.findViewById(R.id.textView16);
            t17 = (TextView) itemView.findViewById(R.id.textView17);
            t18 = (TextView) itemView.findViewById(R.id.textView18);
            t19 = (TextView) itemView.findViewById(R.id.textView19);
            t20 = (TextView) itemView.findViewById(R.id.textView20);
            t21 = (TextView) itemView.findViewById(R.id.textView21);
            t22 = (TextView) itemView.findViewById(R.id.textView22);
            t23 = (TextView) itemView.findViewById(R.id.textView23);
            t24 = (TextView) itemView.findViewById(R.id.textView24);
            t25 = (TextView) itemView.findViewById(R.id.textView25);
            t26 = (TextView) itemView.findViewById(R.id.textView26);
            t27 = (TextView) itemView.findViewById(R.id.textView27);
            t27 = (TextView) itemView.findViewById(R.id.textView27);
            t28 = (TextView) itemView.findViewById(R.id.textView28);
            t29 = (TextView) itemView.findViewById(R.id.textView29);

            t30 = (TextView) itemView.findViewById(R.id.textView30);

            t31 = (TextView) itemView.findViewById(R.id.textView31);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

        }
    }



}





