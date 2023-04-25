package com.example.contactmanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import android.database.sqlite.SQLiteDatabase;
public class MainActivity extends AppCompatActivity {

    EditText e1,e2,e3;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1 = findViewById(R.id.et_st_name);
        e2 = findViewById(R.id.et_sub_name);
        e3 = findViewById(R.id.et_co_fees);
        b1 = findViewById(R.id.btn_submit);
        b2 = findViewById(R.id.btn_display);

        b1.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                if (TextUtils.isEmpty(e1.getText().toString()) && TextUtils.isEmpty(e2.getText().toString()) && TextUtils.isEmpty(e3.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Enter all fields!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    insertData();
                    e1.setText("");
                    e2.setText("");
                    e3.setText("");
                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DisplayActivity.class));
            }
        });
    }

    public void insertData(){
        try {
            String stud_name = e1.getText().toString();
            String sub_name = e2.getText().toString();
            int fees = Integer.parseInt(e3.getText().toString());

            SQLiteDatabase database = openOrCreateDatabase("course_db", Context.MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS course(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, course VARCHAR, fees INTEGER);");

            String sql = "INSERT INTO course (name,course,fees) VALUES (?,?,?);";
            ContentValues cv = new ContentValues();
            cv.put("name", stud_name);
            cv.put("course", sub_name);
            cv.put("fees", fees);
            long status = database.insert("course", null, cv);
            if (status > 0) {
                Toast.makeText(this, "Details submit successfully....", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to insert details", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}