//Done by Lisa and Chris

package com.example.hw4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextBirdName, editTextPersonName, editTextZipCode;
    Button buttonSubmit, buttonGoToSearching;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    editTextBirdName = findViewById(R.id.editTextBirdName);
    editTextPersonName = findViewById((R.id.editTextPersonName));
    editTextZipCode = findViewById(R.id.editTextZipCodeSearch);

    buttonGoToSearching = findViewById(R.id.buttonGoToSearching);
    buttonSubmit = findViewById(R.id.buttonSubmit);

    buttonSubmit.setOnClickListener(this);
    buttonGoToSearching.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("message");

        if(view == buttonSubmit){
            String birdName = editTextBirdName.getText().toString();
            String personName = editTextPersonName.getText().toString();
            int zipCode =  Integer.parseInt(editTextZipCode.getText().toString());

            Bird myBird = new Bird(birdName, personName, zipCode);
            myRef.push().setValue(myBird);


        }else if (view == buttonGoToSearching){
            Intent goToSearchingIntent = new Intent(this, SearchActivity.class);
            startActivity(goToSearchingIntent);

        }

    }
}
