package com.Creation.App.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Creation.App.DataObject;
import com.Creation.App.DataObject1;
import com.Creation.App.MyRecyclerViewAdapter;
import com.Creation.App.MyRecyclerViewAdapter1;
import com.Creation.App.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("ALL")
public class Employees extends Fragment implements MyRecyclerViewAdapter.OnListItemClick {

    FirebaseAuth fAuth;

    FirebaseFirestore fStore;
    String userId, UAN_id;
    TextView employeeRetrive;
    List<String> UAN_i, name, User_Name, phone, password;
    String UserId, UAN;
    String[][] data = new String[4][100];
    private RecyclerView mRecyclerView, mRecyclerView2;
    private RecyclerView.Adapter mAdapter, mAdapter1;
    private RecyclerView.LayoutManager mLayoutManager;


    public Employees() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_employees, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        //do your work here

        work();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        employeeRetrive = view.findViewById(R.id.employee_retrive_name);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
       // final TextView tv_date_last_updated = view.findViewById(R.id.tv_last_updated);// Last Updated Function.


        fAuth = FirebaseAuth.getInstance();

        fStore = FirebaseFirestore.getInstance();



       // userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
        //FirebaseFirestore.getInstance().collection("Users Detail")
           //     .document(userId).addSnapshotListener(new EventListener<DocumentSnapshot>() {
           // @Override
          //  public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
            //    if (error!=null){
             //       // Do nothing
             //   }else{
              //      if (value.getString("Last Updated") != null)
               //         tv_date_last_updated.setText("Last Updated : " + value.getString("Last Updated"));
               //     else
                 //       tv_date_last_updated.setText("Last Updated : " + "NA");
               // }

           // }
       // });


        work();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                LayoutInflater factory = LayoutInflater.from(getContext());
                final View textEntryView = factory.inflate(R.layout.text_entry, null);


                final EditText inputname = (EditText) textEntryView.findViewById(R.id.EditeSupname);
                final EditText inputUAN = (EditText) textEntryView.findViewById(R.id.EdittUAN);
                final EditText inputUser_Name = (EditText) textEntryView.findViewById(R.id.EditeSupUser);
                final EditText inputphone = (EditText) textEntryView.findViewById(R.id.Editemplphone);
                final EditText inputpassword = (EditText) textEntryView.findViewById(R.id.EditUserPassword);
                Button btn_save = (Button) textEntryView.findViewById(R.id.btn_save);

                inputname.setText(null);

                //ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getContext(),
                  //      android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.Desginations));
                //myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //mySpinner.setAdapter(myAdapter);

                final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

                alert.setView(textEntryView);
                final AlertDialog testDialog = alert.create();
                testDialog.show();
                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = inputname.getText().toString().trim();
                        String UAN_id = inputUAN.getText().toString();
                        String User_Name = inputUser_Name.getText().toString();
                        String phone = inputphone.getText().toString();
                        String password = inputpassword.getText().toString();
                        //Toast.makeText(Employees.this, name, Toast.LENGTH_SHORT).show();
                        if (TextUtils.isEmpty(name)) {
                            inputname.setError("Mandatory");

                        } else if (TextUtils.isEmpty(UAN_id)) {
                            inputUAN.setError("Mandatory");

                        } else if (UAN_id.length() != 12) {
                            inputUAN.setError("UAN_ID will be 12 letter");
                        } else if (TextUtils.isEmpty(User_Name)) {
                            inputUser_Name.setError("Mandatory");
                        } else if (User_Name.length() <= 4){
                            inputUser_Name.setError("Please Enter the Above 4 Digit");
                        }else if (TextUtils.isEmpty(password)){
                            inputpassword.setError("Password must be added");
                        }else if (password.length() <= 6){
                            inputpassword.setError("Password must be 6 Character Long");
                        }
                        else {
                            //   Toast.makeText(Employees.this, "Done", Toast.LENGTH_SHORT).show();

                            userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
                            DocumentReference documentReference = fStore.collection("Users Detail").document(userId).collection("Supervisor List").document(User_Name);
                            Map<String, Object> user = new HashMap<>();
                            user.put("Supervisor Name", name);
                            user.put("UAN", UAN_id);
                            user.put("User ID", User_Name);
                            user.put("Phone", phone);
                            user.put("Password", password);

                            final String finalName = name;
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getContext(), "Supervisor" +finalName + " is added.", Toast.LENGTH_SHORT).show();
                                    work();

                                }
                            });


                           // DocumentReference documentReference1 = fStore.collection("Employees List").document(UAN_id);
                            //Map<String, Object> user1 = new HashMap<>();
                           // user1.put("Full Name", name);
                           // user1.put("UAN", UAN_id);
                           // user1.put("Desgination", User_Name);
                           // user1.put("Phone", phone);
                           // documentReference1.set(user1);

                            testDialog.dismiss();
                        }


                    }


                });


            }
        });
    }

    private void work() {

        FirebaseFirestore.getInstance().collection("Users Detail/" + userId + "/Supervisor List").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    UAN_i = new ArrayList<>();
                    name = new ArrayList<>();
                    User_Name = new ArrayList<>();
                    phone = new ArrayList<>();
                    for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                        name.add(document.getString("Supervisor Name"));
                        UAN_i.add(document.getString("UAN"));
                        User_Name.add(document.getString("User ID"));
                        phone.add(document.getString("Phone"));

                    }

                    mAdapter = new MyRecyclerViewAdapter(getDataSet(), com.Creation.App.fragment.Employees.this);
                    mRecyclerView.setAdapter(mAdapter);
                    Log.d("TAG", User_Name.toString());

                    //  Toast.makeText(Employees.this, rf_id.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                    Toast.makeText(getContext(), "Error getting documents", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private ArrayList<DataObject> getDataSet() {
        ArrayList results = new ArrayList<DataObject1>();


        for (int index = 0; index < name.size(); index++) {
            DataObject obj1 = new DataObject(name.get(index), User_Name.get(index));
            results.add(index, obj1);
            data[0][index] = name.get(index);
            data[1][index] = UAN_i.get(index);
            //Here the error is showing that this line is not valid.
            data[2][index] = User_Name.get(index);
            data[3][index] = phone.get(index);

        }
        return results;
    }

    @Override
    public void onItemClick(final int position) {

        Log.d("ITEM CLICKED", "Clicked an item: " + position);
        //Toast.makeText(this, "Name="+ data[0][position]+","+" RF Id="+data[1][position], Toast.LENGTH_SHORT).show();

        LayoutInflater factory = LayoutInflater.from(getContext());
        View supEmployee = factory.inflate(R.layout.sup_employee, null);
        TextView textView = (TextView) supEmployee.findViewById(R.id.getUserName);
        textView.setText(data[2][position]);

        AlertDialog.Builder alert = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        alert.setView(supEmployee).show();

        //Here we want to pass to another activity by also passing the User ID.

        FirebaseFirestore.getInstance().collection("Users Detail/" + userId + "/Supervisor List" + data[2][position]).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    UAN_i = new ArrayList<>();
                    name = new ArrayList<>();
                    User_Name = new ArrayList<>();
                    phone = new ArrayList<>();
                    for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                        name.add(document.getString("Supervisor Name"));
                        UAN_i.add(document.getString("UAN"));
                        User_Name.add(document.getString("User ID"));
                        phone.add(document.getString("Phone"));

                    }

                    mAdapter1 = new MyRecyclerViewAdapter1(getDataSet1(), (MyRecyclerViewAdapter1.OnListItemClick) Employees.this);
                    mRecyclerView.setAdapter(mAdapter1);
                    Log.d("TAG", User_Name.toString());

                    //  Toast.makeText(Employees.this, rf_id.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                    Toast.makeText(getContext(), "Error getting documents", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private ArrayList<DataObject1> getDataSet1() {
        ArrayList results = new ArrayList<DataObject1>();


        for (int index = 0; index < name.size(); index++) {
            DataObject1 obj2 = new DataObject1(name.get(index), User_Name.get(index));
            results.add(index, obj2);
            data[0][index] = name.get(index);
            data[1][index] = UAN_i.get(index);
            //Here the error is showing that this line is not valid.
            data[2][index] = User_Name.get(index);
            data[3][index] = phone.get(index);

        }
        return results;
    }








//        final LayoutInflater factory = LayoutInflater.from(getContext());
//        final View textEntryView = factory.inflate(R.layout.activity_employee_retrive, null);
//        TextView textView = (TextView) textEntryView.findViewById(R.id.employee_retrive_name);
//        textView.setText(data[0][position]);
//        final TextView textView2 = (TextView) textEntryView.findViewById(R.id.employee_retrive_UAN);
//        textView2.setText(data[1][position]);
//        TextView textView3 = (TextView) textEntryView.findViewById(R.id.employee_retrive_Deg);
//        textView3.setText(data[2][position]);
//        TextView textView4 = (TextView) textEntryView.findViewById(R.id.employee_retrive_phone);
//        textView4.setText(data[3][position]);
//
//        final AlertDialog.Builder alert = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
//        alert.setView(textEntryView).setPositiveButton("Delete",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,
//                                        int whichButton) {
//
//                        Task<Void> documentReference = fStore.collection("Users Detail/" + userId + "/Supervisor List").document(data[1][position]).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//
//                                Toast.makeText(getContext(), "Your Data is successfull Delete", Toast.LENGTH_SHORT).show();
//                                work();
//                            }
//                        });
//
//                    }
//                }).setNegativeButton("Cancel",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,
//                                        int whichButton) {
//
//
//                    }
//                });
//        alert.show();

    }