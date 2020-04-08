package com.example.tema3;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;


public class Fragment3 extends Fragment {
    private static Context context;
    public static final int REQUEST_CODE=101;
    private Button button1;
    private Button button2;
    private Button button3;
    TimePickerDialog picker;
    DatePickerDialog picker2;
    EditText editText;
    EditText editText2;
    private static final int NOTIFICATION_ID=0;
    private static final String PRIMART_CHANNEL_ID="primary_notificasion_channel";
    private NotificationManager mnotificationManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_3, container, false);


        button1=layout.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final Calendar calendar=Calendar.getInstance();
                int hour=calendar.get(Calendar.HOUR_OF_DAY);
                int minutes=calendar.get(Calendar.MINUTE);

                picker=new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                        editText.setText(sHour + ":" + sMinute);

                    }
                },hour, minutes, true);
                picker.show();

            }
        });


        button2=layout.findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar=Calendar.getInstance();
                int  day=calendar.get(Calendar.DAY_OF_MONTH);
                int month=calendar.get(Calendar.MONTH);
                int year=calendar.get(Calendar.YEAR);
                picker2= new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        editText2.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year, month, day);
                picker.show();


            }
        });

        mnotificationManager=(NotificationManager) getContext().getSystemService((Context.NOTIFICATION_SERVICE));
        button3=layout.findViewById(R.id.button3);
        Intent notifyIntent=new Intent(this.getContext(), MyReceiver.class);

        final PendingIntent notifyPendingIntent=PendingIntent.getBroadcast(this.getContext(),NOTIFICATION_ID,notifyIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        final AlarmManager alarmManager=(AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);




        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alarmManager!=null){
                    int day, hour, minutes, month, year;
                    Calendar calendar=Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY,hour);
                    calendar.set(Calendar.MINUTE,minutes);
                    calendar.set(Calendar.YEAR,year);
                    calendar.set(Calendar.MONTH,month);
                    calendar.set(Calendar.DAY_OF_MONTH,day);
                    alarmManager.setExact(AlarmManager.RTC, calendar.getTimeInMillis(), notifyPendingIntent);
                   // alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis()+AlarmManager.INTERVAL_HOUR,pendingIntent);
                   // alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis()+AlarmManager.INTERVAL_HOUR, AlarmManager.INTERVAL_HOUR, pendingIntent);
                    //return inflater.inflate(R.layout.fragment_3, container, false);


                }

            }
        });

        createNotificationChannel();
        return  layout;

    }

    private void createNotificationChannel() {
        mnotificationManager=(NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            NotificationChannel notificationChannel=new NotificationChannel(PRIMART_CHANNEL_ID,"Stand up notification",NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            mnotificationManager.createNotificationChannel(notificationChannel);

        }
    }


}
