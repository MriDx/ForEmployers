package com.Creation.App;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

@SuppressWarnings("ALL")
public class Profile extends AppCompatActivity {
    TextView EstName,gstNum,phone,email;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String UserId;
    Button changePassword;
    FirebaseUser userv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Button Monthly=findViewById(R.id.btn_monthly);
        Button Annual=findViewById(R.id.btn_annual);
        Button Employee=findViewById(R.id.btn_employees);
       // Button Profile=findViewById(R.id.btn_profile);
        Button logout=findViewById(R.id.LogoutBtn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                new UserSession(Profile.this).removeUser();
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });
        Monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Monthly.class);
                startActivity(intent);
                finish();
            }
        });
        Annual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Annual.class);
                startActivity(intent);
                finish();
            }
        });
        Employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Employees.class);
                startActivity(intent);
                finish();
            }
        });

        EstName = findViewById(R.id.profileESTName);
        phone = findViewById(R.id.profilePhone);
        email =findViewById(R.id.profileEmail);
        gstNum = findViewById(R.id.profileGSTNum);
        changePassword = findViewById(R.id.changepasswordlocal);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        UserId = fAuth.getCurrentUser().getUid();
        userv = fAuth.getCurrentUser();

        DocumentReference documentReference = fStore.collection("Users Detail").document(UserId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null){
                    Log.d("TAG", "Null");
                }else{
                    EstName.setText(documentSnapshot.getString("Establisment Name"));
                    phone.setText(documentSnapshot.getString("Phone Number"));
                    email.setText(documentSnapshot.getString("Email Id"));
                    gstNum.setText(documentSnapshot.getString("GST No"));
                }

            }
        });



        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText changePassword = new EditText(getApplicationContext());
                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(Profile.this, R.style.myDialog));
                builder.setTitle("Change the Password")
                        .setMessage("Enter Your New Password > 6 Characters long.")
                        .setView(changePassword)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                String newPassword = changePassword.getText().toString();
                                userv.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(Profile.this, "Password changed Successfully.", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Profile.this, "Password changed Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Do nothing.
                    }
                }).show();
            }
        });
    }
}