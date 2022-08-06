package com.example.recyclerviewapp.DataBase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.recyclerviewapp.Paramas.Params;
import com.example.recyclerviewapp.contact.Contact;

import java.util.ArrayList;

public class ContactDataBase extends SQLiteOpenHelper {
    public ContactDataBase(@Nullable Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Params.TABLE_NAME + " ( " + Params.KEY_ID +
                " INTEGER PRIMARY KEY ," + Params.KEY_NAME + " TEXT, " + Params.KEY_NUMBER +
                " TEXT )";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Params.KEY_NAME, contact.getName());
        values.put(Params.KEY_NUMBER, contact.getPhone_no());

        db.insert(Params.TABLE_NAME,null,values);
        db.close();
    }

    public ArrayList<Contact> getContact() {
        ArrayList<Contact> contacts = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM " + Params.TABLE_NAME;

        Cursor cursor = db.rawQuery(select,null);

        while(cursor.moveToNext()) {
            Contact contact = new Contact();
            contact.setId(cursor.getInt(0));
            contact.setName(cursor.getString(1));
            contact.setPhone_no(cursor.getString(2));
            contacts.add(contact);
        }
        cursor.close();
        return contacts;
    }
}
