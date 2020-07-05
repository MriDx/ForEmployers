package com.Creation.App;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
@SuppressWarnings("ALL")
public class EmployeeDetail extends AppCompatActivity {
//error dikhao
    private RecyclerView mEmployeeList;
    private FirestoreRecyclerAdapter adapter;
    FirebaseAuth fAuth;
    private FirebaseFirestore firebaseFirestore;
    String UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);

        mEmployeeList = findViewById(R.id.employeelist);
        firebaseFirestore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        UserId = fAuth.getCurrentUser().getUid();

        //Query ek gltiye hai ruke ek min
        Query query = firebaseFirestore.collection("Users Detail/0RDDqY6G10YWpaQgQk9LxY5sya12/EmployeeList");
        //Recycler Options
        FirestoreRecyclerOptions<EmployeeModal> options = new FirestoreRecyclerOptions.Builder<EmployeeModal>()
                .setQuery(query, EmployeeModal.class)
                .build();
        adapter = new FirestoreRecyclerAdapter<EmployeeModal, EmployeeViewHolder>(options) {
            @NonNull
            @Override
            public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_employee_signal, parent, false);
                return new EmployeeViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position, @NonNull EmployeeModal model) {
                holder.listname.setText(model.getName());

            }
        };

        mEmployeeList.setHasFixedSize(true);
        mEmployeeList.setLayoutManager(new LinearLayoutManager(this));
        mEmployeeList.setAdapter(adapter);


        //View Holder





    }

    private class EmployeeViewHolder extends RecyclerView.ViewHolder {

        private TextView listname;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            listname = findViewById(R.id.list_name);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}