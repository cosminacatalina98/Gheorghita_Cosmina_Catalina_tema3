package com.example.tema3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         Fragment1 fragment1=new Fragment1();
         FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
         fragmentTransaction.add(R.id.fragment_container, fragment1, "fragment1" );
         fragmentTransaction.commit();
    }
}
