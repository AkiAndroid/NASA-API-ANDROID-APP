package com.example.alienware;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class DatePicker extends AppCompatActivity {

    private TextView datetext;
    ImageButton lookButton;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    int day1,month1,year1;
    int todayday,todaymonth,todayyear;
    String Date="";
    Calendar calendar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        lookButton=findViewById(R.id.lookbutton);

        datetext=findViewById(R.id.datetext);


        datetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar=Calendar.getInstance();
               int day=calendar.get(Calendar.DAY_OF_MONTH);
               int month=calendar.get(Calendar.MONTH);
               int year=calendar.get(Calendar.YEAR);


                DatePickerDialog dialog=new DatePickerDialog(
                        DatePicker.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        dateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });

        dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker datePicker, int i, int i1, int i2) {
                year1=i;
                month1=i1+1;
                day1=i2;
                datetext.setText(year1+":"+month1+":"+day1);
               Date=String.valueOf(year1)+"-"+String.format("%02d",month1)+"-"+String.format("%02d",day1);
                Log.d("Hello", Date);
                lookButton.setVisibility(View.VISIBLE);

                calendar1=Calendar.getInstance();
                calendar1.set(Calendar.YEAR,year1);
                calendar1.set(Calendar.MONTH,month1-1);
                calendar1.set(Calendar.DAY_OF_MONTH,day1);


            }
        };

        lookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Log.d("Hello",String.valueOf(day1));

               if(!calendar1.after(Calendar.getInstance())){
                   Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                   intent.putExtra("date", Date);
                   startActivity(intent);

               }

                else {
                    Toast.makeText(DatePicker.this, "It cannot predict the future", Toast.LENGTH_SHORT).show();


                }

            }
        });


    }
}