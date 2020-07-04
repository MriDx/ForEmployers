package com.Creation.App;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

@SuppressWarnings("ConstantConditions")
public class ProfileTab extends AppCompatActivity {
    TextView fullName,phone,email,companyName;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String UserId;
    Button changePassword;
    FirebaseUser userv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_tab);
        fullName = findViewById(R.id.profileName);
        phone = findViewById(R.id.profilePhone);
        email =findViewById(R.id.profileEmail);
        companyName = findViewById(R.id.profileCompanyName);
        changePassword = findViewById(R.id.changepasswordlocal);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        UserId = fAuth.getCurrentUser().getUid();
        userv = fAuth.getCurrentUser();


        DocumentReference documentReference = fStore.collection("Users Detail").document(UserId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
           fullName.setText(documentSnapshot.getString("Full Name"));
           phone.setText(documentSnapshot.getString("Phone Number"));
           email.setText(documentSnapshot.getString("Email Id"));
           companyName.setText(documentSnapshot.getString("Company Name"));

            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText changePassword = new EditText(getApplicationContext());
                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(ProfileTab.this, R.style.myDialog));
                builder.setTitle("Change the Password")
                        .setMessage("Enter Your New Password > 6 Characters long.")
                        .setView(changePassword)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                String newPassword = changePassword.getText().toString();
                                userv.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(ProfileTab.this, "Password changed Successfully.", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(ProfileTab.this, "Password changed Failed", Toast.LENGTH_SHORT).show();
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




    public void LogOut(View view) {
        FirebaseAuth.getInstance().signOut();
        new UserSession(ProfileTab.this).removeUser();
        startActivity(new Intent(ProfileTab.this,Login.class));
        finish();
    }
}