
package com.Creation.App.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Creation.App.DataObject;
import com.Creation.App.MyRecyclerViewAdapterView;
import com.Creation.App.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Monthly extends Fragment {

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId, UAN_id;
    TextView employeeRetrive;
    //Attendance View
    List<String> name, d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, key, UAN_i, date_data, d11, d12, d13, d14, d15, d16, d17, d18, d19, d20, d21, d22, d23, d24, d25, d26, d27, d28, d29, d30, d31;
    String UserId, UAN;
    // Attendance View
    String[][] data = new String[4][100];
    int index;
    Button button_go;
    boolean go;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public Monthly() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_monthly, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        //do your work here


        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        employeeRetrive = view.findViewById(R.id.employee_retrive_name);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        button_go = view.findViewById(R.id.button_go);

        fAuth = FirebaseAuth.getInstance();

        fStore = FirebaseFirestore.getInstance();

        userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();

        go = false;
        button_go.setVisibility(View.INVISIBLE);



        key = new ArrayList<>();
        name = new ArrayList<>();
        d1 = new ArrayList<>();
        d2 = new ArrayList<>();
        d3 = new ArrayList<>();
        d4 = new ArrayList<>();
        d5 = new ArrayList<>();
        d6 = new ArrayList<>();
        d7 = new ArrayList<>();
        d8 = new ArrayList<>();
        d9 = new ArrayList<>();
        d10 = new ArrayList<>();
        d11 = new ArrayList<>();
        d12 = new ArrayList<>();
        d13 = new ArrayList<>();
        d14 = new ArrayList<>();
        d15 = new ArrayList<>();
        d16 = new ArrayList<>();
        d17 = new ArrayList<>();
        d18 = new ArrayList<>();
        d19 = new ArrayList<>();
        d20 = new ArrayList<>();
        d21 = new ArrayList<>();
        d22 = new ArrayList<>();
        d23 = new ArrayList<>();
        d24 = new ArrayList<>();
        d25 = new ArrayList<>();
        d26 = new ArrayList<>();
        d27 = new ArrayList<>();
        d28 = new ArrayList<>();
        d29 = new ArrayList<>();
        d30 = new ArrayList<>();
        d31 = new ArrayList<>();

        UAN_i = new ArrayList<>();
        date_data = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        SimpleDateFormat mon = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat ye = new SimpleDateFormat("yyyy", Locale.getDefault());

        final String date = sd.format(cal.getTime());
        final String month = mon.format(cal.getTime());

        final String year = ye.format(cal.getTime());

        for (int i = 0; i < 31; i++) {
            date_data.add(i, (i + 1) + "-" + month + "-" + year);
            // Toast.makeText(this, (i + 1) +"-"+ month +"-"+year, Toast.LENGTH_SHORT).show();
        }

        work();


        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAdapter = new MyRecyclerViewAdapterView(getDataSet());
                mRecyclerView.setAdapter(mAdapter);
            }
        });
    }

    private void work() {

        //  Toast.makeText(this, month, Toast.LENGTH_SHORT).show();
        FirebaseFirestore.getInstance().collection("Users Detail/" + userId + "/Employee List").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                        name.add(document.getString("Full Name"));
                        UAN_i.add(document.getString("UAN"));
                        final String id = document.getString("UAN");
                        //  Toast.makeText(Monthly.this, id, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(Monthly.this, document.getString("UAN").toString(), Toast.LENGTH_SHORT).show();
                        //search
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(0).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d1.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d1.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(0).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error!= null){
                                    //Nothing
                                }else{
                                    if (value.getString(id) != null) {
                                        d1.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d1.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }

                                }

                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(1).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d2.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d2.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }


                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(2).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d3.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d3.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }


                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(3).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d4.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d4.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(4).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d5.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d5.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(5).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d6.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d6.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(6).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d7.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d7.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(7).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d8.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d8.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(8).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d9.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d9.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(9).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                                if (error != null){
                                    //nothing
                                }else{
                                    if (error != null){
                                        //nothing
                                    }else {
                                        if (value.getString(id) != null) {
                                            d10.add(value.getString(id));
                                            //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                        } else {
                                            d10.add(" ");
                                            //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                        }
                                        // button_go.setVisibility(View.VISIBLE);
                                    }
                                }
                            }


                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(10).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d11.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d11.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(11).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d12.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d12.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(12).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d13.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d13.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(13).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d14.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d14.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(14).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d15.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d15.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(15).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d16.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d16.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(16).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d17.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d17.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(17).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d18.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d18.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(18).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d19.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d19.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(19).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d20.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d20.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(20).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d21.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d21.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(21).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d22.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d22.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(22).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                                if (error != null){
                                    Log.d("TAG", "Nothing");
                                }else {
                                    if (value.getString(id) != null) {
                                        d23.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d23.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                    // button_go.setVisibility(View.VISIBLE);
                                }

                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(23).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d24.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d24.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(24).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d25.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d25.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(25).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d26.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d26.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(26).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d27.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d27.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(27).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d28.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d28.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(28).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d29.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d29.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(29).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d30.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d30.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(30).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (error != null){

                                }else{
                                    if (value.getString(id) != null) {
                                        d31.add(value.getString(id));
                                        //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                    } else {
                                        d31.add(" ");
                                        //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                button_go.setVisibility(View.VISIBLE);
                            }

                        });

                    }


                    //  Toast.makeText(Employees.this, rf_id.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                    Toast.makeText(getContext(), "Error getting documents", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private ArrayList<DataObject> getDataSet() {
        ArrayList results = new ArrayList<DataObject>();

        for (int index = 0; index < name.size(); index++) {

            DataObject obj1 = new DataObject(name.get(index), d1.get(index), d2.get(index), d3.get(index), d4.get(index), d5.get(index), d6.get(index), d7.get(index), d8.get(index), d9.get(index), d10.get(index), d11.get(index), d12.get(index), d13.get(index), d14.get(index), d15.get(index), d16.get(index), d17.get(index), d18.get(index), d19.get(index), d20.get(index), d21.get(index), d22.get(index), d23.get(index), d24.get(index), d25.get(index), d26.get(index), d27.get(index), d28.get(index), d29.get(index), d30.get(index), d31.get(index));

            results.add(index, obj1);

        }
        return results;
    }
}
