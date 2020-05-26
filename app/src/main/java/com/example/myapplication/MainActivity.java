package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    RadioGroup rgPhones;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("CAIS375 Project");
        rgPhones = findViewById(R.id.Phones);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedId = rgPhones.getCheckedRadioButtonId();
                if (checkedId == -1){
                    //no radio buttons are checked
                    message.message(getApplicationContext(), "please select One of the following");
                }
                else {
                    // one of the radio buttons is checked
                    findRadioButtons(checkedId);
                }
            }
        });
    }
    Intent intent;
    private void findRadioButtons(int checkedId) {
        switch (checkedId){
            case  R.id.view:
                intent= new Intent(MainActivity.this,listofmobilesActivity.class);
                startActivity(intent);
                break;

            case  R.id.add:
                Intent intent= new Intent(MainActivity.this, InsertActivity.class);
                startActivity(intent);
                break;

            case  R.id.update:
                intent= new Intent(MainActivity.this, update_list.class);
                startActivity(intent);
                break;

            case  R.id.delete:
                intent= new Intent(MainActivity.this, listofmobilesActivity.class);
                startActivity(intent);
                break;

        }
    }
}
