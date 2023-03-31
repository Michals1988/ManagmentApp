package com.example.pracainzynierska.meeting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.pracainzynierska.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MeetActivity extends AppCompatActivity implements View.OnClickListener {

    Calendar mCalender = Calendar.getInstance();

    TextView textViewDateMeet, textViewTimeMeet;

    EditText editTextTitleMeet, editTextDescriptionMeet, editTextLocationMeet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet);

        editTextTitleMeet = findViewById(R.id.editTextTitleMeet);
        editTextDescriptionMeet = findViewById(R.id.editTextDescriptionMeet);
        editTextLocationMeet = findViewById(R.id.editTextLocationMeet);

        textViewDateMeet = findViewById(R.id.textViewDateMeet);
        textViewTimeMeet = findViewById(R.id.textViewTimeMeet);

        textViewDateMeet.setOnClickListener((View.OnClickListener) this);
        textViewTimeMeet.setOnClickListener((View.OnClickListener) this);

        Button buttonAddNewMeetToCalendar = findViewById(R.id.buttonAddNewMeet);
        Button buttonDismissAddMeet = findViewById(R.id.buttonDissmissMeet);

        buttonAddNewMeetToCalendar.setOnClickListener(this);
        buttonDismissAddMeet.setOnClickListener(this);


    }

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewDateMeet:

                int year = mCalender.get(Calendar.YEAR);
                int month = mCalender.get(Calendar.MONTH);
                int day = mCalender.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dp = new DatePickerDialog(this, android.R.style.Theme_Holo_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        textViewDateMeet.setText(checkDigit(dayOfMonth) + "-" + checkDigit((monthOfYear + 1)) + "-" + year);
                    }
                }, year, month, day);
                dp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dp.show();
                break;

            case R.id.textViewTimeMeet:

                int hour = mCalender.get(Calendar.HOUR_OF_DAY);
                int min = mCalender.get(Calendar.MINUTE);

                TimePickerDialog Tp = new TimePickerDialog(this, android.R.style.Theme_Material_Dialog, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        textViewTimeMeet.setText(checkDigit(hourOfDay) + ":" + checkDigit(minute));

                    }
                }, hour, min, true);
                Tp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Tp.show();
                break;

            case R.id.buttonAddNewMeet:

                Calendar beginTime = Calendar.getInstance();
                Calendar endTime= Calendar.getInstance();

                String dateOfMeeting = textViewDateMeet.getText().toString() + " " + textViewTimeMeet.getText().toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

                try {
                    Date dateStart = dateFormat.parse(dateOfMeeting);
                    beginTime.setTime(dateStart);
                    endTime.setTimeInMillis(beginTime.getTimeInMillis()+1800000);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);
                intent.putExtra(CalendarContract.Events.TITLE, "Urodziny");
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Miejsce wydarzenia");
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
                intent.putExtra(CalendarContract.Events.ALL_DAY, true);

                startActivity(intent);

                Log.d("dodało", "dziala");



                break;

            case R.id.buttonDissmissMeet:
                break;


        }
    }
}