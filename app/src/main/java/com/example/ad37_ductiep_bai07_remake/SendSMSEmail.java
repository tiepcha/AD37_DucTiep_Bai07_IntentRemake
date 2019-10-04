package com.example.ad37_ductiep_bai07_remake;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SendSMSEmail extends AppCompatActivity {

    TextView tvsendwhat,tvsend ;

    List<String> listSpinner;
    Spinner spnfriend;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText edtname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_smsemail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        editor = sharedPreferences.edit();

        tvsend = findViewById(R.id.tvsend);
        tvsendwhat = findViewById(R.id.tvsendwhat);
        spnfriend = findViewById(R.id.spnfriend);
        edtname = findViewById(R.id.edtname);
        listSpinner = new ArrayList<>();

        final Intent intent = getIntent();
        List<String> listSpinner = (List<String>) intent.getSerializableExtra("listContact");




        boolean b = sharedPreferences.getBoolean("sms",true);
        if (b){
            tvsendwhat.setText("Send SMS");
        } else {
            tvsendwhat.setText("Send Email");
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getBaseContext(),android.R.layout.simple_list_item_1,listSpinner);
        spnfriend.setAdapter(arrayAdapter);



    }
}
