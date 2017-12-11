package com.example.t420.applazada.View.ManHinhChao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.t420.applazada.R;
import com.example.t420.applazada.View.TrangChu.TrangChuActivity;

public class ManHinhChaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchao);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(ManHinhChaoActivity.this, TrangChuActivity.class);
                startActivity(intent);
                finish();
            }
        },1000);
    }
}
