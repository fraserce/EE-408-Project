package com.example.ee408project;

import android.os.Bundle;

import com.example.ee408project.ui.main.Tab1Fragment;
import com.example.ee408project.ui.main.Tab2Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.ee408project.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.view_pager);

        adapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "FORM");
        adapter.addFragment(new Tab2Fragment(), "TABLE");
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "blah", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}