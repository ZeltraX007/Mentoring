package com.example.mentoring;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mentoring.Model.EmailCheck;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText enterEmail;
    private Button btnLogin;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Boolean userExists = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = String.valueOf(enterEmail.getText());
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                } else {
                    database = FirebaseDatabase.getInstance("https://mentoring-christ-default-rtdb.asia-southeast1.firebasedatabase.app");
//                    to push a value into database
                    myRef = database.getReference().child("Emails");
//                    EmailCheck upload = new EmailCheck("aritra.sarkar@gmail.com", false);
//                    String uploadID = myRef.push().getKey();
//                    myRef.child(uploadID).setValue(upload);
//                    myRef.push().setValue("AritraSarkar@gmail.com");
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot das: snapshot.getChildren()){
                                EmailCheck download = das.getValue(EmailCheck.class);
                                if(email.equals(download.getEmail()) && download.getAccountExists() == false){
//                                    das.child("accountExists").getRef().setValue(true);
                                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                                    intent.putExtra("email",email);
                                    startActivity(intent);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
    }

    private void initViews() {
        btnLogin = findViewById(R.id.btnLogin);
        enterEmail = findViewById(R.id.enterEmail);
    }
}