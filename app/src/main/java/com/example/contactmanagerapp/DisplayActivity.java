package com.example.contactmanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.*;
import java.util.ArrayList;
public class DisplayActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> titles = new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        SQLiteDatabase database  = openOrCreateDatabase("course_db", Context.MODE_PRIVATE,null);

        listView = findViewById(R.id.lstview);

        String sql = "SELECT * FROM course";
        Cursor cursor = database.rawQuery(sql,null);
        titles.clear();

        adapter = new ArrayAdapter(DisplayActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,titles);
        listView.setAdapter(adapter);

        final ArrayList<Model> model = new ArrayList<>();
        while (cursor.moveToNext()){
            Model m = new Model();
            m.id = cursor.getInt(0);
            m.stud_name = cursor.getString(1);
            m.sub_name = cursor.getString(2);
            m.fees = cursor.getInt(3);

            String result = m.id+ "\t" + m.stud_name+ "\t" + m.sub_name + "\t" +m.fees;
            titles.add(result);
        }
        adapter.notifyDataSetChanged();
        listView.invalidateViews();
    }
}