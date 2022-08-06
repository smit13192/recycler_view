package com.example.recyclerviewapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recyclerviewapp.Adapter.ContactAdapter;
import com.example.recyclerviewapp.DataBase.ContactDataBase;
import com.example.recyclerviewapp.contact.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView contactRecyclerView;
    ArrayList<Contact> contacts = new ArrayList<>();
    FloatingActionButton btn;
    ContactDataBase db;
    ContactAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new ContactDataBase(this);
        seeContact();

        btn = findViewById(R.id.add_contact_btn);
        contactRecyclerView = findViewById(R.id.contactRecyclerView);
        contactRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ContactAdapter(this, contacts);

        contactRecyclerView.setAdapter(adapter);

        btn.setOnClickListener((v) -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_add_contact);

            EditText name = dialog.findViewById(R.id.enter_name);
            EditText phone_number = dialog.findViewById(R.id.enter_phone_no);
            Button submit = dialog.findViewById(R.id.submit);

            submit.setOnClickListener(view -> {

                if (!name.getText().toString().isEmpty() && phone_number.getText().toString().length() == 10) {

                    Contact contact = new Contact(name.getText().toString(), phone_number.getText().toString());
                    contacts.add(contact);
                    db.addContact(contact);
                    adapter.notifyItemInserted(contacts.size() - 1);
                    dialog.dismiss();
                } else {
                    Toast.makeText(this, "Phone Number Must Be 10 Letter && Name Are Not Blank", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
        });
    }

    private void seeContact() {
        contacts = db.getContact();
    }
}