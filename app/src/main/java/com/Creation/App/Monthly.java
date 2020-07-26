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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@SuppressWarnings("ALL")
public class Monthly extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId, UAN_id;
    TextView employeeRetrive;
    List<String> name, d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, key, UAN_i, date_data,d11,d12,d13,d14,d15,d16,d17,d18,d19,d20,d21,d22,d23,d24,d25,d26,d27,d28,d29,d30,d31;
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
            date_data.add(i, (i + 1) +"-"+ month +"-"+year);
           // Toast.makeText(this, (i + 1) +"-"+ month +"-"+year, Toast.LENGTH_SHORT).show();
        }

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

                                if (value.getString(id) != null) {
                                    d1.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d1.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }

                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(0).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d1.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d1.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }

                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(1).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d2.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d2.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }

                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(2).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d3.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d3.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }

                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(3).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d4.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d4.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                               // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(4).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d5.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d5.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(5).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d6.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d6.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(6).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d7.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d7.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                // button_go.setVisibility(View.VISIBLE);
                            }

                        }); FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(7).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                        if (value.getString(id) != null) {
                                            d8.add(value.getString(id));
                                            //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                        } else {
                                            d8.add(" ");
                                            //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                        }
                                        // button_go.setVisibility(View.VISIBLE);
                                    }

                                }); FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(8).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                        if (value.getString(id) != null) {
                                            d9.add(value.getString(id));
                                            //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                        } else {
                                            d9.add(" ");
                                            //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                        }
                                        // button_go.setVisibility(View.VISIBLE);
                                    }

                                });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(9).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d10.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d10.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(10).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d11.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d11.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(11).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d12.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d12.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(12).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d13.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d13.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(13).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d14.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d14.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(14).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d15.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d15.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(15).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d16.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d16.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(16).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d17.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d17.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(17).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d18.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d18.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(18).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d19.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d19.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(19).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d20.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d20.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(20).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d21.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d21.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(21).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d22.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d22.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                // button_go.setVisibility(View.VISIBLE);
                            }

                        }); FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(22).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                        if (value.getString(id) != null) {
                                            d23.add(value.getString(id));
                                            //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                        } else {
                                            d23.add(" ");
                                            //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                        }
                                        // button_go.setVisibility(View.VISIBLE);
                                    }

                                });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(23).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d24.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d24.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(24).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d25.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d25.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(25).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d26.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d26.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(26).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d27.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d27.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(27).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d28.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d28.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(28).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d29.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d29.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(29).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d30.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d30.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                // button_go.setVisibility(View.VISIBLE);
                            }

                        });
                        FirebaseFirestore.getInstance().collection("Users Detail")
                                .document(userId).collection("Attendance Record").document(date_data.get(30).toString()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                                if (value.getString(id) != null) {
                                    d31.add(value.getString(id));
                                    //  Toast.makeText(Monthly.this, id+" "+value.getString(id), Toast.LENGTH_SHORT).show();
                                } else {
                                    d31.add(" ");
                                    //Toast.makeText(Monthly.this, "Not Pres"+value.getString(id), Toast.LENGTH_SHORT).show();
                                }
                                button_go.setVisibility(View.VISIBLE);
                            }

                        });











                        //hggh

                        // d1.add("-");
                       // d2.add("-");
                       // d3.add("-");







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

            DataObject obj1 = new DataObject(name.get(index), d1.get(index), d2.get(index), d3.get(index), d4.get(index), d5.get(index), d6.get(index), d7.get(index), d8.get(index), d9.get(index), d10.get(index), d11.get(index), d12.get(index), d13.get(index), d14.get(index), d15.get(index), d16.get(index), d17.get(index), d18.get(index), d19.get(index), d20.get(index), d21.get(index), d22.get(index), d23.get(index), d24.get(index), d25.get(index), d26.get(index), d27.get(index), d28.get(index), d29.get(index), d30.get(index), d31.get(index));

            results.add(index, obj1);

        }
        return results;
    }


}