package com.example.t420.applazada.View.DangNhap;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.t420.applazada.Adapter.ViewPager.AdapterViewPagerDangNhap;
import com.example.t420.applazada.R;
import com.facebook.FacebookSdk;

public class DangNhapActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout= (TabLayout) findViewById(R.id.tabLayoutDangNhap);
        viewPager= (ViewPager) findViewById(R.id.viewpagerDangNhap);
        tabLayout.setupWithViewPager(viewPager);
        AdapterViewPagerDangNhap viewPagerDangNhap=new AdapterViewPagerDangNhap(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerDangNhap);
    }
}
