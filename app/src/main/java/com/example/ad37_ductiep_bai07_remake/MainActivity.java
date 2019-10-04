package com.example.ad37_ductiep_bai07_remake;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.ad37_ductiep_bai07_remake.R.drawable.transparent;

public class MainActivity extends AppCompatActivity  {

    TextView tvfriends , tvmess ;
    RecyclerView rcv1;
    RelativeLayout rladd;
    ImageView imgrl;
    Contact ct1 , ct2 , ct3 , ct4 , ct5;
    AdapterContact adapterContact;
    List<String> listSpinner;
    int mPosition = -1;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    List<Contact> list ;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        tvfriends = findViewById(R.id.tvfriends);
        tvmess = findViewById(R.id.tvmess);
        rcv1 = findViewById(R.id.rcv1);
        rladd = findViewById(R.id.rladd);
        imgrl = findViewById(R.id.imgrl);
        list = new ArrayList<>();
        listSpinner = new ArrayList<>();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        editor = sharedPreferences.edit();

        ct1 = new Contact("MR.A","",true);
        ct2 = new Contact("MR.B","Can you give me some money?",false);
        ct3 = new Contact("MR.C","Hello , how are you?",true);
        ct4 = new Contact("MR.D","",true);
        ct5 = new Contact("MR.E","Yes i am",true);

        listSpinner.add("MR.A");
        listSpinner.add("MR.B");
        listSpinner.add("MR.C");
        listSpinner.add("MR.D");
        listSpinner.add("MR.E");

        list.add(ct1);
        list.add(ct2);
        list.add(ct3);
        list.add(ct4);
        list.add(ct5);
        editor.putBoolean("mode",true);
        editor.commit();

        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(getBaseContext()
                ,LinearLayoutManager.VERTICAL,false);


        adapterContact = new AdapterContact(list,getBaseContext());

        adapterContact.setIonClickContact(new IonClickContact() {
            @Override
            public void onClickContact(final Contact contact,int position, View view) {


                mPosition = position;

                PopupMenu popupMenu = new PopupMenu(getBaseContext(),view);
                popupMenu.getMenuInflater().inflate(R.menu.contact_menu,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        switch (menuItem.getItemId()){
                            case R.id.sendsms:
                                editor.putBoolean("sms",true);
                                editor.commit();
                                Intent intent = new Intent(getBaseContext(), SendSMSEmail.class);

                                intent.putExtra("listContact", (Serializable) listSpinner);
                                startActivity(intent);

                                break;
                            case R.id.edit:

                                Intent intent1 = new Intent(getBaseContext(),AddOrUpdateContact.class);
                                intent1.putExtra("name",contact.getName());
                                startActivityForResult(intent1,10);

                                break;
                        }

                        return false;
                    }
                });

                popupMenu.show();



//                String[] menu = {"Send SMS","Edit"};
//                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
//                        .setSingleChoiceItems(menu, 0, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                                Toast.makeText(getBaseContext(), "OK", Toast.LENGTH_SHORT).show();
//                            }
//                        }).create();
//                alertDialog.show();


            }
        });

        rcv1.setLayoutManager(layoutManager);
        rcv1.setAdapter(adapterContact);




        tvmess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvfriends.setBackgroundResource(transparent);
                tvmess.setBackgroundResource(R.drawable.tab_background_messages);
                imgrl.setBackgroundResource(R.drawable.send);

                editor.putBoolean("mode" , false);
                editor.commit();
                adapterContact.notifyDataSetChanged();

            }
        });


        tvfriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvmess.setBackgroundResource(transparent);
                tvfriends.setBackgroundResource(R.drawable.tab_background_friends);
                imgrl.setBackgroundResource(R.drawable.addfriend);

                editor.putBoolean("mode" , true);
                editor.commit();
                adapterContact.notifyDataSetChanged();
            }
        });

        rladd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b = sharedPreferences.getBoolean("mode", true);
                if (b) {
                    Intent intent = new Intent(getBaseContext(), AddOrUpdateContact.class);
                    intent.putExtra("name" , "");


                    startActivityForResult(intent,20);
                } else {
                    PopupMenu popupMenu = new PopupMenu(getBaseContext(),view );
                    popupMenu.getMenuInflater().inflate(R.menu.send_menu,popupMenu.getMenu());

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {

                            switch (menuItem.getItemId()){
                                case R.id.sendsms:
                                    editor.putBoolean("sms",true);
                                    editor.commit();
                                    Intent intent = new Intent(getBaseContext(), SendSMSEmail.class);

                                    intent.putExtra("listContact", (Serializable) listSpinner);
                                    startActivity(intent);

                                    break;

                                case R.id.sendemail:
                                    editor.putBoolean("sms",false);
                                    editor.commit();
                                     intent = new Intent(getBaseContext(), SendSMSEmail.class);

                                    intent.putExtra("listContact", (Serializable) listSpinner);
                                    startActivity(intent);

                                    break;


                            }
                            return false;
                        }
                    });
                    popupMenu.show();
            }

            }
        });










    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case RESULT_OK:

                String name = data.getStringExtra("newname");

                if (requestCode == 10) {
                    list.set(mPosition, new Contact(name, list.get(mPosition).getMessage(), false));
                    listSpinner.set(mPosition,name);
                    adapterContact.notifyDataSetChanged();

                } else if (requestCode == 20) {
                    list.add(new Contact(name, "", false));
                    listSpinner.add(name);
                    adapterContact.notifyDataSetChanged();
                }

        }

    }


}
