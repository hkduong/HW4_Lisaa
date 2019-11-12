package com.example.hw4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextZipCodeSearch;
    Button buttonSearch, buttonGoToReport;
    TextView textViewBirdName, textViewPersonName;

    //Add some 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);

        editTextZipCodeSearch = findViewById(R.id.editTextZipCodeSearch);

        textViewBirdName = findViewById(R.id.textViewBirdName);
        textViewPersonName = findViewById(R.id.textViewPersonName);

        buttonSearch = findViewById(R.id.buttonSearch);
        buttonGoToReport = findViewById(R.id.buttonGoToReport);

        buttonSearch.setOnClickListener(this);
        buttonGoToReport.setOnClickListener(this);

    }
    @Override
    public void onClick(View view){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("message");

    if (view == buttonSearch){
        String searchZipCode = editTextZipCodeSearch.getText().toString();
        myRef.orderByChild("Zipcode").equalTo(searchZipCode).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String findKey = dataSnapshot.getKey();
                Bird foundBirdName = dataSnapshot.getValue(Bird.class);
                Bird foundPersonName = dataSnapshot.getValue(Bird.class);

                String findBird = foundBirdName.birdName;
                String findPerson = foundPersonName.personName;

                textViewBirdName.setText(findBird);
                textViewPersonName.setText(findPerson);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }else if (view == buttonGoToReport){
        Intent goToReportIntent = new Intent(this, MainActivity.class);
        startActivity(goToReportIntent);

    }
    }
}
