package com.example.ad37_ductiep_bai07_remake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddOrUpdateContact extends AppCompatActivity {

    String name;
    TextView tvname , tvstt , tvsave;
    EditText edtname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_update_contact);

        tvstt = findViewById(R.id.tvstt);
        tvsave = findViewById(R.id.tvsave);
        edtname = findViewById(R.id.edtname);

       final Intent intent = getIntent();
        name = intent.getStringExtra("name");

        if (name.equals("")){
            tvsave.setText("Add Contact");
            tvstt.setText("Add New Contact");

        }
        else {tvsave.setText("Update");
            edtname.setText(name);
            tvstt.setText("Edit Contact");

        }

        tvsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                intent1.putExtra("newname",edtname.getText().toString());
                setResult(RESULT_OK,intent1);
                finish();
            }
        });



    }
}
