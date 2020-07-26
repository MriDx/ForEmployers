package com.Creation.App;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("ALL")
public class Monthly extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId, UAN_id;
    TextView employeeRetrive;
    List<String> name, d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, key, UAN_i;
    String UserId, UAN;
    String[][] data = new String[4][100];
    int index;
    Button button_go;
    boolean go;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        employeeRetrive = findViewById(R.id.employee_retrive_name);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        button_go = findViewById(R.id.button_go);

        fAuth = FirebaseAuth.getInstance();

        fStore = FirebaseFirestore.getInstance();

        userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();

        go = false;
        button_go.setVisibility(View.INVISIBLE);
        final Button Monthly = findViewById(R.id.btn_monthly);
        Button Annual = findViewById(R.id.btn_annual);
        Button Employee = findViewById(R.id.btn_employees);
        Button Profile = findViewById(R.id.btn_profile);
        Monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Monthly.class);
                startActivity(intent);
                finish();
            }
        });
        Annual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Annual.class);
                startActivity(intent);
                finish();
            }
        });
        Employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Employees.class);
                startActivity(intent);
                finish();
            }
        });
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);
                finish();
            }
        });

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
        UAN_i = new ArrayList<>();

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
                                .document(userId).collection("Attendance Record").document("25-07-2020").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d1.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d1.add("-");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                button_go.setVisibility(View.VISIBLE);
                            }

                        });


                        //hggh

                       // d1.add("-");
                        d2.add("-");
                        d3.add("-");
                        d4.add("-");
                        d5.add("-");
                        d6.add("-");
                        d7.add("-");
                        d8.add("-");
                        d9.add("-");
                        d10.add("-");

                    }


                    //  Toast.makeText(Employees.this, rf_id.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                    Toast.makeText(Monthly.this, "Error getting documents", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    mAdapter = new MyRecyclerViewAdapterView(getDataSet());
                mRecyclerView.setAdapter(mAdapter);
            }
        });

/**
 Calendar cal = Calendar.getInstance();
 SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
 final String date = sd.format(cal.getTime());

 FirebaseFirestore.getInstance().collection("Users Detail/" + userId + "/Attendance Record").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
@Override public void onComplete(@NonNull Task<QuerySnapshot> task) {
if (task.isSuccessful()) {


for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
key.add(document.getId().toString());
// Toast.makeText(Monthly.this,document.getId().toString(), Toast.LENGTH_SHORT).show();

FirebaseFirestore.getInstance().collection("Users Detail")
.document(userId).collection("Attendance Record").document(document.getId().toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
@Override public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

//  Toast.makeText(Monthly.this, value.getId().toString(), Toast.LENGTH_SHORT).show();

}
});

}


final DocumentReference documentReference = FirebaseFirestore.getInstance()
.collection("Users Detail/" + userId
+ "/Attendance Record").document((date.substring(0, 10)));

documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
@Override public void onComplete(@NonNull Task<DocumentSnapshot> task) {
if (task.isSuccessful()) {
DocumentSnapshot document = task.getResult();
if (document != null && document.exists()) {

// Toast.makeText(Monthly.this, document.getId().toString(), Toast.LENGTH_SHORT).show();
}
}
}
});
}
}
}).addOnFailureListener(new OnFailureListener() {
@Override public void onFailure(@NonNull Exception e) {
Toast.makeText(Monthly.this, "Error in submitting data.", Toast.LENGTH_SHORT).show();
}
});
 **/
    }


    private ArrayList<DataObject> getDataSet() {
        ArrayList results = new ArrayList<DataObject>();

        for (int index = 0; index < name.size(); index++) {

            DataObject obj1 = new DataObject(name.get(index), d1.get(index), d2.get(index), d3.get(index), d4.get(index), d5.get(index), d6.get(index), d7.get(index), d8.get(index), d9.get(index), d10.get(index));
            results.add(index, obj1);

        }
        return results;
    }


}