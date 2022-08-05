package com.example.recyclerviewapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.recyclerviewapp.Adapter.ContactAdapter;
import com.example.recyclerviewapp.contact.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView contactRecyclerView;
    ArrayList<Contact> contacts = new ArrayList<>();
    FloatingActionButton btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.add_contact_btn);
        contactRecyclerView = findViewById(R.id.contactRecyclerView);
        contactRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ContactAdapter adapter = new ContactAdapter(this, contacts);

        contactRecyclerView.setAdapter(adapter);

        btn.setOnClickListener((v) -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_add_contact);

            EditText name = dialog.findViewById(R.id.enter_name);
            EditText phone_number = dialog.findViewById(R.id.enter_phone_no);
            Button submit = dialog.findViewById(R.id.submit);

            submit.setOnClickListener(view -> {
                contacts.add(new Contact(name.getText().toString(), phone_number.getText().toString()));
                adapter.notifyItemInserted(contacts.size() - 1);
                dialog.dismiss();
            });
            dialog.show();
        });
    }
}