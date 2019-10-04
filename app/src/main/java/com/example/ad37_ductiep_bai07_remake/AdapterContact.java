package com.example.ad37_ductiep_bai07_remake;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterContact extends RecyclerView.Adapter<AdapterContact.ViewHoder>{
    List<Contact> list;
    Context context;
    SharedPreferences sharedPreferences;
    IonClickContact ionClickContact ;

    public AdapterContact(List<Contact> list,Context context) {
        this.list = list;
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }
    public void setIonClickContact(IonClickContact ionClickContact) {
        this.ionClickContact = ionClickContact;
    }



    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_layout,parent,false);
        ViewHoder viewHoder= new ViewHoder(view);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, final int position) {

        final  Contact contact = list.get(position);


        holder.tvname.setText(contact.getName());
        holder.tvmassage.setText(" : " +contact.getMessage());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ionClickContact.onClickContact(contact , position, view);
            }
        });


        if (contact.isA()){
            holder.img1.setImageResource(R.drawable.sms);
        }
        else {
            holder.img1.setImageResource(R.drawable.unread);
        }

       final boolean mode =sharedPreferences.getBoolean("mode",true);
         if (mode) {
             holder.tvname.setVisibility(View.VISIBLE);
             holder.tvmassage.setVisibility(View.GONE);
             holder.img1.setVisibility(View.GONE);

         } else {
             if (contact.getMessage()==""){
                 holder.tvmassage.setVisibility(View.GONE);
                 holder.tvname.setVisibility(View.GONE);

                 holder.img1.setVisibility(View.GONE);
             }else {
             holder.tvmassage.setVisibility(View.VISIBLE);
             holder.img1.setVisibility(View.VISIBLE);}}}






    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        TextView tvname,tvmassage;
        ImageView img1;
        RelativeLayout item;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tvname);
            tvmassage = itemView.findViewById(R.id.tvmassage);
            img1 = itemView.findViewById(R.id.img1);
            item = itemView.findViewById(R.id.item);


        }
    }{





    }
}


