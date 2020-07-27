
package com.Creation.App.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.Creation.App.Login;
import com.Creation.App.R;
import com.Creation.App.UserSession;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Profile extends Fragment {
    TextView EstName,gstNum,phone,email;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String UserId;
    Button changePassword;
    FirebaseUser userv;

    public Profile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_profile, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        //do your work here


        // Button Profile=findViewById(R.id.btn_profile);
        Button logout=view.findViewById(R.id.LogoutBtn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                new UserSession(getContext()).removeUser();
                Intent intent = new Intent(getContext(), Login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });


        EstName = view.findViewById(R.id.profileESTName);
        phone = view.findViewById(R.id.profilePhone);
        email =view.findViewById(R.id.profileEmail);
        gstNum = view.findViewById(R.id.profileGSTNum);
        changePassword = view.findViewById(R.id.changepasswordlocal);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        UserId = fAuth.getCurrentUser().getUid();
        userv = fAuth.getCurrentUser();

        DocumentReference documentReference = fStore.collection("Users Detail").document(UserId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
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

                final EditText changePassword = new EditText(getContext());
                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.myDialog));
                builder.setTitle("Change the Password")
                        .setMessage("Enter Your New Password > 6 Characters long.")
                        .setView(changePassword)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                String newPassword = changePassword.getText().toString();
                                userv.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getContext(), "Password changed Successfully.", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getContext(), "Password changed Failed", Toast.LENGTH_SHORT).show();
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
